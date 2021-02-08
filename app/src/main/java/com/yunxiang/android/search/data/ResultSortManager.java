package com.yunxiang.android.search.data;

import com.yunxiang.android.personal.data.Jade;
import com.yunxiang.android.search.model.SortType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ResultSortManager {

    public static final long MAX_PRICE = Long.MAX_VALUE;
    public static final long MIN_PRICE = Long.MIN_VALUE;

    public static final int TRADE_TYPE_UNKNOWN = -1;
    public static final int TRADE_TYPE_BUYOUT = 0;
    public static final int TRADE_TYPE_AUCTION = 1;


    private static class InstanceHolder {
        private static final ResultSortManager INSTANCE = new ResultSortManager();
    }

    public static ResultSortManager getInstance() {
        return InstanceHolder.INSTANCE;
    }


    public List<Jade> sort(List<Jade> list, SortType type) {
        switch (type) {
            case TIME:
                return sortByTime(list, type.isDown());
            case PRICE:
                return sortByPrice(list, type.isDown());
            case RANGE:
                return sortByRange(list, type.isDown());
            case SPEED:
                return sortBySpeed(list, type.isDown());
            default:
                return null;
        }
    }

    public List<Jade> filter(List<Jade> list,
                             long minPrice,
                             long maxPrice,
                             int tradeType) {

        List<Jade> resList = new ArrayList<>();
        for (Jade item : list) {
            if (item.getPrice() >= minPrice
                    && item.getPrice() <= maxPrice
                    && item.getTradeType() == tradeType) {
                resList.add(item);
            }
        }
        return resList;
    }

    private List<Jade> sortByTime(List<Jade> list, boolean isDown) {
        Collections.sort(list, (o1, o2) -> isDown
                ? Long.compare(o1.getTime(), o2.getTime())
                : Long.compare(o2.getTime(), o1.getTime()));
        return list;
    }

    private List<Jade> sortBySpeed(List<Jade> list, boolean isDown) {
        Collections.sort(list, (o1, o2) -> isDown
                ? Double.compare(o1.getSpeed(), o2.getSpeed())
                : Double.compare(o2.getSpeed(), o1.getSpeed()));
        return list;
    }

    private List<Jade> sortByRange(List<Jade> list, boolean isDown) {
        Collections.sort(list, (o1, o2) -> isDown
                ? Double.compare(o1.getRange(), o2.getRange())
                : Double.compare(o2.getRange(), o1.getRange()));
        return list;
    }

    private List<Jade> sortByPrice(List<Jade> list, boolean isDown) {
        Collections.sort(list, (o1, o2) -> isDown
                ? Long.compare(o1.getPrice(), o2.getPrice())
                : Long.compare(o2.getPrice(), o1.getPrice()));
        return list;
    }
}
