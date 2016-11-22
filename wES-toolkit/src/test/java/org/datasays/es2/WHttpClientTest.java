package org.datasays.es2;

import org.datasays.es2.actions.Search;
import org.datasays.es2.vo.SearchQuery;
import org.datasays.util.http.HttpClientBuilder;
import org.datasays.util.http.HttpException;
import org.datasays.util.http.WHttpClient;
import org.datasays.util.text.WGsonConvert;
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
        HttpClientBuilder cbulder = new HttpClientBuilder();
        cbulder.addBaseAuth(null, null);
        service = new WHttpClient(cbulder.build(), new WGsonConvert());
        service.logRequestBody = true;
        service.logResponeBody = true;
        service.logUrl = true;
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
