package org.datasays.wes;

import org.datasays.util.JsonObjGetter;
import org.datasays.util.WCfg;
import org.datasays.util.text.TextUtils;
import org.datasays.wes.vo.Query;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WSearchResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EsBaseServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(EsBaseServiceTest.class);
	private static EsBaseService esService = null;
	private String index = "wes_test";
	private String type = "TestDoc";

	@Before
	public void setUp() throws Exception {
		esService = new EsBaseService();
		esService.init(WCfg.getValue("ES.server"), null, null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIndex() {
		try {
			esService.delIndex(index);

			//load test data
			List<TestDoc> allData = new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				TestDoc tdoc = new TestDoc(index, type);
				tdoc.tint = i;
				tdoc.tdouble = i * 0.2;
				tdoc.tfloat = i * 0.2f;
				tdoc.tbool = i % 2 == 0;
				tdoc.tdate = new Date();
				tdoc.tString = "abc中文测试乒乓球" + i * 0.2;
				tdoc.obj = new TestDoc(index, type);
				tdoc.obj.tint = i;
				tdoc.obj.tString = "父类" + i;
				allData.add(tdoc);
			}
			//create index
			esService.createIndex(index, 3, 3);

			//index all doc
			for (TestDoc d : allData) {
				esService.save(d);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			fail();
		}
	}

	@Test
	public void testDelByQuery() {
		try {
			Query query = Query.bool(Query.term("tbool", "false"), null, null);
			esService.deleteByQuery(index, type, query);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			fail();
		}
	}

	@Test
	public void testAll() {
		try {
			//create index
			if (esService.hasIndex(index)) {
				esService.delIndex(index);
				assertFalse(esService.hasIndex(index));
			}
			esService.createIndex(index, 3, 3);
			assertTrue(esService.hasIndex(index));
			//index a doc
			TestDoc testDoc = new TestDoc(index, type);
			Date time = TextUtils.parseDate("2016-11-21 19:47:23", "yyyy-MM-dd HH:mm:ss");
			testDoc.tdate = time;
			testDoc.obj = new TestDoc(index, type);
			testDoc = esService.save(testDoc);

			JsonObjGetter result = null;
			assertNotNull(testDoc);
			assertEquals(index, testDoc.getIndex());
			assertEquals(type, testDoc.getType());
			String id = testDoc.getId();
			assertNotNull(id);
			//get doc
			TestDoc testDoc2 = esService.get(testDoc);
			assertNotNull(testDoc2);
			assertNotEquals(testDoc, testDoc2);
			assertEquals(testDoc.getIndex(), testDoc2.getIndex());
			assertEquals(testDoc.getType(), testDoc2.getType());
			assertEquals(testDoc.getId(), testDoc2.getId());
			assertEquals(testDoc.tint, testDoc2.tint);
			assertEquals(testDoc.tdouble, testDoc2.tdouble, 2);
			assertEquals(testDoc.tfloat, testDoc2.tfloat, 2);
			assertEquals(testDoc.tbool, testDoc2.tbool);
			//assertEquals(time, testDoc2.tdate);
			assertEquals(testDoc.tString, testDoc2.tString);

			testDoc.setId("xxxxxx");
			testDoc2 = esService.get(testDoc);
			assertNull(testDoc2);

			//edit doc
			testDoc.tint = 2;
			testDoc.tdouble = 2.0;
			testDoc.tfloat = 2.0f;
			testDoc.tbool = true;
			testDoc.tdate = new Date(2015);
			time = TextUtils.parseDate("2015-11-21 19:47:23", "yyyy-MM-dd HH:mm:ss");
			testDoc.tdate = time;
			testDoc.tString = "abc中文测试乒乓球123";

			testDoc2 = esService.save(testDoc);
			assertNotNull(testDoc2);
			testDoc2 = esService.get(testDoc);
			assertNotNull(testDoc2);
			assertEquals(testDoc.getIndex(), testDoc2.getIndex());
			assertEquals(testDoc.getType(), testDoc2.getType());
			assertEquals(testDoc.getId(), testDoc2.getId());
			assertEquals(testDoc.tint, testDoc2.tint);
			assertEquals(testDoc.tdouble, testDoc2.tdouble, 2);
			assertEquals(testDoc.tfloat, testDoc2.tfloat, 2);
			assertEquals(testDoc.tbool, testDoc2.tbool);
			//assertEquals(time, testDoc2.tdate);
			assertEquals(testDoc.tString, testDoc2.tString);

			//search doc
			WSearchResult<Object> searchResult = esService.searchObj(index, type, SearchQuery.MatchAll("abc") ,Object.class);
			assertNotNull(searchResult);
			assertTrue(searchResult.getTotal() > 0);
			for(Object o: searchResult.getData()){
				assertNotNull(o);
				JsonObjGetter json = new JsonObjGetter(o);
				json = json.obj("_source");
				assertNotNull(json.str("tString"));
				assertTrue(json.str("tString").contains("abc"));
			}

			searchResult = esService.searchObj(index, type, SearchQuery.MatchAll("中文") ,Object.class);
			assertNotNull(searchResult);
			assertTrue(searchResult.getTotal() > 0);
			for(Object o: searchResult.getData()){
				assertNotNull(o);
				JsonObjGetter json = new JsonObjGetter(o);
				json = json.obj("_source");
				assertNotNull(json.str("tString"));
				assertTrue(json.str("tString").contains("中文"));
			}

			//remove doc
			esService.delete(testDoc);
			//get index mapping
			result = new JsonObjGetter(esService.getMapping(index, type));
			assertNotNull(result);
			//put mapping
			result = new JsonObjGetter(esService.putMapping(index, type, result.obj(index).map("mappings")));
			assertNotNull(result);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			fail();
		}
	}
}
