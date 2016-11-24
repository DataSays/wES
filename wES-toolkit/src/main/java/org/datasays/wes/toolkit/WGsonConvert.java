package org.datasays.wes.toolkit;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import org.datasays.util.WJsonExclued;
import org.datasays.wes.core.IConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by watano on 2016/11/21.
 */
public class WGsonConvert implements IConvert {
    public static boolean prettyPrinting = false;
    private static Logger LOG = LoggerFactory.getLogger(WGsonConvert.class);

    /**
     * 构建通用GsonBuilder, 封装初始化工作
     * @return
     */
    public static GsonBuilder getGsonBuilder() {
        return getGsonBuilder(LOG.isDebugEnabled());
    }

    /**
     * 构建通用GsonBuilder, 封装初始化工作
     * @return
     */
    public static GsonBuilder getGsonBuilder(boolean prettyPrinting) {
        GsonBuilder gb = new GsonBuilder();
        gb.setDateFormat("yyyy-MM-dd HH:mm:ss:mss");
        gb.setExclusionStrategies(new ExclusionStrategy(){
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(WJsonExclued.class) != null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz.getAnnotation(WJsonExclued.class) != null;
            }
        });
        if (prettyPrinting)
            gb.setPrettyPrinting();
        return gb;
    }

    @Override
    public String toText(Object obj) {
        return getGsonBuilder(prettyPrinting).create().toJson(obj);
    }

    @Override
    public <T> T parse(String text, Class<T> cls, Type... genericCls) {
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
        return getGsonBuilder().create().fromJson(text, pt);
    }
}
