
package com.yunxiang.android.home.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class HomeCategoryModel implements Serializable {

    @SerializedName("row")
    private int mRow = 2;
    @SerializedName("column")
    private int mColumn = 5;
    @SerializedName("category_item")
    private List<CategoryModel> mCategoryList;

    public HomeCategoryModel(List<CategoryModel> categoryList) {
        mCategoryList = categoryList;
    }

    public HomeCategoryModel(int row, int column, List<CategoryModel> categoryList) {
        mRow = row;
        mColumn = column;
        mCategoryList = categoryList;
    }

    public List<CategoryModel> getCategoryList() {
        return mCategoryList;
    }

    public int getRow() {
        return mRow;
    }

    public int getColumn() {
        return mColumn;
    }

    public static class CategoryModel implements Serializable {
        @SerializedName("id")
        private int mId;
        @SerializedName("name")
        private String mName;
        @SerializedName("icon")
        private String mIcon;
        @SerializedName("cmd")
        private String mCmd;

        public CategoryModel(int id, String name, String icon, String cmd) {
            mId = id;
            mName = name;
            mIcon = icon;
            mCmd = cmd;
        }

        public int getId() {
            return mId;
        }

        public String getName() {
            return mName;
        }

        public String getIcon() {
            return mIcon;
        }

        public String getCmd() {
            return mCmd;
        }
    }
}
