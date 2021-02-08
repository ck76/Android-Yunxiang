package com.yunxiang.android.account.model;

import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("name")
    private String name;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("signature")
    private String signature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
