package com.yunxiang.android.personal.data;

import com.yunxiang.android.base.model.BaseModel;

import java.util.Objects;

public class Jade extends BaseModel {


    private int id;

    private long price;

    private long time;

    private double range;

    private double speed;

    private int tradeType;

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private String title;

    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jade)) return false;
        Jade jade = (Jade) o;
        return getId() == jade.getId() &&
                Objects.equals(getPrice(), jade.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getPrice());
    }
}
