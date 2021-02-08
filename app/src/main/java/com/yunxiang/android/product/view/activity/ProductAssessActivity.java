package com.yunxiang.android.product.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.neuqer.android.util.DateUtil;
import com.neuqer.android.util.ToastUtil;
import com.neuqer.android.util.UI;
import com.yunxiang.android.R;
import com.yunxiang.android.product.ModelHelper;
import com.yunxiang.android.product.ProductConstans;
import com.yunxiang.android.product.data.IProduct;
import com.yunxiang.android.product.model.AssessModel;
import com.yunxiang.android.product.view.activity.base.ProductBaseActivity;
import com.yunxiang.android.product.view.adapter.AddImageAdapter;
import com.yunxiang.android.product.view.adapter.GridSpacingItemDecoration;
import com.yunxiang.android.product.view.custom.SimpleItemView;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ProductAssessActivity extends ProductBaseActivity implements AddImageAdapter.OnItemClickedListener {

    @BindView(R.id.txt_product_title)
    TextView mToolBarTitleTxt;
    @BindView(R.id.img_product_back)
    ImageView mBackImage;
    @BindView(R.id.edit_product_input_detail)
    EditText mDetailEdit;
    @BindView(R.id.recycler_product_add)
    RecyclerView mAddImageRecyclerView;
    @BindView(R.id.btn_product_assess_assess)
    Button mAssessBtn;
    @BindView(R.id.item_product_name)
    SimpleItemView mNameItem;
    @BindView(R.id.item_product_category)
    SimpleItemView mCategoryItem;
    @BindView(R.id.item_product_appointment_time)
    SimpleItemView mAppointTimeItem;
    @BindView(R.id.item_product_phone_number)
    SimpleItemView mPhoneNumberItem;

    private AddImageAdapter mAddImageAdapter;
    private List<Uri> mSelectedPics;
    private List<String> mResultPics;
    //picker用
    private String mProductType;
    private long mAppointTime;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_product_assess;
    }

    @Override
    protected void initVariable() {
        //TODO 商品品类待设置，需要和产品商量 后端接口联系人phoneNum字段
        //TODO 图片上传地址待商定
        //TODO 商品品类待确定
        initProductInfo();
    }

    /**
     * 初始化商品信息
     */
    private void initProductInfo() {
        mSelectedPics = new ArrayList<>();
        mResultPics = new ArrayList<>();

    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @Override
    protected void initView() {
        initToolBar();
        initPickerView();
        initAddList();
        fixScrollEdit(R.id.edit_product_input_detail, mDetailEdit);
    }

    private void initToolBar() {
        mBackImage.setOnClickListener(view -> finish());
        mToolBarTitleTxt.setText(R.string.product_title_assessment);
    }

    private void initPickerView() {
        List<String> list = new ArrayList<>(Arrays.asList(ProductConstans.PRODUCT_CATEGORY));

        mCategoryItem.setOnIconClickListener(new SimpleItemView.OnIconClickListener() {
            @Override
            public void onClick() {
                startCategoryPickerOne(mContext, "选择商品品类", list, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        mProductType = list.get(options1);
                        mCategoryItem.setContent(list.get(options1));
                    }
                }, new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {

                    }
                });
            }
        });
        mAppointTimeItem.setOnIconClickListener(new SimpleItemView.OnIconClickListener() {
            @Override
            public void onClick() {
                startTimePicker(mContext, "请选择评估时间", new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mAppointTime = DateUtil.getTimeStamp(date);
                        mAppointTimeItem.setContent(DateUtil.getFormatDateDay(date));
                    }
                });
            }
        });
    }

    private void initAddList() {
        mAddImageAdapter = new AddImageAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mAddImageRecyclerView.setLayoutManager(manager);
        mAddImageRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4,
                UI.dp2px(this, 8), true));
        mAddImageRecyclerView.setNestedScrollingEnabled(false);
        mAddImageRecyclerView.setAdapter(mAddImageAdapter);
        mAddImageAdapter.setMax(9);
        mAddImageAdapter.setOnItemClickedListener(this);
    }

    @OnClick({R.id.btn_product_assess_assess})
    public void OnCLick(View view) {
        switch (view.getId()) {
            case R.id.btn_product_assess_assess:
                assess();
//                startPublishActivity();
                break;
            default:
                break;
        }
    }

    /**
     * 预约评估
     */
    public void assess() {
        List<String> pics = mResultPics;
        String name = mNameItem.getContent().trim();
        String type = mProductType;
        String desc = mDetailEdit.getText().toString().trim();
        String appointTime = String.valueOf(mAppointTime);
        String phoneNum = mPhoneNumberItem.getContent().trim();

        AssessModel model = ModelHelper.getAssessModele(name, type, appointTime, phoneNum, desc, pics);

        if (mFormatHelper.checkAssessInfo(model)) {
            mRepository.assessProduct(model, new IProduct.IAssessListener() {
                @Override
                public void assess(int status) {
                    ToastUtil.show(ProductAssessActivity.this, "预约发布成功");
                    finish();
                }

                @Override
                public void onError(int status) {
                }
            });
        }
    }


    /**
     * AddImageAdapter回调接口
     */
    @Override
    public void onContentViewClicked(View view) {

    }

    @Override
    public void onAddViewClicked(View view) {
        startMatisse(mAddImageAdapter.getSurplus());
    }

    @Override
    public void onDeleteViewClicked(View view, int pos) {

    }

    /**
     * matisse选择图片
     * 进行图片数量超限处理
     *
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param data        数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProductConstans.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelectedPics = Matisse.obtainResult(data);
            Log.i("ck", "mSelectedPics: " + mSelectedPics);
            mAddImageAdapter.addImage(mSelectedPics);
        }
    }


    /**
     * test
     */
    private void startPublishActivity() {
        ArrayList<String> mInitailPics = new ArrayList<>();
        mInitailPics.add("http://upload.hljtv.com/2014/0306/1394105326974.jpg");
        mInitailPics.add("http://upload.hljtv.com/2014/0306/1394105326974.jpg");
        ProductPublishActivity.startActivity(this, "ck一号", "10086", mInitailPics, "ck1重");
    }
}
