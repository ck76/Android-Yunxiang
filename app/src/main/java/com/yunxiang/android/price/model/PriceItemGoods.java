package com.yunxiang.android.price.model;

import java.util.List;
import java.util.Objects;


public class PriceItemGoods implements IPriceItemModel{


    /** 商品id */
    private int id;

    /** 商品名称 */
    private String name;

    /** 一口价信息 */
    private List<PriceModel> price;

    /** 商品封面 */
    private String coverUrl;

    /** 商品类型 */
    private int type;

    /** 是否展示序号 */
    private boolean withOrder;

    public boolean isWithOrder() {
        return withOrder;
    }

    public void setWithOrder(boolean withOrder) {
        this.withOrder = withOrder;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PriceModel> getPrice() {
        return price;
    }

    public void setPrice(List<PriceModel> price) {
        this.price = price;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public int getItemType() {
        return TYPE_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceItemGoods)) return false;
        PriceItemGoods goods = (PriceItemGoods) o;
        return getId() == goods.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
