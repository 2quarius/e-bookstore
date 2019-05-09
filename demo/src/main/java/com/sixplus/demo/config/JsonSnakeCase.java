package com.sixplus.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

public class JsonSnakeCase {
    static SerializeConfig config = new SerializeConfig();

    {
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    static public String toJSON(Object o){
        // System.out.println(JSONObject.toJSONString(o, config));
        return JSONObject.toJSONString(o, config);
    }
}

