package com.yunxiang.android.personal.model;

import com.yunxiang.android.base.model.BaseModel;

import java.util.ArrayList;
import java.util.List;


public class ImageModel extends BaseModel {


    private List<String> imgUrls;

    public ImageModel(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public ImageModel(String imgUrl) {
        imgUrls = new ArrayList<>();
        imgUrls.add(imgUrl);
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
