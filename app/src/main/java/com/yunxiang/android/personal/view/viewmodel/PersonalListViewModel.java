package com.yunxiang.android.personal.view.viewmodel;

import com.yunxiang.android.base.paging.PagingViewModel;
import com.yunxiang.android.base.paging.data.PagingDataSource;
import com.yunxiang.android.personal.data.PersonalRepository;
import com.yunxiang.android.personal.model.JadeModel;


public class PersonalListViewModel extends PagingViewModel<JadeModel> {

    @Override
    protected PagingDataSource<JadeModel> getDataSource() {
        return new PagingDataSource<>(this, PersonalRepository.getInstance());
    }


}
