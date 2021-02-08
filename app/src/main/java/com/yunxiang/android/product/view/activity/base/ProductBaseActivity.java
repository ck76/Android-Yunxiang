package com.yunxiang.android.product.view.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.neuqer.android.permission.PermissionRequest;
import com.neuqer.android.permission.PermissionUtil;
import com.neuqer.android.ui.dialog.LoadingDialog;
import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseActivity;
import com.yunxiang.android.product.CheckFormatHelper;
import com.yunxiang.android.product.MatisseGlideEngine;
import com.yunxiang.android.product.ProductConstans;
import com.yunxiang.android.product.data.ProductRepository;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.yunxiang.android.product.ProductConstans.FILE_PROVIDER;
import static com.yunxiang.android.product.ProductConstans.REQUEST_CODE_CHOOSE;


public abstract class ProductBaseActivity extends BaseActivity {

    protected ProductRepository mRepository;
    protected CheckFormatHelper mFormatHelper;
    protected LoadingDialog mLoadingDialog;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mLoadingDialog = new LoadingDialog(this);
        mFormatHelper = new CheckFormatHelper(this);
        mRepository = ProductRepository.getInstance();
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    /**
     * 打开照片选择器
     *
     * @param adapterSurplus 剩余可选择数
     */
    protected void startMatisse(int adapterSurplus) {
        initPermission();

        boolean isPermissionGranted = false;
        for (String permission : ProductConstans.REQUEST_PERMISSIONS) {
            if (!PermissionUtil.isPermissionGranted(mContext, permission)) {
                ToastUtil.show(mContext, "请前往设置为云想开启访问照片权限");
                return;
            }
            isPermissionGranted = true;
        }
        if (isPermissionGranted) {
            Matisse.from((Activity) mContext)
                    .choose(MimeType.ofImage())
                    .countable(true)
                    .maxSelectable(adapterSurplus)
                    //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；
                    //参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                    .captureStrategy(new CaptureStrategy(true, FILE_PROVIDER))
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.product_grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new MatisseGlideEngine())
                    .forResult(REQUEST_CODE_CHOOSE);
        }
    }

    /**
     * 打开时间选择器
     *
     * @param context context
     * @param titile  选择器标题
     */
    protected void startTimePicker(Context context, String titile, OnTimeSelectListener listener) {

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        Date date = new Date();
        endDate.set(2099, 11, 31);

        TimePickerView timePickerView = new TimePickerBuilder(context, listener)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .setTitleText(titile)
                .setRangDate(startDate, endDate)
                .isDialog(true)
                .build();
        timePickerView.show();
    }

    /**
     * 商品品类选择器（一级）
     *
     * @param dataList 数据
     */
    protected void startCategoryPickerOne(Context context, String titile, List<String> dataList,
                                          OnOptionsSelectListener selectListener,
                                          OnOptionsSelectChangeListener changeListener) {

        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(context, selectListener)
                .setOptionsSelectChangeListener(changeListener)
                .setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText(titile)
//                .setSubCalSize(18)//确定和取消文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
//                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                .setContentTextSize(18)//滚轮文字大小
//               // .setLinkage(false)//设置是否联动，默认true
//                .setLabels("省", "市", "区")//设置选择的三级单位
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setCyclic(false, false, false)//循环与否
//                .setSelectOptions(1, 1, 1)  //设置默认选中项
//                .setOutSideCancelable(false)//点击外部dismiss default true
//                .isDialog(true)//是否显示为对话框样式
//                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isDialog(true)
                .build();
        optionsPickerView.setPicker(dataList);
        optionsPickerView.show();
    }

    @SuppressLint("ClickableViewAccessibility")
    protected void fixScrollEdit(int viewId, EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
                if ((v.getId() == viewId) && canVerticalScroll(editText)) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    protected boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;
        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    /**
     * 初始化权限
     */
    private void initPermission() {
        new PermissionRequest.Builder(mContext, ProductConstans.REQUEST_PERMISSIONS)
                .request();
    }
}
