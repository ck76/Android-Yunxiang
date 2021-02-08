

package com.yunxiang.android.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.neuqer.android.ui.DynamicNumImgView;
import com.neuqer.android.ui.banner.Banner;
import com.neuqer.android.ui.banner.loader.ImageLoader;
import com.neuqer.android.ui.pagergrid.PageGridAdapter;
import com.yunxiang.android.R;
import com.yunxiang.android.home.model.HomeActivityModel;
import com.yunxiang.android.home.model.HomeBannerModel;
import com.yunxiang.android.home.model.HomeCategoryModel;
import com.yunxiang.android.home.model.HomeCommodity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.neuqer.android.ui.pagergrid.HorizontalGridPage;
import com.neuqer.android.ui.pagergrid.PageBuilder;
import com.neuqer.android.util.UI;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /** Banner */
    private static final int TYPE_BANNER = 0;
    /** 商品分类 */
    private static final int TYPE_CATEGORY = 1;
    /** 活动 */
    private static final int TYPE_ACTIVITY = 2;
    /** 活动 */
    private static final int TYPE_COMMODITY = 3;
    /** 首页Banner数据 */
    private HomeBannerModel mHomeBanner;
    /** 首页商品分类数据 */
    private HomeCategoryModel mHomeCategory;
    /** 首页活动数据 */
    private HomeActivityModel mHomeActivity;
    /** 首页Banner List数据 */
    private List<HomeBannerModel.BannerModel> mBannerList;
    /** 首页商品分类 List数据 */
    private List<HomeCategoryModel.CategoryModel> mCategoryList;
    /** 首页活动 List数据 */
    private List<HomeActivityModel.Activity> mActivityList;
    /** 首页商品数据 */
    private List<HomeCommodity> mCommodityList;
    /** 上下文 */
    private Context mContext;
    /** 4dp对应的px值 */
    private int dp4;
    /** 8dp对应的px值 */
    private int dp8;

    public HomeAdapter(Context context,
                       HomeBannerModel homeBanner,
                       HomeCategoryModel homeCategory,
                       HomeActivityModel homeActivity,
                       List<HomeCommodity> commodityList) {
        mContext = context;
        mHomeBanner = homeBanner;
        mHomeCategory = homeCategory;
        mHomeActivity = homeActivity;
        mCommodityList = commodityList;
        if (mHomeBanner != null) {
            mBannerList = mHomeBanner.getHomeBannerList();
        }
        if (mHomeCategory != null) {
            mCategoryList = mHomeCategory.getCategoryList();
        }
        if (mHomeActivity != null) {
            mActivityList = mHomeActivity.getActivityList();
        }
        dp4 = UI.dp2px(mContext, 4);
        dp8 = UI.dp2px(mContext, 8);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        switch (viewType) {
            case TYPE_BANNER:
                view = inflater.inflate(R.layout.item_home_banner, parent, false);
                return new BannerViewHolder(view);
            case TYPE_CATEGORY:
                view = inflater.inflate(R.layout.item_home_category, parent, false);
                return new CategoryViewHolder(view);
            case TYPE_ACTIVITY:
                view = inflater.inflate(R.layout.item_home_activity, parent, false);
                return new ActivityViewHolder(view);
            case TYPE_COMMODITY:
                view = inflater.inflate(R.layout.item_home_commodity, parent, false);
                return new CommodityViewHolder(view);
            default:
                throw new IllegalStateException("Home ViewType 异常");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof BannerViewHolder) {
            onBindBannerViewHolder((BannerViewHolder) viewHolder);
        } else if (viewHolder instanceof CategoryViewHolder) {
            onBindCategoryViewHolder((CategoryViewHolder) viewHolder);
        } else if (viewHolder instanceof ActivityViewHolder) {
            onBindActivityViewHolder((ActivityViewHolder) viewHolder);
        } else if (viewHolder instanceof CommodityViewHolder) {
            int realPos = position - getCommodityPosition();
            HomeCommodity commodity = mCommodityList.get(realPos);
            if (realPos % 2 == 0) {
                viewHolder.itemView.setPadding(dp8, 0, dp4, dp8);
            } else {
                viewHolder.itemView.setPadding(dp4, 0, dp8, dp8);
            }
            onBindCommodityViewHolder((CommodityViewHolder) viewHolder, commodity);
        } else {
            throw new IllegalStateException("Home viewHolder 异常");
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        count += (mBannerList != null && mBannerList.size() > 0) ? 1 : 0;
        count += (mCategoryList != null && mCategoryList.size() > 0) ? 1 : 0;
        count += (mActivityList != null && mActivityList.size() > 0) ? 1 : 0;
        count += (mCommodityList != null && mCommodityList.size() > 0) ? mCommodityList.size() : 0;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getBannerPosition()) {
            return TYPE_BANNER;
        } else if (position == getCategoryPosition()) {
            return TYPE_CATEGORY;
        } else if (position == getActivityPosition()) {
            return TYPE_ACTIVITY;
        } else {
            return TYPE_COMMODITY;
        }
    }

    public int getBannerPosition() {
        if (!isBannerExist()) {
            return -1;
        }
        return 0;
    }

    public int getCategoryPosition() {
        if (!isCategoryExist()) {
            return -1;
        }
        int preCount = 0;
        preCount += isBannerExist() ? 1 : 0;
        return preCount;
    }

    public int getActivityPosition() {
        if (!isActivityExist()) {
            return -1;
        }
        int preCount = 0;
        preCount += isBannerExist() ? 1 : 0;
        preCount += isCategoryExist() ? 1 : 0;
        return preCount;
    }

    public int getCommodityPosition() {
        if (!isCommodityExist()) {
            return -1;
        }
        int preCount = 0;
        preCount += isBannerExist() ? 1 : 0;
        preCount += isCategoryExist() ? 1 : 0;
        preCount += isActivityExist() ? 1 : 0;
        return preCount;
    }

    private void onBindBannerViewHolder(BannerViewHolder viewHolder) {
        Banner banner = viewHolder.mBanner;
        banner.setBannerStyle(mHomeBanner.getBannerType());
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        List<String> img = new ArrayList<>();
        List<String> title = new ArrayList<>();
        for (HomeBannerModel.BannerModel model : mBannerList) {
            img.add(model.getUrl());
            title.add(model.getTitle());
        }
        banner.setImages(img);
        banner.setBannerTitles(title);
        banner.isAutoPlay(true);
        banner.setDelayTime(mHomeBanner.getBannerInternal());
        banner.start();
    }

    private void onBindCategoryViewHolder(CategoryViewHolder viewHolder) {
        HorizontalGridPage category = viewHolder.mCategory;
        PageBuilder pageBuilder = new PageBuilder.Builder()
                .setGrid(mHomeCategory.getRow(), mHomeCategory.getColumn())
                .setIndicatorRes(android.R.drawable.presence_invisible, android.R.drawable.presence_online)
                .setIndicatorGravity(Gravity.CENTER)
                .setSwipePercent(50)
                .setShowIndicator(true)
                .build();
        category.init(pageBuilder);
        HomeCategoryAdapter mCategoryAdapter = new HomeCategoryAdapter(mContext, pageBuilder);
        category.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setData(mHomeCategory.getCategoryList());
        mCategoryAdapter.setOnItemClickListener(new PageGridAdapter.OnItemClickListener<HomeCategoryModel.CategoryModel>() {
            @Override
            public void onClick(View v, HomeCategoryModel.CategoryModel model) {
                Toast.makeText(mContext, "点击：" + model.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onBindActivityViewHolder(ActivityViewHolder viewHolder) {
        DynamicNumImgView activity = viewHolder.mActivity;
        activity.setImgList(mHomeActivity.getActivityList(), new DynamicNumImgView.ImgLoader() {
            @Override
            public void loadImg(Context context, String path, ImageView imageView) {
                Glide.with(context)
                        .load(path)
                        .apply(RequestOptions.centerCropTransform())
                        .into(imageView);
            }
        });
        activity.setOnImgClickListener(new DynamicNumImgView.OnImgClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(mContext, "点击：" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onBindCommodityViewHolder(CommodityViewHolder viewHolder, HomeCommodity commodity) {
        ImageView pic = viewHolder.mCommodityPic;
        TextView title = viewHolder.mCommodityTitle;
        TextView price = viewHolder.mCommodityPrice;
        Glide.with(mContext).load(commodity.getImg()).into(pic);
        title.setText(commodity.getTitle());
        price.setText(String.valueOf(commodity.getPrice()));
    }

    private boolean isBannerExist() {
        return mBannerList != null && mBannerList.size() > 0;
    }

    private boolean isCategoryExist() {
        return mCategoryList != null && mCategoryList.size() > 0;
    }

    private boolean isActivityExist() {
        return mActivityList != null && mActivityList.size() > 0;
    }

    private boolean isCommodityExist() {
        return mCommodityList != null && mCommodityList.size() > 0;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_banner)
        Banner mBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_category)
        HorizontalGridPage mCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_activity)
        DynamicNumImgView mActivity;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CommodityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_commodity_pic)
        ImageView mCommodityPic;
        @BindView(R.id.txt_commodity_title)
        TextView mCommodityTitle;
        @BindView(R.id.txt_commodity_price)
        TextView mCommodityPrice;

        public CommodityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
