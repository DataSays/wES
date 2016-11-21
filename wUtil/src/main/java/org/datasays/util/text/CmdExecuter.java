package org.datasays.util.text;

import java.io.IOException;

import org.datasays.util.collection.StrMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.io.FileUtil;
import jodd.util.StringUtil;

public abstract class CmdExecuter {	
	private static final Logger LOG = LoggerFactory.getLogger(CmdExecuter.class);
	public abstract void doAction(String action, String[] args);
	
	public void runFile(String file){
		try {
			String[] lines = FileUtil.readLines(file, "utf-8");
			StrMap values = new StrMap();
			for(String line0:lines){
				String line = line0.trim();
				int index = line.indexOf('=');
				if(line.startsWith("#")){
					//println(line);
				}else if(line.startsWith("set ") && index>0){
					String key = line.substring(4, index);
					String value = line.substring(index+1);
					values.put(values.eval(key.trim()), values.eval(value.trim()));
				}else if(line.startsWith("@")){
					if(index>0){
						String args = line.substring(index+1);
						args = StringUtil.replace(args, "\t", " ");
						doAction(line.substring(1, index).trim(), StringUtil.split(args, " "));
					}else{
						doAction(line.substring(1).trim(), new String[]{});
					}
				}
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}		
	}
}
