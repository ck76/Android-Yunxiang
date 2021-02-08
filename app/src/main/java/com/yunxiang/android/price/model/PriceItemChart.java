package com.yunxiang.android.price.model;

import java.util.List;
import java.util.Objects;


public class PriceItemChart implements IPriceItemModel{


    private boolean increasing;

    private List<GoodsTypeModel> rankList;

    public PriceItemChart() {
    }


    public PriceItemChart(boolean increasing, List<GoodsTypeModel> rankList) {
        this.increasing = increasing;
        this.rankList = rankList;
    }

    public boolean isIncreasing() {
        return increasing;
    }

    public void setIncreasing(boolean increasing) {
        this.increasing = increasing;
    }

    public List<GoodsTypeModel> getRankList() {
        return rankList;
    }

    public void setRankList(List<GoodsTypeModel> rankList) {
        this.rankList = rankList;
    }

    @Override
    public int getItemType() {
        return TYPE_CHART;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceItemChart)) return false;
        PriceItemChart that = (PriceItemChart) o;
        return getRankList().containsAll(that.getRankList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getRankList());
    }
}
