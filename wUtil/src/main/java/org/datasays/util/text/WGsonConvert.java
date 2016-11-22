package org.datasays.util.text;

import org.datasays.util.WJsonUtils;

import java.lang.reflect.Type;

/**
 * Created by watano on 2016/11/21.
 */
public class WGsonConvert implements IConvert{
    @Override
    public String toText(Object obj) {
        return WJsonUtils.toJson(obj);
    }

    @Override
    public <T> T parse(String text, Class<T> cls, Type... genericCls) {
        return WJsonUtils.toObject(text, cls, genericCls);
    }
}
