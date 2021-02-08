package com.yunxiang.android.search.data;

import com.yunxiang.android.search.model.ResultTab;

import java.util.ArrayList;
import java.util.List;

public class SearchResultRepository {


    private SearchResultRepository() {
    }

    private static class InstanceHolder {
        private static final SearchResultRepository INSTANCE = new SearchResultRepository();
    }

    public static SearchResultRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }


    public List<ResultTab> getTabList() {
        List<ResultTab> list = new ArrayList<>();
        list.add(new ResultTab(0, "综合"));
        list.add(new ResultTab(1, "战国红"));
        list.add(new ResultTab(2, "水草"));
        list.add(new ResultTab(3, "南红"));
        list.add(new ResultTab(4, "蓝玉髓"));
        return list;
    }


}
