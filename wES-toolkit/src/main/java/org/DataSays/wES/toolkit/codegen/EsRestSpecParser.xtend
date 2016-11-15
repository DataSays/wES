package org.datasays.wes.toolkit.codegen;

import com.google.gson.Gson;
import java.io.File
import java.util.Hashtable
import java.util.Iterator
import jodd.io.FileUtil
import jodd.util.StringUtil
import org.datasays.util.JsonObjGetter
import org.datasays.util.FindFileUtil

public class EsRestSpecParser {
//	private static val Logger LOG = LoggerFactory.getLogger(EsRestSpecParser);
	private static val gson = new Gson();
	private var codes = new StringBuffer();

	def void appendCode(String line) {
		codes.append(line);
		codes.append('\n');
	}

	def static String javaType(String type) {
		if('string'.equalsIgnoreCase(type)) {
			return 'String';
		} else if('list'.equalsIgnoreCase(type)) {
			return 'String';
		} else {
			return type;
		}
	}

	def void writeToFile(String file) {
		FileUtil.writeString(file, '''
			package org.DataSays.wES.client;
			
			import retrofit2.Call;
			import retrofit2.http.Body;
			import retrofit2.http.DELETE;
			import retrofit2.http.GET;
			import retrofit2.http.HEAD;
			import retrofit2.http.POST;
			import retrofit2.http.PUT;
			import retrofit2.http.Path;
			
			public interface EsService {
			«codes.toString»
			
			}
		''', 'utf-8');
	}

	def void parseCode(String jsoncode) {
		var jsonobj = new JsonObjGetter(gson.fromJson(jsoncode, Object));
		if(jsonobj != null && jsonobj.map != null) {
			jsonobj.map.forEach [ apiName, apiValue, apiIndex |
				{
					var apiId = apiName.toString;
					var apiInfo = new JsonObjGetter(apiValue);
					// documentation
					appendCode('''
					/**
					* documentation: «apiInfo.str('documentation')»''');
					// param
					if(apiInfo.obj('url').map('params') != null) {
						apiInfo.obj('url').map('params').forEach [ paramName, paramValue, paramIndex |
							{
								var paramInfo = new JsonObjGetter(paramValue);
								appendCode('''* param: «paramInfo.str('type')» «paramName»: «paramInfo.str('description')»''');
							}
						];
					}
					// body
					var bodyRequired = false;
					if(apiInfo.obj('body') != null) {
						bodyRequired = apiInfo.obj('body').bool('required') ?: false;
						appendCode('''* body«IF bodyRequired»*«ENDIF»:«apiInfo.obj('body').str('description')?:''»''');
					}
					appendCode('');
					// parts
					var parts = apiInfo.obj('url').map('parts');
					if(parts != null) {
						parts.forEach [ partName, partValue, partIndex |
							{
								var partInfo = new JsonObjGetter(partValue);
								appendCode('''* @param «partInfo.str('type')» «partName»: «partInfo.str('description')»''');
							}
						];
					}
					appendCode('''*/''');

					// http method
					var methods = apiInfo.list('methods');
					var apiMethods = new Hashtable<String, Integer>();
					for (Object m : methods) {
						var httpMethod = m.toString.toUpperCase;
						// url path
						var paths = apiInfo.obj('url').list('paths');
						for (var int i = 0; i < paths.size; i++) {
							var path = paths.get(i).toString;
							if(path.startsWith('/')) {
								path = path.substring(1);
							}
							var apiMethod = StringUtil.replace(apiId, '.', '_');
							if('indices_put_mapping'.equals(apiMethod)){
								
								println('');
							}
							//call type
							var returnType = 'Object';
							if('HEAD'.equalsIgnoreCase(httpMethod)){
								returnType = 'Void';
							}
							var tinyCode = '''public Call<«returnType»> «apiMethod»(''';
							var code = '''
							@«httpMethod»("«path»")
							public Call<«returnType»> «apiMethod»(''';
							if(parts != null) {
								for (Object partObj : parts.keySet) {
									var part = partObj.toString;
									if(path.indexOf('{' + part + '}') >= 0) {
										var partInfo = new JsonObjGetter(parts.get(part));
										code += '''@Path("«part»") «javaType(partInfo.str('type'))» «part»,''';
										tinyCode += '''«javaType(partInfo.str('type'))»,''';
									}
								}
							}
							
							if(#['POST', 'PUT'].contains(httpMethod)) {
								code += '''@Body Object body,''';
								tinyCode += '''Object,''';
							}
							
							//check duplicate java method
							var count = apiMethods.get(tinyCode);
							if(count != null && count.intValue > 0) {
								count = count.intValue+1;
								apiMethods.put(tinyCode, count);	
								code = StringUtil.replace(code, apiMethod, apiMethod + count);
								tinyCode = StringUtil.replace(tinyCode, apiMethod, apiMethod + count);
								
							}else{
								apiMethods.put(tinyCode, 1);
							}

							//fix part params order
			
							if(code.indexOf('''@Path("id") String id,@Path("index") String index,@Path("type") String type''')>=0){
								code = StringUtil.replace(code, 
									'''@Path("id") String id,@Path("index") String index,@Path("type") String type''', 
									'''@Path("index") String index, @Path("type") String type, @Path("id") String id''');								
							}
							if(code.endsWith(',')) {
								code = code.substring(0, code.length - 1);
							}
							code += ''');''';
							appendCode(code);
						}
					}
				}
			];
		}
	}

	def static void main(String[] args) {
		var parser = new EsRestSpecParser();
		var specHome = '''.\api\''';
		var Iterator<File> iterator = FindFileUtil.search(true, false, specHome);
		while(iterator.hasNext) {
			var f = iterator.next;
			if(f.name.endsWith('.json')) {
				var jsoncode = FileUtil.readUTFString(f);
				parser.parseCode(jsoncode);
			}
		}
		parser.writeToFile('''..\wES-client\src\main\java\org\DataSays\wES\client\EsService.java''');
	}
}
