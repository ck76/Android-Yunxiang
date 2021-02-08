package com.yunxiang.android.product.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PublishModel {

    /**
     * checkId : 1
     * goodsPics : {"0":"http://xxxxx","1":"http://xxxxx","2":"http://xxxxx"}
     * goodsName : 美丽的钻石
     * stoneType : 钻石
     * buyType : 1
     * price1 : 100000
     */

    @SerializedName("check_id")
    private String checkId;
    @SerializedName("goods_pics")
    private GoodsPicsBean goodsPics;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("goods_desc")
    private String goodsDesc;
    @SerializedName("stone_type")
    private String stoneType;
    @SerializedName("buy_type")
    private String buyType;
    private String price1;
    private String price2;
    @SerializedName("start_at")
    private String startTime;
    @SerializedName("end_at")
    private String endTime;

    public PublishModel() {

    }

    /**
     * 一口价+竞拍
     */
    public PublishModel(String checkId, List<String> goodsPics,
                        String goodsName, String goodsDesc,
                        String stoneType, String buyType,
                        String price1, String price2,
                        String startTime, String endTime) {
        this.checkId = checkId;
        this.goodsPics = new GoodsPicsBean(goodsPics);
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.stoneType = stoneType;
        this.buyType = buyType;
        this.price1 = price1;
        this.price2 = price2;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 一口价
     */
    public PublishModel(String checkId, List<String> goodsPics,
                        String goodsName, String goodsDesc,
                        String stoneType, String buyType,
                        String price1) {
        this.checkId = checkId;
        this.goodsPics = new GoodsPicsBean(goodsPics);
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.stoneType = stoneType;
        this.buyType = buyType;
        this.price1 = price1;
    }

    /**
     * 竞拍
     */
    public PublishModel(String checkId, List<String> goodsPics,
                        String goodsName, String goodsDesc,
                        String stoneType, String buyType,
                        String price2, String startTime,
                        String endTime) {
        this.checkId = checkId;
        this.goodsPics = new GoodsPicsBean(goodsPics);
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.stoneType = stoneType;
        this.buyType = buyType;
        this.price2 = price2;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public GoodsPicsBean getGoodsPics() {
        return goodsPics;
    }

    public void setGoodsPics(GoodsPicsBean goodsPics) {
        this.goodsPics = goodsPics;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getStoneType() {
        return stoneType;
    }

    public void setStoneType(String stoneType) {
        this.stoneType = stoneType;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }



    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public static class GoodsPicsBean {
        private List<String> picList;

        public GoodsPicsBean(List<String> picList) {
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
