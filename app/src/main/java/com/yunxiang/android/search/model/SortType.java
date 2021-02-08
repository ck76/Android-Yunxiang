package com.yunxiang.android.search.model;


public enum SortType {
    // 时间排序
    TIME(true),
    // 涨幅排序
    RANGE(true),
    // 涨速排序
    SPEED(true),
    // 价格排序
    PRICE(true);

    // 是否为降序排列
    boolean isDown;

    SortType(boolean isDown) {
        this.isDown = isDown;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public static SortType byTime(boolean isDown) {
        TIME.setDown(isDown);
        return TIME;
    }

    public static SortType byRange(boolean isDown) {
        RANGE.setDown(isDown);
        return RANGE;
    }

    public static SortType bySpeed(boolean isDown) {
        SPEED.setDown(isDown);
        return SPEED;
    }

    public static SortType byPrice(boolean isDown) {
        PRICE.setDown(isDown);
        return PRICE;
    }
}
