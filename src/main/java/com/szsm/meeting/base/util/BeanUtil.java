package com.szsm.meeting.base.util;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class BeanUtil {

    public  static <T> T copyPropertiesByFastJson(Object source,Class<T> target) {
        if(source == null) {
            return null;
        } else {
            String jsonString = JSONObject.toJSONString(source);
            return JSONObject.parseObject(jsonString,target);
        }
    }
    public static <T> List<T> copyPropertiesByFastJson(List<?> source, Class<T> target) {
        return source == null ? null : (List)source.stream().map((item) -> {
            return copyPropertiesByFastJson(item, target);
        }).collect(Collectors.toList());
    }

}
