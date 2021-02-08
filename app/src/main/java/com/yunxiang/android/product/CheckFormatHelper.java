package com.yunxiang.android.product;

import android.content.Context;
import android.text.TextUtils;

import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;
import com.yunxiang.android.product.model.AssessModel;
import com.yunxiang.android.product.model.PublishModel;

import java.util.List;


public class CheckFormatHelper {

    private static final int BUY_TYPE_ERROR = 0;
    private static final int BUY_TYPE_NO_BARGAIN = 1;
    private static final int BUY_TYPE_BIDDING = 2;
    private static final int BUY_TYPE_ALL = 3;

    private Context mContext;

    public CheckFormatHelper(Context context) {
        mContext = context;
    }

    /**
     * 检验发布商品信息格式(除空外任何格式)
     *
     * @param model 商品名称(非空)
     * @param model 购买模式(一口价，竞价，竞价+一口价)
     * @param model 一口价价格(小数点后两位)
     * @param model 竞价起拍价小数点后两位)
     * @param model 竞价期限(精确到分钟)
     * @param model 竞价结束时间(精确到分钟)
     * @return 是否正确
     */
    public boolean checkPublishInfo(PublishModel model) {

        String productName = model.getGoodsName();
        String buyType = model.getBuyType();
        String noBargainPrice = model.getPrice1();
        String biddingPrice = model.getPrice2();
        String startTime = model.getStartTime();
        String endTime = model.getEndTime();

        if (TextUtils.isEmpty(productName)) {
            ToastUtil.show(mContext, R.string.product_name_empty_error);
            return false;
        }
        switch (Integer.valueOf(buyType)) {
            case BUY_TYPE_ERROR:
                ToastUtil.show(mContext, "请选择竞价模式");
                return false;
            case BUY_TYPE_ALL:
                if (TextUtils.isEmpty(noBargainPrice)) {
                    ToastUtil.show(mContext, "请输入一口价金额");
                    return false;
                } else if (TextUtils.isEmpty(biddingPrice)) {
                    ToastUtil.show(mContext, "请输入竞价起拍价");
                    return false;
                } else if (TextUtils.isEmpty(String.valueOf(startTime))) {
                    ToastUtil.show(mContext, "请选择竞价开始时间");
                    return false;
                } else if (TextUtils.isEmpty(String.valueOf(endTime))) {
                    ToastUtil.show(mContext, "请选择竞价结束时间");
                    return false;
                } else if (Long.valueOf(endTime) < Long.valueOf(startTime)) {
                    ToastUtil.show(mContext, "请选择正确的竞价时间段");
                    return false;
                }
                break;
            case BUY_TYPE_NO_BARGAIN:
                if (TextUtils.isEmpty(noBargainPrice)) {
                    ToastUtil.show(mContext, "请输入一口价金额");
                    return false;
                }
                break;
            case BUY_TYPE_BIDDING:
                if (TextUtils.isEmpty(biddingPrice)) {
                    ToastUtil.show(mContext, "请输入竞价起拍价");
                    return false;
                } else if (TextUtils.isEmpty(String.valueOf(startTime))) {
                    ToastUtil.show(mContext, "请选择竞价开始时间");
                    return false;
                } else if (TextUtils.isEmpty(String.valueOf(endTime))) {
                    ToastUtil.show(mContext, "请选择竞价结束时间");
                    return false;
                } else if (TextUtils.isEmpty(String.valueOf(endTime))) {
                    ToastUtil.show(mContext, "请选择正确的竞价时间段");
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 检验预约评估信息
     *
     * @param model 商品名称
     * @param model 商品品类
     * @param model 预约时间
     * @param model 联系电话
     * @param model 图片
     * @return
     */
    public boolean checkAssessInfo(AssessModel model) {

        String productName = model.getName();
        String productType = model.getType();
        String appontTime = model.getCheckTime();
        String phoneNum = model.getPhoneNum();
        List pics = model.getPics().getPicList();

        if (TextUtils.isEmpty(productName)) {
            ToastUtil.show(mContext, R.string.product_name_empty_error);
            return false;
        } else if (TextUtils.isEmpty(productType)) {
            ToastUtil.show(mContext, "请选择商品品类");
            return false;
        } else if (TextUtils.isEmpty(appontTime)) {
            ToastUtil.show(mContext, "请选择预约时间");
            return false;
        } else if (TextUtils.isEmpty(phoneNum)) {
            ToastUtil.show(mContext, "请输入联系方式");
            return false;
        } else if (pics == null || pics.size() == 0) {
            ToastUtil.show(mContext, "请选择图片");
            return false;
        }
        return true;
    }

}
