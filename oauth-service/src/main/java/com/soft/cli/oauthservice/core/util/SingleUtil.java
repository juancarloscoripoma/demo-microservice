package com.soft.cli.oauthservice.core.util;


import org.apache.commons.lang.RandomStringUtils;

import java.security.MessageDigest;

public class SingleUtil {

    private static final String CHARSET_UTF8 = "UTF-8";

    private static SingleUtil singleUtil = new SingleUtil();

    private SingleUtil(){}

    public static SingleUtil getInstance(){
        return singleUtil;
    }

    @Deprecated
    public String randomString(Integer count){
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public String encryptPassword(String password) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(CHARSET_UTF8));
            StringBuilder hexString = new StringBuilder();
            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
