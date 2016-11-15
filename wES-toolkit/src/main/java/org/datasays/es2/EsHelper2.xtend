package org.datasays.es2;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import jodd.util.StringUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import org.datasays.util.RetrofitHelper;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;
import org.datasays.util.WPageIterator;
import org.datasays.wes.client.EsService

public class EsHelper2 {
	private static val Logger LOG = LoggerFactory.getLogger(EsHelper2);
	private static val String server = 'http://127.0.0.1:9200';
	private static val String user = null;
	private static val String pswd = null;
	private static var EsHelper2 _instance = null;
	private static var EsService esService = null;

	public static def EsHelper2 getInstance() {
		if(_instance == null) {
			_instance = new EsHelper2();
			_instance.init();
		}
		return _instance;
	}

	private new() {
	}

	def void init() {
		if(esService == null) {
			esService = RetrofitHelper.create(server, user, pswd).build.create(EsService);
			LOG.debug('init EsHelper2');
		}
	}
	
	def Object getMapping(String index) throws Exception{
		return execute(esService.indices_get_mapping(index));
	}
	
	def Object getMapping(String index, String type) throws Exception{
		return execute(esService.indices_get_mapping(index, type));
	}
	
	def Object putMapping(String index, String type, Object mapping) throws Exception{
		return execute(esService.indices_put_mapping(index, type, mapping));
	}
	
	def Object getIndex(String index) throws Exception{
		return execute(esService.indices_get(index));
	}
	
	def Object putIndexSettings(String index, Object settings) throws Exception{
		return execute(esService.indices_put_settings(index, settings));
	}
	
	def Object index(String index, String type, Object body) throws Exception{
		return execute(esService.index(index, type, body));
	}
	
	def Object index(String index, String type, String id, Object body) throws Exception{
		return execute(esService.index(index, type, id, body));
	}
	
	def Object get(String index, String type, String id) throws Exception{
		return get(esService.get(index, type, id));
	}
	
	def Object delIndex(String index) throws Exception{
		return execute(esService.indices_delete(index));
	}
	
	def Object createIndex(String index) throws Exception{
		return execute(esService.indices_create(index, #{}));
	}
	
	def Object openIndex(String index) throws Exception{
		return execute(esService.indices_open(index, #{}));
	}
	
	def Object closeIndex(String index) throws Exception{
		return execute(esService.indices_close(index, #{}));
	}
	
	def Object syncedFlushIndex(String index) throws Exception{
		return execute(esService.indices_flush_synced(index));
	}
	
	def Object flushIndex(String index) throws Exception{
		return execute(esService.indices_flush(index));
	}
	
	def WSearchResult2 search(String index, SearchQuery2 query) throws Exception{
		return search(index, null, query);
	}
	
	def WSearchResult2 search(String index, String type, SearchQuery2 query) throws Exception{
		query.total = null;
		var WSearchResult2 result = null;
		if(StringUtil.isNotBlank(index) && StringUtil.isNotBlank(type)){
			result = new WSearchResult2(execute(esService.search(index, type, query)), query);
		}else if(StringUtil.isNotBlank(index)){
			result = new WSearchResult2(execute(esService.search(index, query)), query);
		}
		return result;
	}
	
	def WPageIterator<WSearchResultHit2> search2(String index, String type, SearchQuery2 query){
		var result = new WPageIterator<WSearchResultHit2>(){
			override void doSearch() {
				var WSearchResult2 result2 = search(index, type, query);
				update(result2.getHits(false), result2.total);
			}
		};
		result.reset(query);//fix bugs for gradle xtend bug. :(
		return result;
	}
	
	def Object addIndexAliases(String index, String alias) throws Exception{
		return execute(esService.indices_put_alias(index, alias, #{}));
	}
	
	def Object delIndexAliases(String index, String alias) throws Exception{
		return execute(esService.indices_delete_alias(index, alias));
	}
	
	def boolean hasIndex(String index) throws Exception{
		return has(esService.indices_exists(index));
	}
	
	def Set<String> getIndexTypes(String index) throws Exception{
		var result = new JsonObjGetter(getMapping(index));
		var Set<String> types = new HashSet<String>();
		for(Object o:result.obj(index).map('mappings').keySet){
			types.add(o as String);
		}
		return types;
	}	
	
	private def <T> T execute(Call<T> call){
		var Response<T> response = call.execute();
		if (response.isSuccessful()) {
			return response.body();
		} else {
			handleError(response);
			return null;
		}
	}
	
	private def <T> T get(Call<T> call){
		var Response<T> response = call.execute();
		if (response.isSuccessful()) {
			return response.body();
		} else if (response.code() == 404) {
			return null;
		} else {
			handleError(response);
			return null;
		}
	}
	
	private def <T> boolean has(Call<T> call) throws Exception {
		var Response<T> response = call.execute();
		if (response.isSuccessful()) {
			return true;
		} else if (response.code() == 404) {
			return false;
		} else {
			handleError(response);
			return false;
		}
	}

	private def void handleError(Response<?> response) throws Exception {
		LOG.debug("code:" + response.code());
		LOG.debug("message:" + response.message());
		var String errorJson = response.errorBody().string();
		LOG.error(errorJson);
		var error = new JsonObjGetter(WJsonUtils.fromJson(errorJson, Object));
		if(error.obj('error') != null){
			error = error.obj('error');
			LOG.debug(error.str('type') + "\n" + error.str('reason'));
			throw new Exception(error.str('type') + "\n" + error.str('reason'));
		}else{
			throw new Exception(errorJson);
		}
	}
	
	def static void main(String[] args) {
		var esHelper = EsHelper2.instance;
		var result = esHelper.getMapping('nongex','BaseEsProductItem');
		var BaseEsProductItem = new JsonObjGetter(result).obj('nongex').obj('mappings').obj('BaseEsProductItem');
		LOG.info(BaseEsProductItem.map('properties').toString);
		
		var properties = BaseEsProductItem.obj('properties');
		Assert.assertEquals('not_analyzed', properties.obj('categories').str('index'));
		
		LOG.info(properties.obj('categories').str('index'));
		
		var mappings = new Hashtable();
		mappings.putAll(BaseEsProductItem.map('properties'))
		mappings.put('categories', #{'type'->'string','analyzer'->'simple'});
		esHelper.putMapping('nongex','BaseEsProductItem', mappings);
		
		result = esHelper.getMapping('nongex','BaseEsProductItem');
		BaseEsProductItem = new JsonObjGetter(result).obj('nongex').obj('mappings').obj('BaseEsProductItem');
		properties = BaseEsProductItem.obj('properties');
		Assert.assertEquals('simple', properties.obj('categories').str('analyzer'));
	}
}
