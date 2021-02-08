package com.neuqer.android.scheme;


public class SchemeRuntime {

    /** 协议头 */
    private static String sSchemeHeader = "neuqer";

    public static String getSchemeHeader() {
        return sSchemeHeader;
    }

    public static void setSchemeHeader(String schemeHeader) {
        sSchemeHeader = schemeHeader;
    }
}
