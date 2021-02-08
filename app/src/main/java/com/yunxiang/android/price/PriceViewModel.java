package com.yunxiang.android.price;

import com.yunxiang.android.base.paging.PagingViewModel;
import com.yunxiang.android.base.paging.data.PagingDataSource;
import com.yunxiang.android.personal.data.Jade;
import com.yunxiang.android.personal.data.JadeRepository;


public class PriceViewModel extends PagingViewModel<Jade> {


    @Override
    protected PagingDataSource<Jade> getDataSource() {
        return new PagingDataSource<>(this, new JadeRepository());
    }
}
