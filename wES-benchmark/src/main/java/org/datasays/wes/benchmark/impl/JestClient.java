package org.datasays.wes.benchmark.impl;

import io.searchbox.action.Action;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.datasays.wes.benchmark.IESClient;
import org.datasays.wes.core.IConvert;
import org.datasays.wes.toolkit.WGsonConvert;
import org.datasays.wes.vo.IEsItem;
import org.datasays.wes.vo.Query;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WEsDoc;
import org.datasays.wes.vo.WSearchResult;
import org.datasays.wes.vo.WSearchResultHits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by watano on 2016/12/1.
 */
public class JestClient implements IESClient {
	private io.searchbox.client.JestClient client = null;
	private IConvert convert = new WGsonConvert();

	@Override
	public void init() throws Exception {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig
				.Builder("http://127.0.0.1:9200")
				//.multiThreaded(true)
				.build());
		client = factory.getObject();
	}

	@Override
	public void close() throws Exception {
		client.shutdownClient();
	}

	private <T extends JestResult> T _exec(Action<T> action) throws Exception {
		T result = client.execute(action);
		if (result != null && result.isSucceeded() && result.getErrorMessage() == null) {
			return result;
		}
		if (result != null && result.getErrorMessage() != null) {
			throw new Exception(result.getErrorMessage());
		}
		return null;
	}

	@Override
	public boolean createIndex(String index) throws Exception {
		JestResult result = _exec(new CreateIndex.Builder(index).build());
		return result != null;
	}

	@Override
	public boolean delIndex(String index) throws Exception {
		try {
			JestResult result = _exec(new DeleteIndex.Builder(index).build());
			return result != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public IEsItem saveIndex(IEsItem doc) throws Exception {
		Index.Builder builder = new Index.Builder(doc);
		if (doc.getIndex() != null) {
			builder.index(doc.getIndex());
		}
		if (doc.getType() != null) {
			builder.type(doc.getType());
		}
		if (doc.getId() != null) {
			builder.id(doc.getId());
		}

		DocumentResult result = _exec(builder.build());
		if (result != null) {
			doc.setIndex(result.getIndex());
			doc.setType(result.getType());
			doc.setId(result.getId());
			return doc;
		}
		return null;
	}

	@Override
	public <T> WSearchResult<T> searchObj(String index, String type, SearchQuery query, Class<T> cls) throws
			Exception {
		Search.Builder builder = new Search.Builder(convert.toText(query));
		if (index != null) {
			builder.addIndex(index);
		}
		if (type != null) {
			builder.addType(type);
		}
		SearchResult result = _exec(builder.build());
		if (result != null) {
			WSearchResult<T> wresult = new WSearchResult<>();
			WSearchResultHits<T> hits = new WSearchResultHits<>();
			hits.setTotal(result.getTotal());
			List<SearchResult.Hit<T, Void>> allHist = result.getHits(cls);
			List<WEsDoc<T>> data = new ArrayList<>();
			for (SearchResult.Hit<T, Void> hit : allHist) {
				WEsDoc<T> doc = new WEsDoc<>();
				doc.setIndex(hit.index);
				doc.setType(hit.type);
				doc.setIndex(hit.index);
				doc.setSource(hit.source);
				doc.setScore(hit.score);
				data.add(doc);
			}
			hits.setHits(data);
			wresult.setHits(hits);
			return wresult;
		}
		return null;
	}

	@Override
	public IEsItem get(IEsItem doc) throws Exception {
		Get.Builder builder = new Get.Builder(doc.getIndex(), doc.getId());
		if (doc.getType() != null) {
			builder.type(doc.getType());
		}
		DocumentResult result = _exec(builder.build());
		if (result != null) {
			return result.getSourceAsObject(doc.getClass());
		}
		return null;
	}

	@Override
	public boolean delete(IEsItem doc) throws Exception {
		Delete.Builder builder = new Delete.Builder(doc.getId());
		if (doc.getIndex() != null) {
			builder.index(doc.getIndex());
		}
		if (doc.getType() != null) {
			builder.type(doc.getType());
		}
		DocumentResult result = _exec(builder.build());
		if (result != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteByQuery(String index, String type, Query query) throws Exception {
		org.datasays.wes.benchmark.impl.jest.DeleteByQuery.Builder builder = new org.datasays.wes.benchmark.impl.jest.DeleteByQuery.Builder(convert.toText(query));
		if (index != null) {
			builder.addIndex(index);
		}
		if (type != null) {
			builder.addType(type);
		}
		JestResult result = _exec(builder.build());
		if (result != null) {
			return true;
		}
		return false;
	}
}
