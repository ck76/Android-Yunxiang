
package com.yunxiang.android.home.data;

import com.neuqer.android.ui.banner.BannerConfig;
import com.yunxiang.android.home.model.HomeActivityModel;
import com.yunxiang.android.home.model.HomeBannerModel;
import com.yunxiang.android.home.model.HomeCategoryModel;

import java.util.ArrayList;
import java.util.List;



public class HomePresetData {

    private static final int BANNER_TYPE = BannerConfig.NUM_INDICATOR_TITLE;
    private static final String BANNER_TITLE = "默认标题，可以加活动介绍";
    private static final String BANNER_URL = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2275563804,3196691498&fm=26&gp=0.jpg";
    private static final String BANNER_CMD = "";

    private static final String CATEGORY_URL = "https://timgsa.baidu" +
            ".com/timg?image&quality=80&size=b9999_10000&sec=1539714578381&di=e8aeac86b9f6cca216a1f25849b7da63&imgtype=0&src=http%3A%2F%2Fimg62.nipic.com%2Ffile%2F20150314%2F4949133_101345674000_1.jpg";

    private static HomeBannerModel sHomeBannerModel;
    private static HomeCategoryModel sHomeCategoryModel;
    private static HomeActivityModel sHomeActivityModel;

    static {
        List<HomeBannerModel.BannerModel> bannerModelList = new ArrayList<>();
        HomeBannerModel.BannerModel bannerModel = new HomeBannerModel.BannerModel(BANNER_TITLE, BANNER_URL, BANNER_CMD);
        bannerModelList.add(bannerModel);
        bannerModelList.add(bannerModel);
        sHomeBannerModel = new HomeBannerModel(BANNER_TYPE, bannerModelList);

        // 设置首页分类数据
        List<HomeCategoryModel.CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "玛瑙", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "砖石", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "碧玉", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "欧珀", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "翡翠", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "和田玉", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "蜜蜡", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "其它", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "其它", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "其它", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "其它", CATEGORY_URL, ""));
        categoryList.add(new HomeCategoryModel.CategoryModel(0, "其它", CATEGORY_URL, ""));
        sHomeCategoryModel = new HomeCategoryModel(categoryList);
    }

    public static HomeBannerModel getHomeBannerModel() {
        return sHomeBannerModel;
    }

    public static HomeCategoryModel getHomeCategoryModel() {
        return sHomeCategoryModel;
    }

    public static HomeActivityModel getHomeActivityModel() {
        return sHomeActivityModel;
    }
}
