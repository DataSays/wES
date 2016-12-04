package org.datasays.wes.toolkit.codegen;

import jodd.io.FileUtil;
import jodd.util.StringUtil;
import org.datasays.util.FindFileUtil;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;
import org.datasays.util.collection.StrMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EsRestSpecGen {
	private static Logger LOG = LoggerFactory.getLogger(EsRestSpecGen.class);
	private StringBuilder codes = new StringBuilder();
	private String clsName = null;
	private String sourceDir = null;
	private String pkg = null;

	public void appendCode(String line) {
		codes.append(line);
		codes.append("\n");
	}

	public static String javaType(String type) {
		if ("string".equalsIgnoreCase(type)) {
			return "String";
		} else if ("time".equalsIgnoreCase(type)) {
			return "long";
		} else if ("number".equalsIgnoreCase(type)) {
			return "Number";
		} else if ("integer".equalsIgnoreCase(type)) {
			return "Integer";
		} else if ("list".equalsIgnoreCase(type)) {
			return "String";
		} else {
			return type;
		}
	}

	public void writeJavaFile() {
		try {
			String pkg2 = pkg + ".actions";
			StringBuilder javaCodes = new StringBuilder();
			javaCodes.append("package " + pkg2 + ";\n\n");

			javaCodes.append("import okhttp3.HttpUrl;\n" +
					"import org.datasays.wes.core.RequestInfo;\n" +
					"import org.datasays.wes.types.*;\n");
			javaCodes.append(codes.toString());
			javaCodes.append("}\n");
			String filePath = sourceDir + pkg2.replace('.', File.separatorChar) + File.separatorChar;
			FileUtil.mkdirs(filePath);
			FileUtil.writeString(filePath + clsName + ".java", javaCodes.toString(), "utf-8");
			codes = new StringBuilder();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeEnumType(String type, List<String> options, String defaultValue, String description) {
		try {
			String pkg2 = pkg + ".types";
			StringBuilder javaCodes = new StringBuilder();
			javaCodes.append("package " + pkg2 + ";\n\n");

			javaCodes.append("//" + description + "\n");
			javaCodes.append("//default: " + defaultValue + "\n");
			javaCodes.append("public enum " + type + " {\n");
			String enumCodes = "";
			for (String option : options) {
				String enumName = option.toUpperCase();
				if (option.trim().length() <= 0) {
					enumName = "v";
				}
				enumCodes += "\t" + enumName + "(\"" + option + "\"),\n";
			}
			enumCodes = StringUtil.cutSuffix(enumCodes, ",\n");
			javaCodes.append(enumCodes + ";\n");

			javaCodes.append("\tprivate String name;\n");
			javaCodes.append("\t" + type + "(String name) {\n");
			javaCodes.append("\t\tthis.name = name;\n");
			javaCodes.append("\t}\n\n");
			javaCodes.append("\t@Override\n");
			javaCodes.append("\tpublic String toString() {\n");
			javaCodes.append("\treturn this.name;\n");
			javaCodes.append("\t}\n");
			javaCodes.append("}");
			String filePath = sourceDir + pkg2.replace('.', File.separatorChar) + File.separatorChar;
			FileUtil.mkdirs(filePath);
			FileUtil.writeString(filePath + type + ".java", javaCodes.toString(), "utf-8");
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
				clsName = StringUtil.replace(apiName, "_", ".");
				clsName = StringUtil.toCamelCase(clsName, true, '.');
				JsonObjGetter apiInfo = new JsonObjGetter(v);

				// documentation
				appendCode("/**");
				appendCode("* documentation: " + apiInfo.str("documentation"));
				appendCode("**/");
				appendCode("public class " + clsName + " extends RequestInfo{");
				appendCode("");

				appendCode("\tpublic " + clsName + "(String baseUrl){");
				appendCode("\t\tsuper(baseUrl);");
				appendCode("\t}");
				appendCode("\tpublic " + clsName + "(HttpUrl baseUrl){");
				appendCode("\t\tsuper(baseUrl);");
				appendCode("\t}");
				appendCode("");

				Map<?, ?> params = apiInfo.obj("url").map("params");
				if (params != null) {
					params.forEach((k2, paramValue) -> {
						String paramName = k2.toString();
						paramName = StringUtil.replace(paramName, "_", ".");
						paramName = StringUtil.toCamelCase(paramName, false, '.');
						paramName = StringUtil.uncapitalize(paramName);
						JsonObjGetter paramInfo = new JsonObjGetter(paramValue);
						String paramType = paramInfo.str("type");
						if (paramType != null) {

							if ("enum".equals(paramType)) {
								paramType = "Enum" + StringUtil.capitalize(paramName);
								List<String> options = (List<String>) paramInfo.list("options");
								writeEnumType(paramType, options, paramInfo.str("default"), paramInfo.str("description"));
							} else {
								paramType = javaType(paramType);
							}
							appendCode("\t/** param: " + paramInfo.str("type") + " " + paramName + ": " + paramInfo.str("description") + "**/");
							appendCode("\tpublic " + clsName + " " + paramName + "(" + paramType + " " + paramName + "){");
							appendCode("\t\taddParams(\"" + paramName + "\", " + paramName + ");");
							appendCode("\t\treturn this;");
							appendCode("\t}");
						} else {
							LOG.warn(apiName + "-" + k2.toString() + " don't have a type!");
						}
					});
				}
				// body
				boolean bodyRequired = false;
				if (apiInfo.obj("body") != null) {
					if (apiInfo.obj("body").bool("required") != null && apiInfo.obj("body").bool("required")) {
						bodyRequired = true;
					}
					appendCode("\t/** body" + (bodyRequired ? "*" : "") + ":" + apiInfo.obj("body").str("description") + "**/");
					appendCode("\t@Override");
					appendCode("\tpublic void setBody(Object body) {");
					appendCode("\t\tsuper.setBody(body);");
					appendCode("\t}");
				}
				appendCode("");

				// parts
				Map<?, ?> parts = apiInfo.obj("url").map("parts");
				String partDefCodes = "";
				StrMap partDefs = new StrMap();
				if (parts != null) {
					parts.forEach((k3, partValue) -> {
						String partName = k3.toString();
						JsonObjGetter partInfo = new JsonObjGetter(partValue);
						String partType = javaType(partInfo.str("type"));
						partDefs.put(partName, partType);
						appendCode("\t/**" + partInfo.str("description") + "**/");
						appendCode("\tprivate " + partType + " " + partName + ";");
					});
				}
				String partDefArgs = "";
				String partDefSetting = "";
				if (partDefs.containsKey("index")) {
					partDefArgs += "String index,";
				}
				if (partDefs.containsKey("type")) {
					partDefArgs += "String type,";
				}
				if (partDefs.containsKey("id")) {
					partDefArgs += "String id,";
				}
				for (String partName : partDefs.keySet()) {
					String partType = partDefs.get(partName);
					partDefSetting += "\t\tthis." + partName + "=" + partName + ";\n";
					if ("index".equals(partName) || "type".equals(partName) || "id".equals(partName)) {
						continue;
					}
					partDefArgs += javaType(partType) + " " + partName + ",";
				}
				partDefArgs = StringUtil.cutSuffix(partDefArgs, ",");

				appendCode("\tpublic " + clsName + " setParts(" + partDefArgs + "){");
				appendCode(partDefSetting);
				appendCode("\t\treturn this;");
				appendCode("\t}");
				appendCode("");

				// http method and paths
				String methodsFilter = "";
				List<String> methods = (List<String>) apiInfo.list("methods");
				for (String method : methods) {
					methodsFilter += "!\"" + method + "\".equalsIgnoreCase(method) && ";
				}
				methodsFilter = StringUtil.cutSuffix(methodsFilter, " && ");
				List<String> paths = (List<String>) apiInfo.obj("url").list("paths");
				String parseUrlCodes = "";
				List<String> pathIds = new ArrayList<String>();
				for (String path : paths) {
					int count = 90;
					String defs = "";
					for (String part : partDefs.keySet()) {
						if (path.contains("{" + part + "}")) {
							defs += part + ";";
							count--;
						}
					}
					pathIds.add(count + ":" + path);
				}
				Collections.sort(pathIds);
				int count2 = 0;
				for (String pathId : pathIds) {
					int index = pathId.indexOf(":");
					int count = Integer.parseInt(pathId.substring(0, index));
					String pathTmp = pathId.substring(index + 1);
					String partCnds = "";
					for (String part : partDefs.keySet()) {
						if (pathTmp.contains("{" + part + "}")) {
							partCnds += part + " != null && ";
						}
					}

					String partUrl = "";
					for (String p : StringUtil.split(pathTmp, "/")) {
						if (p.startsWith("{") && p.endsWith("}")) {
							partUrl += p.substring(1, p.length() - 1) + ", ";
						} else {
							partUrl += "\"" + p + "\", ";
						}
					}

					partCnds = StringUtil.cutSuffix(partCnds, "&& ");
					partUrl = StringUtil.cutSuffix(partUrl, ", ");
					partUrl = StringUtil.replace(partUrl, "\"\", ", "");
					parseUrlCodes += "\t\t//=>" + pathTmp + "\n";
					if (partCnds.trim().length() > 0) {
						parseUrlCodes += "\t\tif(" + partCnds + "){\n";
						parseUrlCodes += "\t\t\tsetUrl(" + partUrl + ");\n";
						parseUrlCodes += "\t\t\treturn super.parseUrl(method);\n";
						parseUrlCodes += "\t\t}\n";
					} else if (count2 <= 0) {
						parseUrlCodes += "\t\tsetUrl(" + partUrl + ");\n";
						parseUrlCodes += "\t\treturn super.parseUrl(method);\n";
						count2++;
					}
				}

				appendCode("\t@Override");
				appendCode("\tpublic String parseUrl(String method) {");
				appendCode("\t\tif(" + methodsFilter + "){");
				appendCode("\t\t\tthrow new IllegalArgumentException(\"Unsupported method:\"+method);");
				appendCode("\t\t}");
				appendCode(parseUrlCodes);
				if (count2 <= 0) {
					appendCode("\t\treturn null;");
				}
				appendCode("\t}");
			});
		}
	}

	public static void main(String[] args) {
		EsRestSpecGen codeGen = new EsRestSpecGen();
		String specHome = ".\\api\\";
		codeGen.sourceDir = "..\\wES-client\\src\\main\\java\\";
		codeGen.pkg = "org.datasays.wes";
		Iterator<File> iterator = FindFileUtil.search(true, false, specHome);
		while (iterator.hasNext()) {
			File f = iterator.next();
			if (f.getName().endsWith(".json")) {
				try {
					String jsoncode = FileUtil.readUTFString(f);
					codeGen.parseCode(jsoncode);
					codeGen.writeJavaFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
