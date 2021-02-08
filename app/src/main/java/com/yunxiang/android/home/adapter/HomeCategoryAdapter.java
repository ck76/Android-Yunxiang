
package com.yunxiang.android.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.neuqer.android.ui.pagergrid.PageBuilder;
import com.neuqer.android.ui.pagergrid.PageGridAdapter;
import com.yunxiang.android.R;
import com.yunxiang.android.home.model.HomeCategoryModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeCategoryAdapter extends PageGridAdapter<HomeCategoryModel.CategoryModel, HomeCategoryAdapter.ViewHolder> {

    private Context mContext;

    public HomeCategoryAdapter(Context context, PageBuilder pageBuilder) {
        super(pageBuilder);
        mContext = context;
    }

    @Override
    protected ViewHolder getItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_category_item, null);
        return new ViewHolder(view);
    }

    @Override
    protected void bindItemViewHolder(ViewHolder holder, int position) {
        HomeCategoryModel.CategoryModel model = getItemData(position);
        if (model != null) {
            Glide.with(mContext)
                    .load(model.getIcon())
                    .apply(RequestOptions.centerCropTransform())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(holder.image);
            holder.title.setText(model.getName());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
