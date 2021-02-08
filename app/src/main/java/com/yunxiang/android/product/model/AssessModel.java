package com.yunxiang.android.product.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssessModel {

    /**
     * pics : {"0":"http://xxxxx","1":"http://xxxxx","2":"http://xxxxx"}
     * name : 好看的玛瑙
     * type : 玛瑙
     * desc : 这是一颗48k纯玛瑙！
     */

    @SerializedName("stone_pics")
    private StonePicsBean pics;
    @SerializedName("stone_name")
    private String name;
    @SerializedName("stone_type")
    private String type;
    @SerializedName("stone_description")
    private String desc;
    @SerializedName("check_time")
    private String checkTime;
    private String phoneNum;

    public AssessModel() {
    }

    public AssessModel(String name, String type, String checkTime, String phoneNum, String desc, List<String> pics) {
        this.pics = new StonePicsBean(pics);
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.checkTime = checkTime;
        this.phoneNum = phoneNum;
    }

    public StonePicsBean getPics() {
        return pics;
    }

    public void setPics(StonePicsBean pics) {
        this.pics = pics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public static class StonePicsBean {
        private List<String> picList;

        public StonePicsBean(List<String> picList) {
            this.picList = picList;
        }

        public List<String> getPicList() {
            return picList;
        }

        public void setPicList(List<String> picList) {
            this.picList = picList;
        }
    }
}
