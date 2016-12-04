package org.datasays.util.collection;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MapUtil {
	public static Object traverse(MapTraverser action) {
		Iterator<?> keys = action.getMap().keySet().iterator();
		while (keys.hasNext()) {
			Object key = keys.next();
			action.doAction(key, action.getMap().get(key));
		}
		return action.getResult();
	}

	public static Object traverse(ListTraverser action) {
		Iterator<?> keys = action.getList().iterator();
		while (keys.hasNext()) {
			Object key = keys.next();
			action.doAction(key);
		}
		return action.getResult();
	}

	public static String toString(Map<?, ?> map) {
		return (String) MapUtil.traverse(new MapTraverser(map) {
			private StringBuffer sb = new StringBuffer();

			@Override
			public void doAction(Object key, Object value) {
				sb.append("\t[").append(/*value.getClass().getName()+"@"+value.hashCode()+"@"+*/key).append("=");
				if (value instanceof Map<?, ?>) {
					sb.append(MapUtil.toString((Map<?, ?>) value));
				} else if (value instanceof List<?>) {
					sb.append(MapUtil.toString((List<?>) value));
				} else {
					sb.append(value);
				}
				sb.append("],\n");
			}

			@Override
			public Object getResult() {
				return /*super.getMap().getClass().getName()+"@"+super.getMap().hashCode()+*/"{\n" + sb.toString() + "}";
			}
		});
	}

	public static String toString(List<?> list) {
		return (String) MapUtil.traverse(new ListTraverser(list) {
			private StringBuffer sb = new StringBuffer();

			@Override
			public void doAction(Object key) {
				if (key instanceof Map<?, ?>) {
					sb.append(MapUtil.toString((Map<?, ?>) key));
				} else if (key instanceof List<?>) {
					sb.append(MapUtil.toString((List<?>) key));
				} else {
					sb.append(key);
				}
				sb.append(",");
			}

			@Override
			public Object getResult() {
				return /*super.getList().getClass().getName()+"@"+super.getList().hashCode()+*/"{" + sb.toString() + "}";
			}
		});
	}

	public static boolean compare(Map<?, ?> map1, Map<?, ?> map2) {
		boolean result = true;
		if (map1 == map2) {
			return true;
		}
		if (map1.equals(map2)) {
			return true;
		}
		if (map1.size() != map2.size()) {
			return false;
		}
		//TODO compare(Map map1, Map map2)
		result = false;
		return result;
	}

	public static String muilString(String str, int i) {
		String tmp = "";
		int j = i;
		while (j-- > 0) {
			tmp += str;
		}
		return tmp;
	}

	public static void append(Map<?, ?> map1, Map<?, ?> map2) {
		MapUtil.traverse(new MapTraverser(map2, map1) {
			@Override
			public void doAction(Object key, Object value) {
				Map<Object, Object> target = (Map<Object, Object>) input;
				target.put(key, value);
			}
		});
	}

	public static Object getValue(Map<?, ?> map, Object key, Object defaultValue) {
		Object value = null;
		if (map != null) {
			value = map.get(key);
			if (value == null) {
				value = defaultValue;
			}
		}
		return value;
	}

	/**
	 * note:
	 * this method invoked Object.equals(),so need insure that
	 * otherExclusion implement equals() method according to your want
	 *
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @param otherExclusion
	 * @return
	 */
	public static Object getValue(Map<?, ?> map, Object key, Object defaultValue, Object otherExclusion) {
		Object value = null;
		if (map != null) {
			value = map.get(key);
			if (value == null || value.equals(otherExclusion)) {
				value = defaultValue;
			}
		}
		return value;
	}
}
