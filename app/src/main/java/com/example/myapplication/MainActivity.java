package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mChannelRv;
    private RecyclerView mTotalChannelRv;
    private List<ChannelBean> channelList = new ArrayList<>();
    private List<ChannelBean> myChannelList = new ArrayList<>();
    private TotalChannelAdapter channelAdapter;
    private MyChannelAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChannelRv = findViewById(R.id.myChannelRv);
        mTotalChannelRv = findViewById(R.id.mTotalChannelRv);
        GridLayoutManager lm = new GridLayoutManager(this, 4);
        GridLayoutManager myLm = new GridLayoutManager(this, 4);
        mTotalChannelRv.setLayoutManager(lm);
        mChannelRv.setLayoutManager(myLm);

        myAdapter = new MyChannelAdapter(this, myChannelList);
        mChannelRv.setAdapter(myAdapter);

        channelAdapter = new TotalChannelAdapter(this, channelList);
        mTotalChannelRv.setAdapter(channelAdapter);

        channelAdapter.setOnItemClickListener(new TotalChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (channelList.get(position).getSelect()) {
                    channelList.get(position).setSelect(false);
                    for (int i = 0; i < channelList.size(); i++) {
                        for (int j = 0; j < myChannelList.size(); j++) {
                            if (channelList.get(position).getName().equals(myChannelList.get(j).getName())) {
                                myChannelList.remove(j);
                                myAdapter.notifyItemRemoved(j);
                                myAdapter.notifyItemRangeChanged(j, myChannelList.size() - j);
                                j--;
                            }
                        }
                    }

                } else {
                    channelList.get(position).setSelect(true);
                    myChannelList.add(channelList.get(position));
                    myAdapter.notifyDataSetChanged();
                }
                channelAdapter.notifyDataSetChanged();
            }
        });
        myAdapter.setOnItemClickListener(new MyChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemDelete(int position) {
                if (myChannelList.size() > 0) {
                    notifyTotalChange(position, myChannelList.get(position).getName());

                }
            }
        });

        getTotalChannel();
    }

    private void notifyTotalChange(int position, String positionName) {
        for (int i = 0; i < channelList.size(); i++) {
            if (channelList.get(i).getName().equals(positionName)) {
                channelList.get(i).setSelect(false);
                myChannelList.remove(position);
                myAdapter.notifyItemRemoved(position);
                myAdapter.notifyItemRangeChanged(position, myChannelList.size() - position);
            }
        }
        channelAdapter.notifyDataSetChanged();
    }

    private void getTotalChannel() {
        channelList.add(new ChannelBean("电影", false));
        channelList.add(new ChannelBean("电视剧", false));
        channelList.add(new ChannelBean("动漫", false));
        channelList.add(new ChannelBean("记录", false));
        channelList.add(new ChannelBean("综艺", false));
        channelList.add(new ChannelBean("娱乐", false));
        channelList.add(new ChannelBean("游戏", false));
        channelList.add(new ChannelBean("搞笑", false));
        channelAdapter.notifyDataSetChanged();
    }
}
