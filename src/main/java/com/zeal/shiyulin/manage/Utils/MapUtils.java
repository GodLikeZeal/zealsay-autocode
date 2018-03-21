package com.zeal.shiyulin.manage.Utils;

import java.util.Map;

/**
 * Created by zeal on 2016/1/27.
 */
public class MapUtils {
    public static String FilterMapValue(Map map,String key){
        if(map==null)
            return "";
        else{
            if(map.containsKey(key)){
                if(map.get(key)==null){
                    return "";
                }
                else{
                    return String.valueOf(map.get(key));
                }
            }
            return "";
        }
    }
}
