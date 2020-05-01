package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyChannelAdapter extends RecyclerView.Adapter<MyChannelAdapter.TCVh> {


    private List<ChannelBean> channelList;
    private LayoutInflater inflate;
    private Context context;
    private OnItemClickListener listener;

    public MyChannelAdapter(Context context, List<ChannelBean> channelList) {
        this.context = context;
        this.channelList = channelList;
        inflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TCVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TCVh(inflate.inflate(R.layout.item_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyChannelAdapter.TCVh holder, final int position) {
        holder.checkTv.setText(channelList.get(position).getName());
        holder.checkTv.setChecked(true);
        holder.ivDelete.setVisibility(View.VISIBLE);
        holder.checkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ClickUtil.canClick()) return;
                if (listener != null) {
                    listener.onItemDelete(position);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return channelList.size();
    }

    public void setItemSelect(int position) {

    }

    public class TCVh extends RecyclerView.ViewHolder {
        AppCompatCheckedTextView checkTv;
        ImageView ivDelete;

        public TCVh(@NonNull View itemView) {
            super(itemView);
            checkTv = itemView.findViewById(R.id.checkTv);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(int position);

        void onItemDelete(int position);
    }
}
