package com.neuqer.android.sample.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.neuqer.android.sample.R;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;
    private List<ItemModel> mItemModelList;

    public ListAdapter(Context context) {
        mContext = context;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        mItemModelList = itemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_list, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemModel model = mItemModelList.get(position);
        holder.mTitleView.setText(model.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, model.getActivity());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemModelList != null ? mItemModelList.size() : 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleView;

        ViewHolder(View itemView) {
            super(itemView);
            mTitleView = itemView.findViewById(R.id.title);
        }
    }
}
