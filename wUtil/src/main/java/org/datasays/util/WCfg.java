package org.datasays.util;

import jodd.props.Props;
import jodd.typeconverter.Convert;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class WCfg {
	private static final Logger LOG = LoggerFactory.getLogger(WCfg.class);
	public static String PropsFile = ".\\project.props";
	private static Props cfg = null;

	public static Props getCfg() {
		if (cfg == null) {
			try {
				cfg = new Props();
				cfg.load(new File(PropsFile));
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return cfg;
	}

	public static String getValue(final String key, final String... profiles) {
		return getCfg().getValue(key, profiles);
	}

	public static String[] getValues(final String key, final String... profiles) {
		String value = getValue(key, profiles);
		return StringUtil.split(value, ";");
	}

	public static int getIntValue(final String key, final String... profiles) {
		return Integer.parseInt(getValue(key, profiles));
	}

	public static double getDoubleValue(final String key, final String... profiles) {
		return Convert.toDoubleValue(getValue(key, profiles));
	}

	public static boolean getBoolValue(final String key, final String... profiles) {
		return Convert.toBooleanValue(getValue(key, profiles));
	}

	public static Map<String, Object> getMap(String prefix) {
		return getCfg().innerMap(prefix);
	}

	public static String getPath(final String key, final String... profiles) {
		String path = getValue(key, profiles);
		path = path.trim();
		path = StringUtil.replace(path, "\\", File.separator);
		path = StringUtil.replace(path, "//", File.separator);
		if (!path.endsWith(File.separator)) {
			path += File.separator;
		}
		return getCfg().getValue(key, profiles);
	}

}
