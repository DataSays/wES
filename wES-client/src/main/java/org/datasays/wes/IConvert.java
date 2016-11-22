package org.datasays.wes;

import java.lang.reflect.Type;

/**
 * Created by watano on 2016/11/21.
 */
public interface IConvert {
    public String toText(Object obj);
    public <T> T parse(String text, Class<T> cls, Type... genericCls);
}
