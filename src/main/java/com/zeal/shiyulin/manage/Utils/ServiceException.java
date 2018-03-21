package com.zeal.shiyulin.manage.Utils;


import org.json.JSONObject;

/**
 * Created by zeal on 2016/1/28.
 */
public class ServiceException {
    public static String ErrorMsg(Throwable ex){
        String msg=ex.getMessage();
        if(msg==null) {
            msg = ex.toString();
        }
        return msg;
    }

    public static String ErrorMsg(Exception er){
        String msg=er.getMessage();
        if(msg==null) {
            msg = er.toString();
        }
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("errorCode", "200");
            jsonObject.put("errorMessage", msg);
        }
        catch(Exception e) {

        }
        return jsonObject.toString();
    }
}
