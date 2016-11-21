package org.datasays.wes.demo.vo;

import org.datasays.util.collection.StrMap;

import jodd.util.StringUtil;

public interface IPropVO {
	public StrMap getProps();

	public void setProps(StrMap props);

	public default String getProp(String name){
		return getProp(name, null);
	}

	public default String getProp(String name, String defaultValue){
		return getProps().getOrDefault(name, defaultValue);
	}

	public default void setProp(String name, String value){
		if(StringUtil.isNotBlank(name)){
			if(value == null){
				getProps().remove(name);
			}else{
				getProps().putWithoutNull(name, value);
			}
		}
	}
}
