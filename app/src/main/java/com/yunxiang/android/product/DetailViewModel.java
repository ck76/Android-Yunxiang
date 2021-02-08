package com.yunxiang.android.product;

import android.arch.lifecycle.MutableLiveData;

import com.neuqer.android.network.response.ApiResponse;
import com.yunxiang.android.base.BaseViewModel;
import com.yunxiang.android.network.consumer.ExceptionConsumer;
import com.yunxiang.android.network.consumer.NetworkConsumer;
import com.yunxiang.android.personal.data.PersonalRepository;
import com.yunxiang.android.personal.model.EvaluationModel;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.product.data.ProductRepository;

import io.reactivex.functions.Consumer;


public class DetailViewModel extends BaseViewModel {

    private MutableLiveData<EvaluationModel> mEvaluationDetail = new MutableLiveData<>();

    private MutableLiveData<JadeModel> mJadeDetail = new MutableLiveData<>();

    public void setEvaluationDetail(EvaluationModel evaluationDetail) {
        mEvaluationDetail.setValue(evaluationDetail);
    }

    public void setJadeDetail(JadeModel jadeDetail) {
        mJadeDetail.setValue(jadeDetail);
    }

    public MutableLiveData<EvaluationModel> getEvaluationDetail() {
        return mEvaluationDetail;
    }

    public MutableLiveData<JadeModel> getJadeDetail() {
        return mJadeDetail;
    }


    public void queryGoodsDetail() {
        if (mJadeDetail.getValue() == null) {
            return;
        }
        register(ProductRepository.getInstance()
                .getProductInfo(mJadeDetail.getValue().getId())
                .subscribe(jadeModel -> mJadeDetail.postValue(jadeModel), new ExceptionConsumer()));
    }

    public void queryAssessDetail() {
        if (mEvaluationDetail.getValue() != null) {
            register(ProductRepository.getInstance()
                    .getEvaluationInfo(mEvaluationDetail.getValue().getId())
                    .subscribe(evaluationModel -> mEvaluationDetail.postValue(evaluationModel), new ExceptionConsumer())
            );
        }
    }


}
