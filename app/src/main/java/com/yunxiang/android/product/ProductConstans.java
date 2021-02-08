package com.yunxiang.android.product;

import android.Manifest;


public class ProductConstans {

    /**
     * 权限
     */
    public static String[] REQUEST_PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    /**
     * 商品品类
     */
    public static String[] PRODUCT_CATEGORY = {"ck1", "ck2"};

    /**
     * 图片&视频选择请求码
     **/
    public static int REQUEST_CODE_CHOOSE = 100;

    /**
     * FileProvider 用于matisse
     **/
    public static String FILE_PROVIDER = "com.yunxiang.android.fileprovider";



    public enum BuyType {
        /**
         * 错误，请选择
         */
        error(0),
        /**
         * 一口价
         **/
        noBargain(1),
        /**
         * 竞价
         **/
        bidding(2),
        /**
         * 竞价+一口价
         **/
        both(3);

        int status;

        BuyType(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public static int getBuyType(boolean isNoBargain, boolean isBdinng) {
            if (isNoBargain && isBdinng) {
                return both.status;
            } else if (isNoBargain) {
                return noBargain.status;
            } else if (isBdinng) {
                return bidding.status;
            } else {
                return error.status;
            }
        }
    }
}
