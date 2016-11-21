package org.datasays.es2;

import java.util.Hashtable;
import java.util.Map;

import org.datasays.util.JsonObjGetter;
import org.datasays.util.collection.StrObjMap;
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
    public void testGet() {
        try {
            Object esObj = helper.get("nongex", "BaseEsProduct", "xxx");
            assertNull(esObj);
            esObj = helper.get("nongex", "BaseEsProduct", "AVXj4htw848oDhU8Et2v");
            assertNotNull(esObj);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    public void testAll() {
        try {
            Object result = helper.getMapping("nongex", "BaseEsProductItem");
            JsonObjGetter BaseEsProductItem = new JsonObjGetter(result).obj("nongex").obj("mappings").obj("BaseEsProductItem");
            LOG.info(BaseEsProductItem.map("properties").toString());

            JsonObjGetter properties = BaseEsProductItem.obj("properties");
            Assert.assertEquals("not_analyzed", properties.obj("categories").str("index"));

            LOG.info(properties.obj("categories").str("index"));

            Map<Object, Object> mappings = new Hashtable<>();
            mappings.putAll(BaseEsProductItem.map("properties"));
            mappings.put("categories", new StrObjMap("type", "string", "analyzer", "simple"));
            helper.putMapping("nongex", "BaseEsProductItem", (Object) mappings);


            result = helper.getMapping("nongex", "BaseEsProductItem");
            BaseEsProductItem = new JsonObjGetter(result).obj("nongex").obj("mappings").obj("BaseEsProductItem");
            properties = BaseEsProductItem.obj("properties");
            Assert.assertEquals("simple", properties.obj("categories").str("analyzer"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
