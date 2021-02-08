package com.yunxiang.android.personal.data;

import com.neuqer.android.network.response.ApiResponse;
import com.yunxiang.android.personal.model.JadeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface PersonalApi {

    /**
     * 获取我的预约评估列表
     *
     * @param checkStatus 评估状态 : 送检中/已检验
     * @param checkRes    评估结果 : 通过/未通过
     * @return 预约列表
     */
    @GET("check/records/me")
    Call<ApiResponse<List<JadeModel>>> getMyEvaluationList(@Query("check_status") String checkStatus,
                                                          @Query("check_res") String checkRes);

}
