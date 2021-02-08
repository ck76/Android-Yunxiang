package com.yunxiang.android.personal.data;

import com.neuqer.android.network.HttpClient;
import com.neuqer.android.network.response.ApiResponse;
import com.yunxiang.android.base.paging.api.PagingCallBack;
import com.yunxiang.android.base.paging.api.PagingRepository;
import com.yunxiang.android.personal.PersonalConstants;
import com.yunxiang.android.personal.model.ImageModel;
import com.yunxiang.android.personal.model.JadeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalRepository implements PagingRepository {


    private int mListType;
    private PersonalApi mApi;

    private PersonalRepository() {
        mApi = HttpClient.getInstance().createService(PersonalApi.class);
    }

    private static class InstanceHolder {
        private static final PersonalRepository INSTANCE = new PersonalRepository();
    }

    public static PersonalRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setListType(int listType) {
        mListType = listType;
    }

    @Override
    public void getDataList(int offset, int count, PagingCallBack callBack) {
        try {
            switch (mListType) {
                case PersonalConstants.TYPE_EVALUATE:
                    getMyEvaluationList(offset, count, callBack);
                    break;
                default:
                    callBack.onSuccess(mock(offset, count));
                    break;
            }
        } catch (RuntimeException e) {
            callBack.onError(e);
        }
    }


    /**
     * 获取我的评估列表
     *
     * @param offset   起始点
     * @param count    数量
     * @param callBack paging回调
     */
    private void getMyEvaluationList(int offset, int count, PagingCallBack callBack) {
        mApi.getMyEvaluationList(null, null).enqueue(new Callback<ApiResponse<List<JadeModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<JadeModel>>> call, Response<ApiResponse<List<JadeModel>>> response) {
                if (response.body() != null) {
//                    callBack.onSuccess(response.body().getData());
                    callBack.onSuccess(mock(offset, count));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<JadeModel>>> call, Throwable t) {
                callBack.onError(new Exception(t));
            }
        });
    }

    private List<JadeModel> mock(int offset, int count) {
        List<JadeModel> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            JadeModel jade = new JadeModel();
            jade.setId(offset + i);
            jade.setStonePrice(123456 + i);
            jade.setBuyoutPrice(111111 + i);
            jade.setAuctionPrice(222222 + i);
            jade.setBuyType(2);
            jade.setGoodsName("商品商品商品商品商品" + jade.getId());
            jade.setStoneName("商品商品商品商品商品" + jade.getId());
            jade.setGoodPics(new ImageModel("http://img.hellofhy.cn/19-1-1/30261975.jpg"));
            jade.setStonePics(new ImageModel("http://img.hellofhy.cn/19-1-1/30261975.jpg"));
            jade.setGoodDescribe("aaaaaaaaaaaaaaaaaaaaaaaa");
            jade.setStoneDescription("aaaaaaaaaaaaaaaaaaaaaaaa");
            list.add(jade);
        }
        return list;
    }


    public JadeModel mockJade() {

        JadeModel jade = new JadeModel();
        jade.setId(1);
        jade.setStonePrice(123456);
        jade.setBuyoutPrice(111111);
        jade.setAuctionPrice(222222);
        jade.setBuyType(2);
        jade.setGoodsName("商品商品商品商品商品" + jade.getId());
        jade.setStoneName("商品商品商品商品商品" + jade.getId());
        jade.setGoodPics(new ImageModel("http://img.hellofhy.cn/19-1-1/30261975.jpg"));
        jade.setStonePics(new ImageModel("http://img.hellofhy.cn/19-1-1/30261975.jpg"));
        jade.setGoodDescribe("aaaaaaaaaaaaaaaaaaaaaaaa");
        jade.setStoneDescription("aaaaaaaaaaaaaaaaaaaaaaaa");

        return jade;
    }


}
