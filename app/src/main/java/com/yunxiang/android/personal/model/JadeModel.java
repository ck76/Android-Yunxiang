package com.yunxiang.android.personal.model;

import com.google.gson.annotations.SerializedName;
import com.yunxiang.android.base.model.BaseModel;


public class JadeModel extends BaseModel {

    // 玉石id
    @SerializedName("id")
    private int id;

    // 用户id
    @SerializedName("user_id")
    private int userId;

    // 评估id
    @SerializedName("check_id")
    private int checkId;


    /** ======================= 商品相关 ============================= */

    // 商品图片
    @SerializedName("goods_pics")
    private ImageModel goodPics;

    // 商品名称
    @SerializedName("goods_name")
    private String goodsName;

    // 商品描述
    @SerializedName("goods_desc")
    private String goodDescribe;

    // 购买类型
    @SerializedName("buy_type")
    private int buyType;

    // 购买状态
    @SerializedName("buy_status")
    private int buyStatus;

    // 一口价价格
    @SerializedName("price1")
    private long buyoutPrice;

    // 当前竞拍价格
    @SerializedName("price2")
    private long auctionPrice;

    // 售卖开始时间
    @SerializedName("start_at")
    private long startAt;

    // 售卖结束时间
    @SerializedName("end_at")
    private long endAt;

    // 出售数量
    @SerializedName("sell_num")
    private int sellNum;

    // 创建时间
    @SerializedName("created_at")
    private long createdAt;

    // 更新时间
    @SerializedName("updated_at")
    private long updatedAt;

    // 收藏人数

    private int followNumber;

    /** ======================= 玉石相关 ============================= */

    @SerializedName("master")
    private int master;

    // 玉石种类
    @SerializedName("stone_type")
    private String stoneType;

    // 玉石图片
    @SerializedName("stone_pics")
    private ImageModel stonePics;

    // 玉石名称
    @SerializedName("stone_name")
    private String stoneName;

    // 玉石价格
    @SerializedName("stone_price")
    private long stonePrice;

    // 玉石描述
    @SerializedName("stone_description")
    private String stoneDescription;

    /** ======================= 评估相关 ============================= */

    // 评估时间
    @SerializedName("check_time")
    private long checkTime;

    // 评估状态 : 送检中/已检验
    @SerializedName("status")
    private String checkStatus;

    // 评估结果 : 通过/未通过(当check_status为送检中时，此参数无用)
    @SerializedName("check_result")
    private String checkResult;

    // 评估机构id
    @SerializedName("org")
    private int orgId;

    // 评估机构名称
    @SerializedName("org_name")
    private String orgName;

    // 评估人
    @SerializedName("checker")
    private String checker;

    // 评估内容
    @SerializedName("checker_say")
    private String checkContent;


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

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public ImageModel getGoodPics() {
        return goodPics;
    }

    public void setGoodPics(ImageModel goodPics) {
        this.goodPics = goodPics;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodDescribe() {
        return goodDescribe;
    }

    public void setGoodDescribe(String goodDescribe) {
        this.goodDescribe = goodDescribe;
    }

    public int getBuyType() {
        return buyType;
    }

    public void setBuyType(int buyType) {
        this.buyType = buyType;
    }

    public int getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(int buyStatus) {
        this.buyStatus = buyStatus;
    }

    public long getStartAt() {
        return startAt;
    }

    public void setStartAt(long startAt) {
        this.startAt = startAt;
    }

    public long getEndAt() {
        return endAt;
    }

    public void setEndAt(long endAt) {
        this.endAt = endAt;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public String getStoneType() {
        return stoneType;
    }

    public void setStoneType(String stoneType) {
        this.stoneType = stoneType;
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

    public long getStonePrice() {
        return stonePrice;
    }

    public void setStonePrice(long stonePrice) {
        this.stonePrice = stonePrice;
    }

    public String getStoneDescription() {
        return stoneDescription;
    }

    public void setStoneDescription(String stoneDescription) {
        this.stoneDescription = stoneDescription;
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

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public long getBuyoutPrice() {
        return buyoutPrice;
    }

    public void setBuyoutPrice(long buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }

    public long getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(long auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public int getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(int followNumber) {
        this.followNumber = followNumber;
    }
}
