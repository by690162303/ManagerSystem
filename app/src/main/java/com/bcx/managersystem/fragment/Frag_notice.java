package com.bcx.managersystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.activity.ActivityNews;
import com.bcx.managersystem.adapter.FragNoticeAdapter;
import com.bcx.managersystem.base.BaseApplication;
import com.bcx.managersystem.base.BaseFragment;
import com.bcx.managersystem.util.Refresh.CanRefreshLayout;

/**
 * Created by 白杨 on 2016/5/6.
 */
public class Frag_notice extends BaseFragment implements AdapterView.OnItemClickListener{
    private String stt[] = {"11","112","113","114","115","116","117",
            "118","119","110","111","112","113","114",};
    private ListView mRefreshListview;
    private FragNoticeAdapter noticeAdapter;
    private CanRefreshLayout refreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag__notice,container,false);
        mRefreshListview = (ListView) view.findViewById(R.id.can_content_view);
        refreshLayout = (CanRefreshLayout) view.findViewById(R.id.refresh);
        mContext =getContext();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void init(){
        noticeAdapter = new FragNoticeAdapter(getContext(),stt);
        mRefreshListview.setAdapter(noticeAdapter);
        mRefreshListview.setOnItemClickListener(this);
        refreshLayout.setOnLoadMoreListener(new CanRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //上推加载
                BaseApplication.getExe().execute(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        refreshLayout.refreshComplete();
                    }
                });
            }
        });
        refreshLayout.setOnRefreshListener(new CanRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                BaseApplication.getExe().execute(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        refreshLayout.refreshComplete();
                    }
                });
            }
        });


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intents = new Intent(mContext, ActivityNews.class);
        Bundle xx = new Bundle();

        startActivity(intents);
    }
}
