package com.zeal.shiyulin.manage.Utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zeal on 2016/2/1.
 */
public class MD5 {

    /**
     * MD5加密，并转换程Base64String
     * 和.BET加密方式一样
     * @param text
     * @return
     */
    public static String Encypt(String text){
        try {
            MessageDigest md=null;
            try {
                md = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException er){
                throw new RuntimeException(er.getMessage());
            }
            try {
                md.update(text.getBytes("US-ASCII"));
            }
            catch (UnsupportedEncodingException er){
                throw new RuntimeException(er.getMessage());
            }
            byte[] digest = md.digest();
            String password=new BASE64Encoder().encode(digest);
            return password;
           /* System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密*/
        } catch (RuntimeException er) {
            // TODO Auto-generated catch block
            throw  er;

        }
    }
}
