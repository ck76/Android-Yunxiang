
package com.yunxiang.android.news.data;

import com.yunxiang.android.news.model.TabModel;

import java.util.ArrayList;
import java.util.List;

import com.yunxiang.android.news.model.TabModel;


public class TabPresetData {

    private static final int RECOMMEND_TAB_ID = 0;
    private static final String RECOMMEND_TAB_TITLE = "推荐";
    private static final int AGATE_TAB_ID = 1;
    private static final String AGATE_TAB_TITLE = "玛瑙";

    private static List<TabModel> sTabModelList;

    static {
        sTabModelList = new ArrayList<>();
        sTabModelList.add(new TabModel(RECOMMEND_TAB_ID, RECOMMEND_TAB_TITLE));
        sTabModelList.add(new TabModel(AGATE_TAB_ID, AGATE_TAB_TITLE));
    }

    public static List<TabModel> getTabModelList() {
        return sTabModelList;
    }
}
