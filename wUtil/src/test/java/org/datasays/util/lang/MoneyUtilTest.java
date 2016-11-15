package org.datasays.util.lang;

import static org.junit.Assert.assertEquals;

import org.datasays.util.lang.MoneyUtil;
import org.junit.Test;

public class MoneyUtilTest {
	@Test()
	public void testGetWeightedAverage1() {
		Double d1 = 150d;
		Double d2 = 149d;
		Long q1 = 0L;
		Long q2 = 1L;
		Double result = MoneyUtil.getWeightedAverage(d1, q1, d2, q2);
		assertEquals(149d, result.doubleValue(), 2);
	}

	@Test()
	public void testGetWeightedAverage2() {

	}
}
