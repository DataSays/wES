package org.datasays.util.lang;

import jodd.typeconverter.Convert;

import java.util.ArrayList;
import java.util.List;

public class ValuePlus {
	public static int intValue(Object value, int defaultValue) {
		if (value != null) {
			return Convert.toIntValue(value);
		}
		return defaultValue;
	}

	public static long longValue(Object value, long defaultValue) {
		if (value != null) {
			return Convert.toLongValue(value);
		}
		return defaultValue;
	}

	public static short shortValue(Object value, short defaultValue) {
		if (value != null) {
			return Convert.toShortValue(value);
		}
		return defaultValue;
	}

	public static float floatValue(Object value, float defaultValue) {
		if (value != null) {
			return Convert.toFloatValue(value);
		}
		return defaultValue;
	}

	public static double doubleValue(Object value, double defaultValue) {
		if (value != null) {
			return Convert.toDoubleValue(value);
		}
		return defaultValue;
	}

	public static byte byteValue(Object value, byte defaultValue) {
		if (value != null) {
			return Convert.toByteValue(value);
		}
		return defaultValue;
	}

	public static char charValue(Object value, char defaultValue) {
		if (value != null) {
			return Convert.toCharValue(value);
		}
		return defaultValue;
	}

	public static String strValue(Object value, String defaultValue) {
		if (value != null) {
			return Convert.toString(value);
		}
		return defaultValue;
	}

	public static String[] strValues(Object value, String[] defaultValue) {
		if (value != null) {
			return Convert.toStringArray(value);
		}
		return defaultValue;
	}

	public static boolean booleanValue(Object value, boolean defaultValue) {
		if (value != null && value instanceof String && ((String) value).trim().length() > 0) {
			String v = ((String) value).trim();
			if ("1".equals(v) || "true".equalsIgnoreCase(v) || "yes".equalsIgnoreCase(v) || "on".equalsIgnoreCase(v)) {
				return true;
			}
			if ("0".equals(v) || "false".equalsIgnoreCase(v) || "no".equalsIgnoreCase(v) || "off".equalsIgnoreCase(v)) {
				return false;
			}
			return Convert.toBooleanValue(value);
		}
		return defaultValue;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> lst(T... all) {
		List<T> lst = new ArrayList<>();
		for (T t : all) {
			lst.add(t);
		}
		return lst;
	}
}
