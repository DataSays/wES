package org.datasays.util.lang;

import org.junit.Assert;
import org.junit.Test;

public class ArithUtilsTest {

	@Test
	public void testAdd() {
		Assert.assertEquals(ArithUtils.add(0d, 0d), 0.0, 10);
		Assert.assertEquals(ArithUtils.add(1.00001, 1.0001), 2.00011, 10);
	}

	@Test
	public void testSub() {
	}

	@Test
	public void testMul() {
	}

	@Test
	public void testDivDoubleDouble() {
	}

	@Test
	public void testDivDoubleDoubleInt() {
	}

	@Test
	public void testRound() {
		Assert.assertEquals(ArithUtils.round(0.1234, 2), 0.12, 10);
		Assert.assertEquals(ArithUtils.round(0.0049, 2), 0.00, 10);
		Assert.assertEquals(ArithUtils.round(0.0050, 2), 0.01, 10);
		Assert.assertEquals(ArithUtils.round(0.0099, 2), 0.01, 10);
		Assert.assertEquals(ArithUtils.round(0.0000, 2), 0.00, 10);
		Assert.assertEquals(ArithUtils.round(-0.1234, 2), -0.12, 10);
		Assert.assertEquals(ArithUtils.round(1234567890.1234, 2), 1234567890.12, 10);
		Assert.assertEquals(ArithUtils.round(-1234567890.1234, 2), -1234567890.12, 10);
	}

}
