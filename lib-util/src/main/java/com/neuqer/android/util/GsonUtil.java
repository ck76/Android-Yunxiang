package com.neuqer.android.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;



public class GsonUtil {

    /** Gson */
    private static Gson sGson;

    static {
        sGson = new Gson();
    }

    /**
     * 获取Gson解析
     */
    public static Gson get() {
        return sGson;
    }

    /**
     * 解析String 为JSONObject
     *
     * @param content 内容
     */
    public static JsonObject parseString(String content) {
        if (TextUtils.isEmpty(content)) {
            return new JsonObject();
        }
        try {
            return new JsonParser().parse(content).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }
}
