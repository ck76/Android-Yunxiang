package com.yunxiang.android.product.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunxiang.android.R;

import java.util.ArrayList;
import java.util.List;


public class AddImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_ADD = 1;
    private boolean mIsDeleteMode = false;
    private boolean[] mIsIconVisible;
    private int mMax = 9;
    private int mAssessPicCount;

    private List<String> mInitailPics;
    private List<Uri> mSelectUris;
    private OnItemClickedListener mOnItemClickedListener;

    public AddImageAdapter(Context context) {
        mIsIconVisible = new boolean[mMax];
        mInitailPics = new ArrayList<>();
        mSelectUris = new ArrayList<>();
        mAssessPicCount = 0;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_CONTENT) {
            return new ContentImageHolder(LayoutInflater.from(mContext).inflate(R.layout.view_image_with_delete, viewGroup, false));
        }
        if (i == TYPE_ADD) {
            return new AddImageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_add_image, viewGroup, false));
        }
        return new ContentImageHolder(LayoutInflater.from(mContext).inflate(R.layout.view_image_with_delete, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ContentImageHolder) {
            ContentImageHolder holder = (ContentImageHolder) viewHolder;
            if (position < mAssessPicCount) {
                //评估界面传进来的图片
                onBindInitail(holder, mInitailPics.get(position), position);
            } else {
                //绑定选择的图片
                onBindContent(holder, mSelectUris.get(position - mAssessPicCount), position);
            }
        }
        if (viewHolder instanceof AddImageHolder) {
            AddImageHolder holder = (AddImageHolder) viewHolder;
            onBindAddImagHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mAssessPicCount + mSelectUris.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= (mSelectUris.size() + mAssessPicCount)) {
            return TYPE_ADD;
        } else {
            return TYPE_CONTENT;
        }
    }


    public void addImage(List<Uri> imageUri) {
        mSelectUris.addAll(imageUri);
        notifyDataSetChanged();
    }

    public void deletImage(int position) {
        mSelectUris.remove(position - mAssessPicCount);
        notifyDataSetChanged();
    }

    public void setMax(int max) {
        mMax = max;
    }


    public int getSurplus() {
        return mMax - (mInitailPics.size() + mSelectUris.size());
    }

    public void setInitailPics(List<String> initailPics) {
        if (initailPics != null) {
            mInitailPics = initailPics;
            mAssessPicCount = mInitailPics.size();
        }
    }

    public List<String> getResultPics() {
        List<String> result = new ArrayList<>();
        if (mSelectUris.size() == 0) {
            return result;
        }
        for (Uri uri : mSelectUris) {
            // TODO 获取照片上传后的地址，待实现
        }
        return result;
    }


    private void onBindAddImagHolder(AddImageHolder holder, int position) {
        if (position >= mMax) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(v -> {
                if (mOnItemClickedListener != null) {
                    mOnItemClickedListener.onAddViewClicked(holder.itemView);
                }
            });
        }
    }

    private void onBindContent(ContentImageHolder holder, Uri uri, int position) {
        if (!mIsIconVisible[position]) {
            holder.mDeleteView.setVisibility(View.GONE);
        } else {
            holder.mDeleteView.setVisibility(View.VISIBLE);
        }
        Glide.with(mContext)
                .load(uri)
                .into(holder.mContentView);
        setPicClickListener(holder);
    }

    private void onBindInitail(ContentImageHolder holder, String picUrl, int position) {
        holder.mDeleteView.setVisibility(View.GONE);
        Glide.with(mContext)
                .load(picUrl)
                .into(holder.mContentView);
    }

    private void setPicClickListener(ContentImageHolder holder) {
        holder.mContentView.setOnClickListener(v -> {
            if (mOnItemClickedListener != null) {
                mOnItemClickedListener.onContentViewClicked(v);
            }
        });
        holder.mContentView.setOnLongClickListener(v -> {
            if (!mIsDeleteMode) {
                mIsDeleteMode = true;
                for (int i = 0; i < mIsIconVisible.length; i++) {
                    mIsIconVisible[i] = true;
                }
                notifyDataSetChanged();
            }
            return true;
        });
        holder.mDeleteView.setOnClickListener(v -> {
            deletImage(holder.getAdapterPosition());
        });
    }

    /**
     * 展示图片Holder
     */
    public class ContentImageHolder extends RecyclerView.ViewHolder {
        private ImageView mContentView;
        private ImageView mDeleteView;

        public ContentImageHolder(@NonNull View itemView) {
            super(itemView);
            mContentView = itemView.findViewById(R.id.img_content);
            mDeleteView = itemView.findViewById(R.id.img_delete);
        }
    }

    /**
     * 添加图片Holder
     */
    public static class AddImageHolder extends RecyclerView.ViewHolder {
        public AddImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 设置点击事件监听
     */
    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.mOnItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onContentViewClicked(View view);

        void onAddViewClicked(View view);

        void onDeleteViewClicked(View view, int pos);
    }
}
