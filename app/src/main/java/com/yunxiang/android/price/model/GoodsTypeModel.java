package com.yunxiang.android.price.model;

import java.util.List;
import java.util.Objects;


public class GoodsTypeModel {


    /** 商品种类id */
    private int typeId;

    /** 商品种类名称 */
    private String typeName;

    /** 商品种类价格信息 */
    private List<PriceModel> priceList;


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<PriceModel> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceModel> priceList) {
        this.priceList = priceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodsTypeModel)) return false;
        GoodsTypeModel typeModel = (GoodsTypeModel) o;
        return getTypeId() == typeModel.getTypeId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeId());
    }
}
