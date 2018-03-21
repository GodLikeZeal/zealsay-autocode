package com.zeal.shiyulin.manage.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeal on 2016/1/22.
 */
public class RequestUtils {
    public static Map<String,String> getMapValueFromRequest(Map<String,String[]> map){
        Map mapRequest=new HashMap();
        for(String key : map.keySet()){
            String[] arrValue=map.get(key);
            String value=arrValue[0];
            mapRequest.put(key,value);
        }
        return mapRequest;
    }
}
