package com.zeal.shiyulin.common.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zeal on 2016/12/15.
 */
public class PropertiesUtils {
    public static String getM_jdbcUrl() {
        return m_jdbcUrl;
    }

    public static void setM_jdbcUrl(String m_jdbcUrl) {
        PropertiesUtils.m_jdbcUrl = m_jdbcUrl;
    }

    public static String getM_jdbcUserName() {
        return m_jdbcUserName;
    }

    public static void setM_jdbcUserName(String m_jdbcUserName) {
        PropertiesUtils.m_jdbcUserName = m_jdbcUserName;
    }

    public static String getM_jdbcPassWord() {
        return m_jdbcPassWord;
    }

    public static void setM_jdbcPassWord(String m_jdbcPassWord) {
        PropertiesUtils.m_jdbcPassWord = m_jdbcPassWord;
    }

    private static String m_jdbcUrl=null;
    private static String m_jdbcUserName=null;
    private static String m_jdbcPassWord=null;
    static{
        loads();
    }
    synchronized static public void loads(){
        if(m_jdbcUrl==null || m_jdbcUserName==null
                || m_jdbcPassWord==null ){
            InputStream is = PropertiesUtils.class.getResourceAsStream("/config.properties");
            Properties urlProperties = new Properties();
            try {
                urlProperties.load(is);

                m_jdbcUrl  = urlProperties.getProperty("jdbc.url").toString();
                m_jdbcUserName  = urlProperties.getProperty("jdbc.username").toString();
                m_jdbcPassWord  = urlProperties.getProperty("jdbc.password").toString();
            }
            catch (Exception e) {
                System.err.println("不能读取属性文件. " + "请确保url.properties在CLASSPATH指定的路径中");
            }
        }

    }
}
