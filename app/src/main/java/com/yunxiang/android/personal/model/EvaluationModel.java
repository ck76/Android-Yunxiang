package com.yunxiang.android.personal.model;

import com.google.gson.annotations.SerializedName;
import com.yunxiang.android.base.model.BaseModel;


public class EvaluationModel extends BaseModel {

    // 评估记录id
    @SerializedName("id")
    private int id;

    // 发起评估用户id
    @SerializedName("user_id")
    private int userId;

    // 玉石主人
    @SerializedName("master")
    private int master;

    // 玉石图片
    @SerializedName("stone_pics")
    private ImageModel stonePics;

    // 玉石名称
    @SerializedName("stone_name")
    private String stoneName;

    // 玉石描述
    @SerializedName("stone_description")
    private String stoneDescription;

    // 玉石类型
    @SerializedName("stone_type")
    private String stoneType;

    // 玉石价格
    @SerializedName("stone_price")
    private long stonePrice;

    // 发起评估预约时间
    @SerializedName("created_at")
    private long createdAt;

    // 评估时间
    @SerializedName("check_time")
    private long checkTime;

    // 评估状态 : 送检中/已检验
    @SerializedName("status")
    private String checkStatus;

    // 评估结果 : 通过/未通过(当check_status为送检中时，此参数无用)
    @SerializedName("check_result")
    private String checkResult;

    // 评估内容
    @SerializedName("checker_say")
    private String checkContent;

    // 评估人
    @SerializedName("checker")
    private String checker;

    // 评估机构id
    @SerializedName("org")
    private int orgId;

    // 评估机构名称
    @SerializedName("org_name")
    private String orgName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public ImageModel getStonePics() {
        return stonePics;
    }

    public void setStonePics(ImageModel stonePics) {
        this.stonePics = stonePics;
    }

    public String getStoneName() {
        return stoneName;
    }

    public void setStoneName(String stoneName) {
        this.stoneName = stoneName;
    }

    public String getStoneDescription() {
        return stoneDescription;
    }

    public void setStoneDescription(String stoneDescription) {
        this.stoneDescription = stoneDescription;
    }

    public String getStoneType() {
        return stoneType;
    }

    public void setStoneType(String stoneType) {
        this.stoneType = stoneType;
    }

    public long getStonePrice() {
        return stonePrice;
    }

    public void setStonePrice(long stonePrice) {
        this.stonePrice = stonePrice;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(long checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
