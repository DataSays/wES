package org.datasays.wes.demo.service;

import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WEsDoc;
import org.datasays.wes.vo.WSearchResult;
import org.datasays.wes.vo.WSearchResultHits;
import org.datasays.util.WPage;
import org.datasays.util.WPageIterator;
import org.datasays.wes.demo.vo.BaseEsProduct;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class ShopServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(ShopServiceTest.class);
    private ShopService shopService = null;

    @Before
    public void setUp() throws Exception {
        shopService = new ShopService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSearchProduct() {
        try {
            WSearchResult<BaseEsProduct> result = shopService.searchProduct(new SearchQuery());
            assertNotNull(result);
            assertNotNull(result.getHits());
            assertNotNull(result.getHits().getHits());
            assertTrue(result.getHits().getTotal() > 0);
            WSearchResultHits<BaseEsProduct> hits = (WSearchResultHits<BaseEsProduct>) result.getHits();
            assertTrue(hits.getHits()[0] instanceof WEsDoc);
            WEsDoc<BaseEsProduct> hit = (WEsDoc<BaseEsProduct>) hits.getHits()[0];
            assertNotNull(hit);
            assertEquals(hit.getIndex(), shopService.index);
            assertNotNull(hit.getSource());
            assertTrue(hit.getSource() instanceof BaseEsProduct);
            assertNotNull(hit.getSource().getIndex(), shopService.index);
            LOG.info(hit.getSource().getName());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSearchProductIterator() {
        try {
            WPage page = new WPage();
            SearchQuery queryDSL = new SearchQuery(page);
            WPageIterator<BaseEsProduct> result = shopService.searchProductIterator(queryDSL);
            assertNotNull(result);
            int index = 0;
            while (result.hasNext()) {
                BaseEsProduct vo = result.next();
                assertNotNull(result.getPage());
                assertTrue(result.getPage().getTotal() > 0);
                assertNotNull(vo.getIndex(), shopService.index);
                LOG.info(index + "--" + vo.getName() + "--" + vo.getId());
                if (++index >= 25)
                    break;
            }
            assertEquals(25, index);
            LOG.info(page.toText());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    //@Test
    public void testSearchProductItem() {
        //fail("Not yet implemented");
    }
}
