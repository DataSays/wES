package org.datasays.wes.toolkit;

import okhttp3.OkHttpClient;
import org.datasays.util.JsonObjGetter;
import org.datasays.wes.core.HttpException;
import org.datasays.wes.core.JsonObj;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.core.WHttpClient;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by watano on 2016/12/4.
 */
public class BintrayUtil {
	private WHttpClient client;
	private String baseUrl = "https://api.bintray.com/";
	private String subject;
	private String repo;
	private String user;
	private String apiKey;

	public BintrayUtil(String subject, String repo, String user, String apiKey) {
		this.subject = subject;
		this.repo = repo;
		this.user = user;
		this.apiKey = apiKey;
	}

	public void init() {
		client = new WHttpClient(new OkHttpClient.Builder().build(), new WGsonConvert());
		client.setLogFlag(true, true, true);
	}

	private RequestInfo create() {
		RequestInfo action = new RequestInfo(baseUrl);
		action.baseAuth(user, apiKey);
		return action;
	}

	private void exec(Object o) throws HttpException {
		JsonObjGetter result = new JsonObjGetter(o);
		String message = result.str("message");
		if (message != null && "success".equalsIgnoreCase(message)) {
		} else if (message != null) {
			throw new HttpException(new Exception(message));
		}
	}

	public void createPackage(Object packageInfo) {
		try {
			RequestInfo action = create();
			action.setUrl("packages", subject, repo);
			action.setBody(packageInfo);

			exec(client.post(action, Object.class));
		} catch (HttpException e) {
			System.err.println(e.toText());
			e.printStackTrace();
		}
	}

	public void updatePackage(Object packageInfo, String pkg) {
		try {
			RequestInfo action = create();
			action.setUrl("packages", subject, repo, pkg);
			action.setBody(packageInfo);

			exec(client.patch(action, Object.class));
		} catch (HttpException e) {
			System.err.println(e.toText());
			e.printStackTrace();
		}
	}

	public void uploadContent(String filePath, String file, String pkg, String version, boolean publish, boolean override, boolean explode) {
		try {
			RequestInfo action = create();
			filePath += ";bt_package=" + pkg + ";bt_version=" + version + ";publish=" + (publish ? 1 : 0) + ";override=" + (override ? 1 : 0) + ";explode=" + (explode ? 1 : 0);
			action.setUrl("content", subject, repo, filePath);
			action.setBody(new File(file));

			exec(client.put(action, Object.class));
		} catch (HttpException e) {
			System.err.println(e.toText());
			e.printStackTrace();
		}
	}

	public void mavenCentralSync(String pkg, String version) {
		try {
			RequestInfo action = create();
			action.setUrl("maven_central_sync", subject, repo, pkg, "versions", version);
			exec(client.post(action, Object.class));
		} catch (HttpException e) {
			System.err.println(e.toText());
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		BintrayUtil bintray = new BintrayUtil("datasays","maven","watano", args[0]);
		bintray.init();
		Map<String, String> projects = new HashMap<>();
		projects.put("wUtil", "1.0");
		projects.put("wES-client", "1.0");
		projects.put("wES-toolkit", "0.5");
		projects.put("wES-benchmark", "0.5");
		for (String project : projects.keySet()) {
			String pkg = "io.github.datasays:" + project;
			String version = projects.get(project);
			JsonObj packageInfo = new JsonObj("name", pkg, "desc", "DataSays " + project);
			packageInfo.lstAdd("labels", "elasticsearch", "DataSays");
			packageInfo.lstAdd("licenses", "MIT");
			packageInfo.addAll(
					"vcs_url", "https://github.com/DataSays/wES.git",
					"website_url", "https://github.com/DataSays/wES",
					"issue_tracker_url", "https://github.com/DataSays/wES/issues",
					"github_repo", "DataSays/wES",
					//"github_release_notes_file", "RELEASE.md",
					"public_download_numbers", false,
					"public_stats", true);
			bintray.createPackage(packageInfo);
			//bintray.updatePackage(packageInfo, pkg);

			bintray.uploadContent("org/datasays/" + project + "/maven-metadata.xml", "./wES-toolkit/maven-metadata-local.xml", pkg, version, true, true, false);
			bintray.uploadContent("org/datasays/" + project + "/" + version + "/" + project + "-" + version + ".pom", "./" + project + "/" + version + "/" + project + "-" + version + ".pom", pkg, version, true, true, false);
			bintray.uploadContent("org/datasays/" + project + "/" + version + "/" + project + "-" + version + ".jar", "./" + project + "/" + version + "/" + project + "-" + version + ".jar", pkg, version, true, true, false);
			bintray.uploadContent("org/datasays/" + project + "/" + version + "/" + project + "-" + version + "-sources.jar", "./" + project + "/" + version + "/" + project + "-" + version + "-sources.jar", pkg, version, true, true, false);

			//bintray.mavenCentralSync(pkg, version);
		}
	}
}
