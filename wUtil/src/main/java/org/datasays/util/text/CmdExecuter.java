package org.datasays.util.text;

import jodd.io.FileUtil;
import jodd.util.StringUtil;
import org.datasays.util.collection.StrMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CmdExecuter {
	private static final Logger LOG = LoggerFactory.getLogger(CmdExecuter.class);
	public StrMap mapData = new StrMap();

	public abstract void doAction(String action, String[] args);

	public String[] values(String key) {
		String value = mapData.get(key);
		if (value != null) {
			return StringUtil.split(value, "|");
		} else {
			return new String[]{};
		}
	}

	public String value(String key) {
		return mapData.get(key);
	}

	public void runFile(String file) {
		try {
			String[] lines = FileUtil.readLines(file, "utf-8");
			for (String line0 : lines) {
				String line = line0.trim();
				int index = line.indexOf('=');
				if (line.startsWith("#")) {//comment
					//println(line);
				} else if (line.startsWith("add ")) {//add value into map-key obj
					String key = line.substring(4, index).trim();
					String value = line.substring(index + 1).trim();

					//eval key and value
					key = mapData.eval(key);
					value = mapData.eval(value);
					String keyObj = mapData.get(key);
					if (keyObj == null) {
						mapData.put(key, value);
					} else {
						mapData.put(key, keyObj + "|" + value);
					}
				} else if (line.startsWith("set ") && index > 0) {//set value into map-key obj
					String key = line.substring(4, index).trim();
					String value = line.substring(index + 1).trim();
					//eval key and value
					key = mapData.eval(key);
					value = mapData.eval(value);
					mapData.put(key, value);
				} else if (line.startsWith("@")) {
					line = line.substring(1).trim();
					line = StringUtil.replace(line, "  ", " ");
					String action = "";
					List<String> allArgs = new ArrayList<>();
					int i = 0;
					for (String a : StringUtil.split(line, " ")) {
						a = a.trim();
						a = mapData.eval(a);
						if (i == 0) {
							action = a;
						} else {
							allArgs.add(a);
						}
						i++;
					}
					doAction(action, allArgs.toArray(new String[]{}));
				}
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
