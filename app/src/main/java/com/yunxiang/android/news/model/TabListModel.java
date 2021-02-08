
package com.yunxiang.android.news.model;

import java.io.Serializable;
import java.util.List;


public class TabListModel implements Serializable {

    private List<TabModel> mTabModelList;

    public List<TabModel> getTabModelList() {
        return mTabModelList;
    }

    public void setTabModelList(List<TabModel> tabModelList) {
        mTabModelList = tabModelList;
    }
}
