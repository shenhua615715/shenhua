/**
 * 
 */
package com.shenhua.base.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>集合工具类</p>
 * 
 * @author liuye
 * @version [1.0, 2017年8月11日]
 * @since [2017年8月11日]
 */
public class CollectionUtil {

    /**
     * <p>以key, value, key, value....参数构造一个map</p>
     * 
     * @param objs 键值对们
     * @return
     * @author liuye
     * @since [2017年8月11日]
     */
    public static Map<String, Object> buildKeyValueMap(Object... objs) {
        Map<String, Object> map = new HashMap<String, Object>();
        return buildKeyValueMap(map, objs);
    }

    /**
     * <p>以key, value, key, value....参数构造一个有序map</p>
     * 
     * @param objs 键值对们
     * @return
     * @author liuye
     * @since [2017年8月11日]
     */
    public static Map<String, Object> buildLinkedKeyValueMap(Object... objs) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        return buildKeyValueMap(map, objs);
    }

    /**
     * <p>构造一个顺序的列表</p>
     * 
     * @param objs
     * @return
     * @author liuye
     * @since [2017年10月2日]
     */
    public static <T> List<T> buildArrayList(T... objs) {
        List<T> ret = new ArrayList<T>();
        if (null == objs || objs.length == 0) {
            return ret;
        }
        for (T obj : objs) {
            ret.add(obj);
        }
        return ret;
    }

    public static Map<String, Object> buildKeyValueMap(Map<String, Object> result, Object... objs) {
        for (int i = 0; i < objs.length / 2; i++) {
            String key = (String) objs[i * 2];
            Object value = objs[i * 2 + 1];
            if (value != null) {
                result.put(key, value);
            }
        }
        return result;
    }

    public static boolean isEmpty(Collection<?> col) {
        return null == col || col.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> col) {
        return null == col || col.isEmpty();
    }
}
