package com.yunxiang.android.personal.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.PagingListAdapter;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.personal.view.holder.AssessViewHolder;
import com.yunxiang.android.personal.view.holder.FollowViewHolder;
import com.yunxiang.android.personal.view.holder.OrderViewHolder;

import static com.yunxiang.android.personal.PersonalConstants.TYPE_EVALUATE;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_FOLLOW;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_ORDER_BUY;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_ORDER_SELL;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_RELEASE;


public class PersonalListAdapter extends PagingListAdapter<JadeModel> {


    private int mAdapterType;

    public PersonalListAdapter(LifecycleOwner owner, int type) {
        super(DIFF_CALLBACK, owner);
        mAdapterType = type;
    }

    @Override
    protected RecyclerView.ViewHolder createNormalViewHolder(ViewGroup viewGroup, int type) {
        switch (type) {
            case TYPE_ORDER_BUY:
            case TYPE_ORDER_SELL:
                return createOrderViewHolder(viewGroup);
            case TYPE_RELEASE:
                return createReleaseViewHolder(viewGroup);
            case TYPE_EVALUATE:
                return createEvaluateViewHolder(viewGroup);
            case TYPE_FOLLOW:
                return createFollowViewHolder(viewGroup);
            default:
                throw new RuntimeException("unknown personal list type");
        }
    }


    private RecyclerView.ViewHolder createOrderViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.personal_item_order, viewGroup, false);
        return new OrderViewHolder(view);
    }

    private RecyclerView.ViewHolder createReleaseViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.personal_item_order, viewGroup, false);
        return new OrderViewHolder(view);
    }

    private RecyclerView.ViewHolder createEvaluateViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.personal_item_evaluate, viewGroup, false);
        return new AssessViewHolder(view);
    }

    private RecyclerView.ViewHolder createFollowViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.personal_item_follow, viewGroup, false);
        return new FollowViewHolder(view);
    }

    @Override
    protected int getDataItemViewType(int position) {
        return mAdapterType;
    }

    private static final DiffUtil.ItemCallback<JadeModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<JadeModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull JadeModel jadeModel, @NonNull JadeModel t1) {
            return t1.getId() == jadeModel.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull JadeModel jadeModel, @NonNull JadeModel t1) {
            return t1.equals(jadeModel);
        }
    };
}
