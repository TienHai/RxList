package com.rxlist.rxlist.model;

import java.io.UnsupportedEncodingException;

public class ModelUtils {

    /**
     * @param string
     * @return
     */
    public static String decodeString(String string) {
        try {
            return new String(string.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return string;
        }
    }

}