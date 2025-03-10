package com.itdr.utils;

import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesUTil {
    public static String getValue(String key){
        Properties ps = new Properties();
        InputStream in = GetPropertiesUTil.class.getClassLoader().getResourceAsStream("const.properties");
        try{
            ps.load(in);
        }catch(Exception e){
            e.printStackTrace();
        }
        String value = ps.getProperty(key);
        return value;
    }

    public static void main(String[] args) {
        System.out.println(getValue("USER_DISABLE_CODE"));
        System.out.println(getValue("USER_DISABLE_MSG"));
    }
}
