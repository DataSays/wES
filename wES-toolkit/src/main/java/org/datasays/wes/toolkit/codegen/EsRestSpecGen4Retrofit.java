package org.datasays.wes.toolkit.codegen;

import jodd.io.FileUtil;
import jodd.util.StringUtil;
import org.datasays.util.FindFileUtil;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EsRestSpecGen4Retrofit {
	//	private static Logger LOG = LoggerFactory.getLogger(EsRestSpecGen4Retrofit);
	private StringBuilder codes = new StringBuilder();

	public void appendCode(String line) {
		codes.append(line);
		codes.append("\n");
	}

	public static String javaType(String type) {
		if ("string".equalsIgnoreCase(type)) {
			return "String";
		} else if ("list".equalsIgnoreCase(type)) {
			return "String";
		} else {
			return type;
		}
	}

	public void writeToFile(String file) {
		StringBuilder javaCodes = new StringBuilder();
		javaCodes.append("package org.datasays.wes.client;\n\n");

		javaCodes.append("import retrofit2.Call;\n");
		javaCodes.append("import retrofit2.http.Body;\n");
		javaCodes.append("import retrofit2.http.DELETE;\n");
		javaCodes.append("import retrofit2.http.GET;\n");
		javaCodes.append("import retrofit2.http.HEAD;\n");
		javaCodes.append("import retrofit2.http.POST;\n");
		javaCodes.append("import retrofit2.http.PUT;\n");
		javaCodes.append("import retrofit2.http.Path;\n\n");

		javaCodes.append("public interface EsService {\n");
		javaCodes.append("	" + codes.toString() + "\n");

		javaCodes.append("}\n");
		try {
			FileUtil.writeString(file, javaCodes.toString(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parseCode(String jsoncode) {
		JsonObjGetter jsonobj = WJsonUtils.fromJson(jsoncode);
		if (jsonobj != null && jsonobj.map() != null) {
			jsonobj.map().forEach((Object k, Object v) -> {
				String apiName = k.toString();
				String apiId = apiName.toString();
				JsonObjGetter apiInfo = new JsonObjGetter(v);
				// documentation
				appendCode("\n\t/**");
				appendCode("\t* documentation: " + apiInfo.str("documentation") + "");
				appendCode("");
				Map<?, ?> params = apiInfo.obj("url").map("params");
				if (params != null) {
					params.forEach((k2, paramValue) -> {
						String paramName = k2.toString();
						JsonObjGetter paramInfo = new JsonObjGetter(paramValue);
						appendCode("\t* param: " + paramInfo.str("type") + " " + paramName + ": " + paramInfo.str("description") + "");
					});
				}
				// body
				boolean bodyRequired = false;
				if (apiInfo.obj("body") != null) {
					if (apiInfo.obj("body").bool("required") != null && apiInfo.obj("body").bool("required")) {
						bodyRequired = true;
					}
					appendCode("\t* body" + (bodyRequired ? "*" : "") + ":" + apiInfo.obj("body").str("description"));
				}
				appendCode("");
				// parts
				Map<?, ?> parts = apiInfo.obj("url").map("parts");
				if (parts != null) {
					parts.forEach((k3, partValue) -> {
						String partName = k3.toString();
						JsonObjGetter partInfo = new JsonObjGetter(partValue);
						appendCode("\t* @param " + partInfo.str("type") + " " + partName + ": " + partInfo.str("description") + "");
					});
				}
				appendCode("*/");

				// http method
				List<?> methods = apiInfo.list("methods");
				Map<String, Integer> apiMethods = new Hashtable<>();
				for (Object m : methods) {
					String httpMethod = m.toString().toUpperCase();
					// url path
					List<?> paths = apiInfo.obj("url").list("paths");
					for (int i = 0; i < paths.size(); i++) {
						String path = paths.get(i).toString();
						if (path.startsWith("/")) {
							path = path.substring(1);
						}
						String apiMethod = StringUtil.replace(apiId, ".", "_");
						//call type
						String returnType = "Object";
						if ("HEAD".equalsIgnoreCase(httpMethod)) {
							returnType = "Void";
						}
						String tinyCode = "\tpublic Call<" + returnType + "> " + apiMethod + "(";
						String code = "";
						code += "\t@" + httpMethod + "(\"" + path + "\")\n";
						code += "\tpublic Call<" + returnType + "> " + apiMethod + "(";
						if (parts != null) {
							for (Object partObj : parts.keySet()) {
								String part = partObj.toString();
								if (path.indexOf("{" + part + "}") >= 0) {
									JsonObjGetter partInfo = new JsonObjGetter(parts.get(part));
									code += "@Path(\"" + part + "\") " + javaType(partInfo.str("type")) + " " + part + ",";
									tinyCode += "" + javaType(partInfo.str("type")) + ",";
								}
							}
						}

						if ("POST".equals(httpMethod) || "PUT".equals(httpMethod)) {
							code += "@Body Object body,";
							tinyCode += "Object,";
						}

						//check duplicate java method
						Integer count = apiMethods.get(tinyCode);
						if (count != null && count.intValue() > 0) {
							count = count.intValue() + 1;
							apiMethods.put(tinyCode, count);
							code = StringUtil.replace(code, apiMethod, apiMethod + count);
							tinyCode = StringUtil.replace(tinyCode, apiMethod, apiMethod + count);

						} else {
							apiMethods.put(tinyCode, 1);
						}

						//fix part params order

						if (code.indexOf("@Path(\"id\") String id,@Path(\"index\") String index,@Path(\"type\") String type") >= 0) {
							code = StringUtil.replace(code, "@Path(\"id\") String id,@Path(\"index\") String index,@Path(\"type\") String type", "@Path(\"index\") String index, @Path(\"type\") String type, @Path(\"id\") String id");
						}
						if (code.endsWith(",")) {
							code = code.substring(0, code.length() - 1);
						}
						code += ");";
						appendCode(code);
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		EsRestSpecGen4Retrofit parser = new EsRestSpecGen4Retrofit();
		String specHome = ".\\api\\";
		Iterator<File> iterator = FindFileUtil.search(true, false, specHome);
		while (iterator.hasNext()) {
			File f = iterator.next();
			if (f.getName().endsWith(".json")) {
				try {
					String jsoncode = FileUtil.readUTFString(f);
					parser.parseCode(jsoncode);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		parser.writeToFile("..\\wES-client\\src\\main\\java\\org\\DataSays\\wES\\client\\EsService.java");
	}
}
