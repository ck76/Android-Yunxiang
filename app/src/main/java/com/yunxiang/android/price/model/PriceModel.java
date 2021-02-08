package com.yunxiang.android.price.model;

import java.util.Objects;


public class PriceModel {

    /** 该价格对应的时间 */
    private String time;
    /** 具体价格 */
    private long price;
    /** 涨/跌 */
    private boolean increasing;
    /** 增/跌 幅度 */
    private double priceRange;
    /** 增/跌 速度 */
    private double priceSpeed;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isIncreasing() {
        return increasing;
    }

    public void setIncreasing(boolean increasing) {
        this.increasing = increasing;
    }

    public double getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(double priceRange) {
        this.priceRange = priceRange;
    }

    public double getPriceSpeed() {
        return priceSpeed;
    }

    public void setPriceSpeed(double priceSpeed) {
        this.priceSpeed = priceSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceModel)) return false;
        PriceModel that = (PriceModel) o;
        return getPrice() == that.getPrice() &&
                isIncreasing() == that.isIncreasing() &&
                Double.compare(that.getPriceRange(), getPriceRange()) == 0 &&
                Double.compare(that.getPriceSpeed(), getPriceSpeed()) == 0 &&
                Objects.equals(getTime(), that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTime(), getPrice(), isIncreasing(), getPriceRange(), getPriceSpeed());
    }
}
