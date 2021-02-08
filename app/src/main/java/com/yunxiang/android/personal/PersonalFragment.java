
package com.yunxiang.android.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.personal.view.activity.PersonalListActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunxiang.android.personal.PersonalConstants.TYPE_EVALUATE;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_FOLLOW;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_ORDER_BUY;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_ORDER_SELL;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_RELEASE;


public class PersonalFragment extends BaseFragment {


    @BindView(R.id.img_user_avatar)
    ImageView mAvatarImg;
    @BindView(R.id.txt_user_name)
    TextView mUserNameTxt;
    @BindView(R.id.txt_user_sign)
    TextView mUserSignTxt;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initVariable() {
        RoundedCorners roundedCorners = new RoundedCorners(100);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(getContext())
                .load(getResources().getDrawable(R.drawable.default_avatar, null))
                .apply(options)
                .into(mAvatarImg);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.row_order_sell, R.id.row_order_buy, R.id.row_release, R.id.row_follow, R.id.row_evaluate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.row_order_sell:
                PersonalListActivity.startActivity(getContext(), TYPE_ORDER_SELL);
                break;
            case R.id.row_order_buy:
                PersonalListActivity.startActivity(getContext(), TYPE_ORDER_BUY);
                break;
            case R.id.row_release:
                PersonalListActivity.startActivity(getContext(), TYPE_RELEASE);
                break;
            case R.id.row_evaluate:
                PersonalListActivity.startActivity(getContext(), TYPE_EVALUATE);
                break;
            case R.id.row_follow:
                PersonalListActivity.startActivity(getContext(), TYPE_FOLLOW);
                break;
            default:
                break;
        }
    }
}
