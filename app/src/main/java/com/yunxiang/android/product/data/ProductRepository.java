package com.yunxiang.android.product.data;

import com.neuqer.android.network.HttpClient;
import com.neuqer.android.network.response.ApiResponse;
import com.yunxiang.android.personal.model.EvaluationModel;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.product.model.AssessModel;
import com.yunxiang.android.product.model.PublishModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class ProductRepository {

    /**
     * 请求成功状态码
     */
    public static final int STATUS_CODE_OK = 0;

    private ProductApi mApi;

    private ProductRepository(ProductApi api) {
        mApi = api;
    }

    private static class ProductDataManagerHolder {
        private static final ProductRepository INSTANCE = new ProductRepository(
                HttpClient.getInstance().createService(ProductApi.class)
        );
    }

    public static ProductRepository getInstance() {
        return ProductDataManagerHolder.INSTANCE;
    }


    /**
     * 发布商品
     *
     * @param publishModel 发布信息
     * @param listener     回调
     */
    public void publishProduct(PublishModel publishModel, IProduct.IPublishListener listener) {
        mApi.publishProduct(publishModel)
                .enqueue(new ApiCallBack<Void>(listener) {
                    @Override
                    void onDataBack(ApiResponse<Void> response) {
                        listener.publish(response.getCode());
                    }
                });
    }

    /**
     * 预约评估
     *
     * @param assessModel 评估信息
     * @param listener    回调
     */
    public void assessProduct(AssessModel assessModel, IProduct.IAssessListener listener) {
        mApi.assessProduct(assessModel)
                .enqueue(new ApiCallBack<Void>(listener) {
                    @Override
                    void onDataBack(ApiResponse<Void> response) {
                        listener.assess(response.getCode());
                    }
                });
    }


    /**
     * 获取产品详情
     *
     * @param goodsId 商品id
     * @return 商品详情
     */
    public Observable<JadeModel> getProductInfo(int goodsId) {
        return mApi.getProductInfo(goodsId)
                .map(ApiResponse::getData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取预约评估详情
     *
     * @param checkId 评估id
     * @return 评估记录详情
     */
    public Observable<EvaluationModel> getEvaluationInfo(int checkId) {
        return mApi.getAssessInfo(checkId)
                .map(ApiResponse::getData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
