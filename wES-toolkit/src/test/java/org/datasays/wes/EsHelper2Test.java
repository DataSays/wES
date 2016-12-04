package org.datasays.wes;

import org.datasays.util.JsonObjGetter;
import org.datasays.util.text.TextUtils;
import org.datasays.wes.vo.Query;
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

public class EsHelper2Test {
	private static Logger LOG = LoggerFactory.getLogger(EsHelper2Test.class);
	private static EsHelper2 helper = null;
	private String index = "wes";
	private String type = "TestDoc";

	@Before
	public void setUp() throws Exception {
		helper = new EsHelper2();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIndex() {
		try {
			helper.delIndex(index);

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
			helper.createIndex(index, 3, 3);

			//index all doc
			for (TestDoc d : allData) {
				helper.save(d);
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
			helper.deleteByQuery(index, type, query);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			fail();
		}
	}

	@Test
	public void testAll() {
		try {
			//create index
			if (helper.hasIndex(index)) {
				helper.delIndex(index);
				assertFalse(helper.hasIndex(index));
			}
			helper.createIndex(index, 3, 3);
			assertTrue(helper.hasIndex(index));
			//index a doc
			TestDoc testDoc = new TestDoc(index, type);
			Date time = TextUtils.parseDate("2016-11-21 19:47:23", "yyyy-MM-dd HH:mm:ss");
			testDoc.tdate = time;
			testDoc.obj = new TestDoc(index, type);
			testDoc = helper.save(testDoc);

			JsonObjGetter result = null;
			assertNotNull(testDoc);
			assertEquals(index, testDoc.getIndex());
			assertEquals(type, testDoc.getType());
			String id = testDoc.getId();
			assertNotNull(id);
			//get doc
			TestDoc testDoc2 = helper.get(testDoc);
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
			testDoc2 = helper.get(testDoc);
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

			testDoc2 = helper.save(testDoc);
			assertNotNull(testDoc2);
			testDoc2 = helper.get(testDoc);
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

			//remove doc
			helper.delete(testDoc);
			//get index mapping
			result = new JsonObjGetter(helper.getMapping(index, type));
			assertNotNull(result);
			//put mapping
			result = new JsonObjGetter(helper.putMapping(index, type, result.obj("wes_test").map("mappings")));
			assertNotNull(result);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			fail();
		}
	}
}
