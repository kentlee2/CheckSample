package com.example.myapplication;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TotalChannelAdapter extends RecyclerView.Adapter<TotalChannelAdapter.TCVh> {


    private List<ChannelBean> channelList;
    private LayoutInflater inflate;
    private Context context;
    private OnItemClickListener listener;

    public TotalChannelAdapter(Context context, List<ChannelBean> channelList) {
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
    public void onBindViewHolder(@NonNull TotalChannelAdapter.TCVh holder, final int position) {
        holder.checkTv.setText(channelList.get(position).getName());
        holder.checkTv.setChecked(channelList.get(position).getSelect());
        holder.checkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
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

        public TCVh(@NonNull View itemView) {
            super(itemView);
            checkTv = itemView.findViewById(R.id.checkTv);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
