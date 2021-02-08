package com.yunxiang.android.personal.data;

import com.yunxiang.android.base.paging.api.PagingCallBack;
import com.yunxiang.android.base.paging.api.PagingRepository;
import com.yunxiang.android.search.data.ResultSortManager;

import java.util.ArrayList;
import java.util.List;

public class JadeRepository implements PagingRepository {

    @Override
    public void getDataList(int offset, int count, PagingCallBack callBack) {
        List<Jade> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Jade jade = new Jade();
            jade.setId(offset + i);
            jade.setPrice(123456 + i);
            jade.setTitle("商品商品商品商品商品" + jade.getId());
//            jade.set
            jade.setCover("http://img.hellofhy.cn/19-1-1/30261975.jpg");
            if (i % 2 == 0) {
                jade.setRange(0.55 + i * 0.01);
                jade.setSpeed(0.66 + i * 0.01);
                jade.setTradeType(ResultSortManager.TRADE_TYPE_BUYOUT);
            } else {
                jade.setRange(0.55 - i * 0.01);
                jade.setSpeed(0.66 - i * 0.01);
                jade.setTradeType(ResultSortManager.TRADE_TYPE_AUCTION);
            }
            list.add(jade);
        }
        callBack.onSuccess(list);
    }
}
