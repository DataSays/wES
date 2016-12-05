package org.datasays.wes.core;

import java.lang.reflect.Type;

/**
 * Created by watano on 2016/11/21.
 */
public interface IConvert {
	/**
	 * serialize a java object to json text
	 * @param obj : java object
	 * @return json text
	 */
	public String toText(Object obj);

	/**
	 * deserialize a json text to java object
	 * @param text :json text
	 * @param cls :deserialize java object class
	 * @param genericCls :deserialize java object generic class
	 * @return deserialized java object
	 */
	public <T> T parse(String text, Class<T> cls, Type... genericCls);
}
