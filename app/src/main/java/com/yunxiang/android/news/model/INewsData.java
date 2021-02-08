
package com.yunxiang.android.news.model;

import org.json.JSONObject;


interface INewsData<T> {

    /**
     * Model转换为Json对象
     */
    JSONObject toJson();

    /**
     * 解析Json对象为Java Model
     *
     * @param jsonObject Json数据
     */
    T toModel(JSONObject jsonObject);
}
