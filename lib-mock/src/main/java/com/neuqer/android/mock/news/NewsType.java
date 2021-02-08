
package com.neuqer.android.mock.news;

import java.util.HashMap;


public enum NewsType {

    NO_MATCH("no_match"),                                            // 非法数据
    TEXT("text_only"),                                               // 纯文本
    ONE_IMG_LEFT("one_img_left"),                                    // 左图右文
    THREE_IMG("three_img");                                          // 三图


    private static final HashMap<String, NewsType> sMap = new HashMap<>();
    private String mName;

    static {
        for (NewsType layout : NewsType.values()) {
            sMap.put(layout.mName, layout);
        }
    }

    NewsType(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public static NewsType getLayout(String name) {
        NewsType layout = sMap.get(name);
        return layout == null ? NO_MATCH : layout;
    }

    public static boolean isSupportLayout(String name) {
        return getLayout(name) != NO_MATCH;
    }
}
