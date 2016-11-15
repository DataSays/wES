package org.datasays.es2

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.junit.Assert.*

public class EsHelper2Test {
	val static Logger LOG = LoggerFactory.getLogger(EsHelper2Test);
	var static EsHelper2 helper = null;

	@Before
	def void setUp() throws Exception {
		helper = EsHelper2.getInstance()
	}

	@After
	def void tearDown() throws Exception {
	}
	
	@Test
	def void testGet() {
		try {
			var esObj = helper.get('nongex', "BaseEsProduct", "xxx");
			assertNull(esObj);
			esObj = helper.get('nongex', "BaseEsProduct", "AVXj4htw848oDhU8Et2v");
			assertNotNull(esObj);
		} catch(Exception e) {
			LOG.error(e.message, e)
			fail(e.message);
		}
	}
	
	@Test
	def void testSearch() {
		try {
			var result = helper.search2('nongex', "BaseEsProduct", new SearchQuery2);
			while(result.hasNext) {
				var hit = result.next;
				assertNotNull(hit);
				LOG.info(hit.source.map.toString);
			}
		} catch(Exception e) {
			LOG.error(e.message, e);
			fail(e.message);
		}
	}
	
}
