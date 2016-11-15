package org.datasays.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;

import jodd.io.FileUtil;

public class WJsonUtils {
	private static final Logger LOG = LoggerFactory.getLogger(WJsonUtils.class);

	/**
	 * 根据cls和泛型类型反序列化json字符串
	 * @param json
	 * @param cls
	 * @param genericCls
	 * @return
	 */
	public static <T extends Object> T toObject(String json, Class<T> cls, Type... genericCls) {
		ParameterizedType pt = new ParameterizedType() {
			@Override
			public Type[] getActualTypeArguments() {
				return genericCls;
			}

			@Override
			public Type getRawType() {
				return cls;
			}

			@Override
			public Type getOwnerType() {
				return null;
			}
		};
		return getGsonBuilder().create().fromJson(json, pt);
	}

	/**
	 * 根据cls反序列化json字符串
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T extends Object> T fromJson(String json, Class<T> cls) {
		return (T) getGsonBuilder().create().fromJson(json, cls);
	}

	public static <T extends Object> T fromJson(File f, Class<T> cls) {
		try {
			return (T) getGsonBuilder().create().fromJson(FileUtil.readString(f, "utf-8"), cls);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static String toJson(Object obj) {
		return getGsonBuilder().create().toJson(obj);
	}

	public static void writeJson(String file, Object obj) {
		try {
			FileUtil.writeString(file, getGsonBuilder().create().toJson(obj), "utf-8");
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 构建通用GsonBuilder, 封装初始化工作
	 * @return
	 */
	public static GsonBuilder getGsonBuilder() {
		GsonBuilder gb = new GsonBuilder();
		gb.setDateFormat("YYYY-MM-DD hh:mm:ss:mss");
		if (LOG.isDebugEnabled())
			gb.setPrettyPrinting();
		return gb;
	}

}
