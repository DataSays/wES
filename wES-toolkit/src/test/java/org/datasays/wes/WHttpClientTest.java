package org.datasays.wes;

import okhttp3.OkHttpClient;
import org.datasays.wes.actions.Search;
import org.datasays.wes.core.HttpException;
import org.datasays.wes.core.WHttpClient;
import org.datasays.wes.toolkit.WGsonConvert;
import org.datasays.wes.vo.SearchQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by watano on 2016/11/21.
 */
public class WHttpClientTest {
	private static Logger LOG = LoggerFactory.getLogger(WHttpClientTest.class);
	private static WHttpClient service = null;

	@Before
	public void setUp() throws Exception {
		service = new WHttpClient(new OkHttpClient.Builder().build(), new WGsonConvert());
		service.setLogFlag(true, true, true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSearchRequest() {
		try {
			String server = "http://127.0.0.1:9200";
			Search searchRequest = new Search(server).setParts("wes_test", null);

			searchRequest.setBody(SearchQuery.MatchAll());
			TestDoc testDoc = service.post(searchRequest, TestDoc.class);
		} catch (HttpException e) {
			e.printStackTrace();
		}
	}
}
