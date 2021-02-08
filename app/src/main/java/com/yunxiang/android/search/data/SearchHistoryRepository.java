package com.yunxiang.android.search.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunxiang.android.search.SearchViewModel;


import java.util.LinkedList;
import java.util.List;


public class SearchHistoryRepository {

    private static final String KEY_SEARCH_HISTORY = "key_search_history";

    private SharedPreferences mSp;

    public SearchHistoryRepository(Context context) {
        mSp = context.getSharedPreferences(KEY_SEARCH_HISTORY, Context.MODE_PRIVATE);
    }

    public void add(int userId, String search) {
        String key = KEY_SEARCH_HISTORY + userId;
        List<String> list = get(userId);
        // 去掉重复记录
        list.remove(search);
        list.add(0, search);
        if (list.size() > SearchViewModel.MAX_HISTORY_SAVE) {
            list = list.subList(0, SearchViewModel.MAX_HISTORY_SAVE);
        }
        save(list, key);
    }


    public void clear(int userId) {
        String key = KEY_SEARCH_HISTORY + userId;
        SharedPreferences.Editor editor = mSp.edit();
        editor.clear();
        editor.remove(key);
        editor.apply();
    }


    public List<String> get(int userId) {
        String key = KEY_SEARCH_HISTORY + userId;
        List<String> list = new LinkedList<>();
        String jsonString = mSp.getString(key, null);
        if (jsonString == null) {
            return list;
        }
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
        }.getType());
        return list;
    }


    public void save(List<String> list, String key) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        SharedPreferences.Editor editor = mSp.edit();
        editor.clear();
        editor.putString(key, jsonString);
        editor.apply();
    }
}
