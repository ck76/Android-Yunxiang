

package com.neuqer.android.sample.home;

import java.util.ArrayList;
import java.util.List;

import com.neuqer.android.sample.module.ActionBarActivity;
import com.neuqer.android.sample.module.TestActivity;


public class ListDataManager {

    /** 数据集合 */
    private static List<ItemModel> mItemList = new ArrayList<>();

    static {
        mItemList.add(new ItemModel("ActionBar", ActionBarActivity.class));
        mItemList.add(new ItemModel("测试页面", TestActivity.class));
    }

    /**
     * 获取列表数据集合
     */
    public static List<ItemModel> getmItemList() {
        return mItemList;
    }
}
