
package com.yunxiang.android.account.data;

import com.google.gson.JsonObject;
import com.neuqer.android.network.response.ApiResponse;
import com.yunxiang.android.account.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountApi {

    /**
     * 判断手机号是否已经注册
     */
    @GET("user/issignedup/{phoneNum}")
    Call<ApiResponse<JsonObject>> checkHasSigned(@Path("phoneNum") String phoneNum);

    /**
     * 注册时获取验证码
     */
    @FormUrlEncoded
    @POST("user/signup/getverificationcode")
    Call<ApiResponse<Void>> getRegisterVerify(@Field("phone") String phoneNumber);

    /**
     * 携带验证码注册
     */
    @FormUrlEncoded
    @POST("user/signup/signup")
    Call<ApiResponse<JsonObject>> register(@Field("phone") String phoneNumber,
                                           @Field("verificationCode") String code,
                                           @Field("password") String password);

    /**
     * 登录时获取验证码
     */
    @FormUrlEncoded
    @POST("user/logininwithcode/getcode")
    Call<ApiResponse<Void>> getLoginVerify(@Field("phone") String phoneNumber);

    /**
     * 更改密码时获取验证码
     */
    @FormUrlEncoded
    @POST("user/alterpassword/getverificationCode")
    Call<ApiResponse<Void>> getResetVerify(@Field("phone") String phoneNumber);

    /**
     * 修改密码时检查验证码是否正确
     */
    @FormUrlEncoded
    @POST("user/alterpassword/checkverificationcode")
    Call<ApiResponse<Void>> verify(@Field("phone") String phoneNumber,
                                   @Field("verificationCode") String code);

    /**
     * 携带验证码更改密码
     */
    @FormUrlEncoded
    @POST("user/alterpassword")
    Call<ApiResponse<Void>> resetPassword(@Field("phone") String phoneNumber,
                                          @Field("verificationCode") String code,
                                          @Field("password") String password);

    /**
     * 手机号+验证码登录
     */
    @FormUrlEncoded
    @POST("user/logininwithcode/loginwithcode")
    Call<ApiResponse<JsonObject>> loginWithCode(@Field("phone") String phoneNumber,
                                                @Field("verificationCode") String code);

    /**
     * 手机号+密码登录
     */
    @FormUrlEncoded
    @POST("user/passwordlogin/passwordlogin")
    Call<ApiResponse<JsonObject>> loginWithPwd(@Field("phone") String phoneNumber,
                                               @Field("password") String password);

    /**
     * 根据手机号获取用户所有信息
     */
    @GET("user/{phoneNum}")
    Call<ApiResponse<User>> getUserInfo(@Path("phoneNum") String phoneNumber);

}
