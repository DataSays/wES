package org.datasays.es2;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import jodd.typeconverter.Convert;
import org.datasays.es2.vo.WEsDoc;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;
import org.datasays.util.collection.StrObjMap;
import org.datasays.util.text.TextUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class EsHelper2Test {
    private static Logger LOG = LoggerFactory.getLogger(EsHelper2Test.class);
    private static EsHelper2 helper = null;

    @Before
    public void setUp() throws Exception {
        helper = new EsHelper2();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAll() {
        String index = "wes_test";
        try {
            //create index
            if (helper.hasIndex(index)) {
                helper.delIndex(index);
            }
            helper.createIndex(index, 3, 3);
            //index a doc
            String type = "TestDoc";
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
            result = helper.delete(testDoc);
            assertNotNull(result);
            //get index mapping
            result = new JsonObjGetter(helper.getMapping(index, type));
            assertNotNull(result);
            //put mapping
            result = new JsonObjGetter(helper.putMapping(index, type, result.obj("wes_test").map("mappings")));
            assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
