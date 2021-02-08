package com.yunxiang.android.product.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.neuqer.android.util.DateUtil;
import com.neuqer.android.util.UI;
import com.yunxiang.android.R;
import com.yunxiang.android.product.ModelHelper;
import com.yunxiang.android.product.ProductConstans;
import com.yunxiang.android.product.data.IProduct;
import com.yunxiang.android.product.model.PublishModel;
import com.yunxiang.android.product.view.activity.base.ProductBaseActivity;
import com.yunxiang.android.product.view.adapter.AddImageAdapter;
import com.yunxiang.android.product.view.adapter.GridSpacingItemDecoration;
import com.yunxiang.android.product.view.custom.SimpleItemView;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class ProductPublishActivity extends ProductBaseActivity implements
        AddImageAdapter.OnItemClickedListener {
    @BindView(R.id.recycler_product_add)
    RecyclerView mAddImageRecyclerView;
    @BindView(R.id.edit_product_input_detail)
    EditText mDetailEdit;
    @BindView(R.id.btn_product_publish_publish)
    Button mPublishBtn;
    @BindView(R.id.txt_product_title)
    TextView mToolBarTitleTxt;
    @BindView(R.id.img_product_back)
    ImageView mBackImage;
    @BindView(R.id.item_product_name)
    SimpleItemView mNameItem;
    @BindView(R.id.item_product_id)
    SimpleItemView mIdItem;
    @BindView(R.id.item_product_price)
    SimpleItemView mPriceItem;
    @BindView(R.id.item_product_bidding_price)
    SimpleItemView mBiddingPriceItem;
    @BindView(R.id.item_product_bidding_time)
    SimpleItemView mBiddingTimeItem;
    @BindView(R.id.checkbox_product_no_bargain)
    CheckBox mNoBargainCheckbox;
    @BindView(R.id.checkbox_product_bidding)
    CheckBox mBiddingCheckbox;
    @BindView(R.id.ll_product_no_bargain)
    LinearLayout mNoBargainLl;
    @BindView(R.id.ll_product_bidding)
    LinearLayout mBiddingLl;

    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_INIT_PICS = "productInitPics";
    private static final String PRODUCT_TYPE = "productType";

    private AddImageAdapter mAddImageAdapter;
    private boolean mNoBargainState;
    private boolean mBiddingState;
    private List<Uri> mSelectedPics;
    private List<String> mInitailPics;
    private List<String> mResultPics;
    //picker用
    private long mStartTime;
    private long mEndTime;
    //初始化
    private String mId;
    private String mName;
    private String mStoneType;


    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_product_publish;
    }

    @Override
    protected void initVariable() {
        //TODO 根据我的评估页面传过来的数据进行页面初始化 id + name + 预设置图片 + type
        initProductInfo();
    }

    /**
     * 初始化商品信息
     */
    private void initProductInfo() {
        mSelectedPics = new ArrayList<>();
        mInitailPics = new ArrayList<>();
        mResultPics = new ArrayList<>();

        Intent intent = getIntent();
        mId = intent.getStringExtra(PRODUCT_ID);
        mName = intent.getStringExtra(PRODUCT_NAME);
        mInitailPics = intent.getStringArrayListExtra(PRODUCT_INIT_PICS);
        mStoneType = intent.getStringExtra(PRODUCT_TYPE);

        mIdItem.setContent(mId);
        mNameItem.setContent(mName);
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initToolBar();
        initAddList();
        initPickerView();
        fixScrollEdit(R.id.edit_product_input_detail, mDetailEdit);
    }


    private void initToolBar() {
        mBackImage.setOnClickListener(view -> finish());
        mToolBarTitleTxt.setText(R.string.product_title_publish);
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
        mAddImageAdapter.setInitailPics(mInitailPics);
        mAddImageAdapter.setOnItemClickedListener(this);
    }

    /**
     * 两次弹出对话框，选择竞价开始和结束时间
     * 并更新界面
     */
    private void initPickerView() {
        mBiddingTimeItem.setOnIconClickListener(new SimpleItemView.OnIconClickListener() {
            @Override
            public void onClick() {
                startTimePicker(mContext, "请选择竞价开始时间", new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mStartTime = DateUtil.getTimeStamp(date);
                        startTimePicker(mContext, "请选择竞价结束时间", new OnTimeSelectListener() {
                            @Override
                            public void onTimeSelect(Date date, View v) {
                                mEndTime = DateUtil.getTimeStamp(date);
                                mBiddingTimeItem.setContent(DateUtil.stampToDateMinutes(mStartTime)
                                        + "  至  "
                                        + DateUtil.stampToDateMinutes(mEndTime));
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * checkbox点击切换交易模式(可双选)
     */
    @OnCheckedChanged({R.id.checkbox_product_no_bargain, R.id.checkbox_product_bidding})
    public void onBoxClicked(CompoundButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.checkbox_product_no_bargain:
                if (isChecked) {
                    mNoBargainState = true;
                    mNoBargainLl.setVisibility(View.VISIBLE);
                } else {
                    mNoBargainState = false;
                    mNoBargainLl.setVisibility(View.GONE);
                }
                break;
            case R.id.checkbox_product_bidding:
                if (isChecked) {
                    mBiddingState = true;
                    mBiddingLl.setVisibility(View.VISIBLE);
                } else {
                    mBiddingState = false;
                    mBiddingLl.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.btn_product_publish_publish})
    public void OnCLick(View view) {
        switch (view.getId()) {
            case R.id.btn_product_publish_publish:
                publish();
                startActivity(ProductAssessActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 发布商品
     */
    private void publish() {
        String id = mId;
        String name = mNameItem.getContent().trim();
        String desc = mDetailEdit.getText().toString().trim();
        String stoneType = mStoneType;
        String buyType = String.valueOf(ProductConstans.BuyType.getBuyType(mNoBargainState, mBiddingState));
        String noBargainPrice = mPriceItem.getContent().trim();
        String biddingPrice = mBiddingPriceItem.getContent().trim();
        String startTime = String.valueOf(mStartTime);
        String endTime = String.valueOf(mEndTime);

        PublishModel model = ModelHelper.getPublishModel(id, new LinkedList<String>(),
                name, desc, stoneType, buyType, noBargainPrice, biddingPrice, startTime, endTime);

        if (mFormatHelper.checkPublishInfo(model)) {
            mRepository.publishProduct(model, new IProduct.IPublishListener() {
                @Override
                public void publish(int status) {
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

    public static void startActivity(Context context, String name, String id,
                                     ArrayList<String> pics,
                                     String type) {
        Intent intent = new Intent(context, ProductPublishActivity.class);
        intent.putExtra(PRODUCT_NAME, name);
        intent.putExtra(PRODUCT_ID, id);
        intent.putStringArrayListExtra(PRODUCT_INIT_PICS, pics);
        intent.putExtra(PRODUCT_TYPE, type);
        context.startActivity(intent);
    }
}
