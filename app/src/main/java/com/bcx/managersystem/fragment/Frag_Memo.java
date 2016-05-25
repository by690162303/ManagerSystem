package com.bcx.managersystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.activity.AddActivity;
import com.bcx.managersystem.adapter.Myadapter;
import com.bcx.managersystem.base.BaseFragment;
import com.bcx.managersystem.db.IDB;
import com.bcx.managersystem.db.IMPL;
import com.bcx.managersystem.entity.MyEntity;

import java.util.List;

/**
 * Created by 白杨 on 2016/5/6.
 */
public class Frag_Memo extends BaseFragment implements View.OnClickListener{
    private ListView listview;
    private Myadapter adapter;
    private List<MyEntity> list;
    private IDB db ;
    private TextView tv_del;
    private TextView tv_add;
    private int num=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag__msg,container,false);
        listview = (ListView) view.findViewById(R.id.listView);
        mContext =getContext();
        tv_add = (TextView) view.findViewById(R.id.tt_del);
        tv_del = (TextView) view.findViewById(R.id.tt_add);
        tv_del.setOnClickListener(this);
        tv_add.setOnClickListener(this);
        init();
        return view;
    }

    protected void init() {
        db = new IMPL(mContext);
        list = db.getAllMessage();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                MyEntity entity = list.get(position);
                Intent intents = new Intent(mContext,AddActivity.class);
                intents.putExtra("MyEntity", entity);
                startActivity(intents);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new Myadapter(getContext());
        listview.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tt_del){
            if(num%2==1){
                adapter.setShow(true);
            }else{
                adapter.setShow(false);
            }
            num++;
        }else{
            Intent intents = new Intent(mContext,AddActivity.class);
            startActivity(intents);
        }
        adapter.notifyDataSetChanged();
    }


}
