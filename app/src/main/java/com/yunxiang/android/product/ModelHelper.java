package com.yunxiang.android.product;

import com.yunxiang.android.product.model.AssessModel;
import com.yunxiang.android.product.model.PublishModel;

import java.util.List;


public class ModelHelper {
    private static final int BUY_TYPE_ERROR = 0;
    private static final int BUY_TYPE_NO_BARGAIN = 1;
    private static final int BUY_TYPE_BIDDING = 2;
    private static final int BUY_TYPE_ALL = 3;

    public static PublishModel getPublishModel(String id, List<String> picsBean,
                                               String name, String desc, String stoneType,
                                               String buyType, String noBargainPrice,
                                               String biddingPrice, String startTime,
                                               String endTime) {
        PublishModel result = null;
        switch (Integer.valueOf(buyType)) {
            case BUY_TYPE_ALL:
                result = new PublishModel(id, picsBean, name, desc, stoneType, buyType,
                        noBargainPrice, biddingPrice, startTime, endTime);
                break;
            case BUY_TYPE_NO_BARGAIN:
                result = new PublishModel(id, picsBean, name, desc, stoneType, buyType, noBargainPrice);
                break;
            case BUY_TYPE_BIDDING:
                result = new PublishModel(id, picsBean, name, desc, stoneType, buyType, biddingPrice, startTime, endTime);
                break;
            case BUY_TYPE_ERROR:
                result = new PublishModel();
            default:
                break;
        }
        return result;
    }

    public static AssessModel getAssessModele(String name, String type, String appointTime,
                                              String phoneNum, String desc, List<String> pics) {
        return new AssessModel(name, type, appointTime, phoneNum, desc, pics);

    }
}
