package org.datasays.util.lang;

import jodd.util.ArraysUtil;
import jodd.util.StringUtil;

public class ArgCheck {
	public static void checkState(boolean b, String msg) {
		if (!b) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void checkState(boolean b) {
		if (!b) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkNotNull(Object object) {
		if (object == null) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkNotNull(Object object, String msg) {
		if (object == null) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void checkNotContains(String possibleValues, String value) {
		checkNotContains(StringUtil.split(possibleValues, ","), value);
	}

	public static void checkNotContains(String possibleValues, String value, String msg) {
		checkNotContains(StringUtil.split(possibleValues, ","), value, msg);
	}

	public static void checkNotContains(String[] possibleValues, String value, String msg) {
		if (!ArraysUtil.contains(possibleValues, value)) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void checkNotContains(String[] possibleValues, String value) {
		checkNotContains(possibleValues, value, "possible values are [" + possibleValues + "], but value=" + value);
	}
}
