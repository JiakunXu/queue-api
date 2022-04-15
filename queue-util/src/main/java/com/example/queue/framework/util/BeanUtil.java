package com.example.queue.framework.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author JiakunXu
 */
public class BeanUtil {

    /**
     *
     * @param source
     * @param target
     * @return
     */
    public static <T> T copy(Object source, Class<T> target) {
        return JSON.parseObject(JSON.toJSONString(source), target);
    }

    /**
     *
     * @param source
     * @param target
     */
    public static <T> List<T> copy(List<?> source, Class<T> target) {
        return JSON.parseArray(JSON.toJSONString(source), target);
    }

}
