package com.yunxiang.android.product.data;

import com.neuqer.android.network.response.ApiResponse;
import com.yunxiang.android.personal.model.EvaluationModel;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.product.model.AssessModel;
import com.yunxiang.android.product.model.PublishModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ProductApi {
    /**
     * 发布商品
     *
     * @param publishModel 发布商品信息
     */
    @POST("good/sell")
    Call<ApiResponse<Void>> publishProduct(@Body PublishModel publishModel);

    /**
     * 预约评估
     *
     * @param assessModel 评估商品信息
     */
    @POST("check/create")
    Call<ApiResponse<Void>> assessProduct(@Body AssessModel assessModel);


    /**
     * 获取商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    @GET("good/detail")
    Observable<ApiResponse<JadeModel>> getProductInfo(@Query("good_id") int id);


    /**
     * 获取预约评估详情
     *
     * @param id 预约评估id
     * @return 预约评估详情
     */
    Observable<ApiResponse<EvaluationModel>> getAssessInfo(@Query("check_id") int id);

}
