package org.datasays.util;

import jodd.io.FileUtil;
import jodd.util.StringUtil;
import org.datasays.util.text.CmdExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by watano on 2016/11/25.
 */
public class GradleGen extends CmdExecuter {
	private static Logger LOG = LoggerFactory.getLogger(GradleGen.class);
	private StringBuffer codes = new StringBuffer();
	private StringBuffer excodes = new StringBuffer();

	protected void appendCode(int index, String line) {
		codes.append(StringUtil.repeat('\t', index));
		codes.append(line);
		codes.append('\n');
	}

	@Override
	public void doAction(String action, String[] args) {
		if ("GenSubBuildGradle".equalsIgnoreCase(action.trim()) && args != null && args.length > 0) {
			if (args.length == 1) {
				genSubBuildGradle(args[0], "utf-8");
			} else if (args.length == 2) {
				genSubBuildGradle(args[0], args[1]);
			}
		} else if ("AddGradleJavaTask".equalsIgnoreCase(action.trim()) && args != null && args.length == 3) {
			addGradleJavaTask(args[0], args[1], args[2]);
		}
	}

	public void addGradleJavaTask(String name, String mainCls, String pwd) {
		excodes.append("task " + name + "(type: JavaExec, dependsOn: []) {\n");
		excodes.append("\tworkingDir = " + pwd + "\n");
		excodes.append("\tclasspath = sourceSets.main.runtimeClasspath\n");
		excodes.append("\tmain = '" + mainCls + "'\n");
		excodes.append("\targs = []\n");
		excodes.append("\tsystemProperties System.getProperties()\n");
		excodes.append("}\n\n");
	}

	public void genSubBuildGradle(String file, String encoding) {
		try {
			appendCode(0, "plugins {");
			for (String v : values("plugins")) {
				appendCode(1, "id '" + v + "\'");
			}
			appendCode(0, "}");
			appendCode(0, "");

			appendCode(0, "group = '" + value("group") + "'");
			appendCode(0, "version = '" + value("version") + "'");
			appendCode(0, "description = \"\"\"" + value("description") + "\"\"\"");
			appendCode(0, "archivesBaseName = '" + value("project") + "'");
			appendCode(0, "");

			if (StringUtil.isNotBlank(value("applyFrom"))) {
				appendCode(0, "apply from: '" + value("applyFrom") + "'");
			}
			appendCode(0, "dependencies {");
			for (String v : values("deps")) {
				appendCode(1, v);
			}
			appendCode(0, "}");
			appendCode(0, "");

			appendCode(0, "configurations {");
			appendCode(1, "published");
			appendCode(0, "}");
			appendCode(0, "");

//            appendCode(0, "publishing {");
//            appendCode(1, "publications {");
//            appendCode(2, "nebula(MavenPublication) {");
//            appendCode(2, "}");
//            appendCode(1, "}");
//            appendCode(0, "}");
//            appendCode(0, "");

			appendCode(0, excodes.toString());
			LOG.info(file);
			FileUtil.writeString(new File(file), codes.toString(), encoding);
			codes = new StringBuffer();
			excodes = new StringBuffer();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		String cmdFile = "gradle.txt";
		if (args != null && args.length > 0) {
			cmdFile = args[0];
		}
		new GradleGen().runFile(cmdFile);
	}
}
