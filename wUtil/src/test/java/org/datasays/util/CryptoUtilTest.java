package org.datasays.util;

import static org.junit.Assert.assertNotNull;

import org.datasays.util.CryptoUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoUtilTest {
	private static final Logger LOG = LoggerFactory.getLogger(CryptoUtilTest.class);

	@Test
	public void testAll() {
		long starttime = System.currentTimeMillis();
		try {
			for (int i = 0; i < 10000; i++) {
				assertNotNull(CryptoUtil.encrypt(System.currentTimeMillis() + "4--]tFyI;hU!'ct^+c}swatano"));
			}
		} catch (Exception e) {
			assertNotNull(e);
		}
		LOG.debug("total time:" + (System.currentTimeMillis() - starttime) + "ms");
	}
}
