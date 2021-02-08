
package com.yunxiang.android.news.data;

import com.neuqer.android.cache.SimpleCache;
import com.yunxiang.android.news.model.TabListModel;
import com.yunxiang.android.news.model.TabModel;

import java.util.List;

import com.neuqer.android.runtime.AppRuntime;

public class TabDataManager {

    private static final String TAB_CACHE_NAME = "tab_cache";
    private SimpleCache mCache;

    private TabDataManager() {
        mCache = SimpleCache.get(AppRuntime.getAppContext());
    }

    public static TabDataManager getInstance() {
        return Holder.sInstance;
    }


    public List<TabModel> getTabList() {
        Object data = mCache.getAsObject(TAB_CACHE_NAME);
        if (data instanceof TabListModel) {
            return ((TabListModel) data).getTabModelList();
        }
        return TabPresetData.getTabModelList();
    }

    public void saveTabList(List<TabModel> tabModelList) {
        if (tabModelList == null) {
            return;
        }
        TabListModel model = new TabListModel();
        model.setTabModelList(tabModelList);
        mCache.put(TAB_CACHE_NAME, model);
    }

    private static class Holder {
        private static final TabDataManager sInstance = new TabDataManager();
    }
}
