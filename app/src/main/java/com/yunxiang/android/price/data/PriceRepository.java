package com.yunxiang.android.price.data;

import com.yunxiang.android.price.model.GoodsTypeModel;
import com.yunxiang.android.price.model.IPriceItemModel;
import com.yunxiang.android.price.model.PriceItemChart;
import com.yunxiang.android.price.model.PriceItemGoods;
import com.yunxiang.android.price.model.PriceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class PriceRepository {


    private static class PriceRepositoryHolder {
        private static final PriceRepository INSTANCE = new PriceRepository();
    }

    public static PriceRepository getInstance() {
        return PriceRepositoryHolder.INSTANCE;
    }


    public List<IPriceItemModel> mockItemList(boolean withChart) {

        List<IPriceItemModel> list;
        if (withChart) {
            list = new ArrayList<>(mockChartList());
            list.addAll(mockGoodPriceList(false));
        } else {
            list = new ArrayList<>(mockGoodPriceList(true));
        }


        return list;
    }


    private List<PriceItemChart> mockChartList() {
        List<PriceItemChart> list = new ArrayList<>();
        list.add(new PriceItemChart(true, mockGoodsType(true)));
        list.add(new PriceItemChart(false, mockGoodsType(true)));
        return list;
    }

    private List<PriceItemGoods> mockGoodPriceList(boolean withOrder) {
        List<PriceItemGoods> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PriceItemGoods goods = new PriceItemGoods();
            goods.setId(i);
            goods.setPrice(mockPrice(i % 2 == 0));
            goods.setName("宝石" + i);
            goods.setType(i);
            goods.setWithOrder(withOrder);
            goods.setCoverUrl("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2747464534,3370638099&fm=58&bpow=640&bpoh=640");
            list.add(goods);
        }
        return list;
    }


    private List<GoodsTypeModel> mockGoodsType(boolean increase) {
        List<GoodsTypeModel> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            GoodsTypeModel typeModel = new GoodsTypeModel();
            typeModel.setTypeId(i);
            switch (i % 3) {
                case 0:
                    typeModel.setTypeName("钻石");
                    break;
                case 1:
                    typeModel.setTypeName("翡翠");
                    break;
                case 2:
                    typeModel.setTypeName("玛瑙");
                    break;
                default:
                    break;
            }
            typeModel.setPriceList(mockPrice(increase));
            list.add(typeModel);
        }
        return list;
    }


    private List<PriceModel> mockPrice(boolean increase) {
        List<PriceModel> list = new ArrayList<>();
        for (int i = 9; i <= 12; i++) {
            PriceModel price = new PriceModel();
            price.setIncreasing(increase);
            price.setTime(i + "");
            price.setPrice(i % 2 == 0 ? 100000 + i * 3100 : 100000 - i * 310);
            price.setPriceRange(0.2346);
            price.setPriceSpeed(-0.5678);
            list.add(price);
        }
        return list;
    }


}
