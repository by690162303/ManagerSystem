package com.bcx.managersystem.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.adapter.FragMySelfMsgAdapter;
import com.bcx.managersystem.base.BaseContent;
import com.bcx.managersystem.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


public class Myself_frag_msg extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.frag_me_msg_listview)
    private ListView fragMeMsgListview;
    @BindView(R.id.frag_me_msg_tv_alert)
    private TextView fragMeMsgTvAlert;
    private FragMySelfMsgAdapter msgAdapter;
    private ImageView back_im ;
    private Map<String,String> map = new HashMap<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myself_frag_msg, container, false);
        v.setOnTouchListener(this);
        fragMeMsgListview = (ListView) v.findViewById(R.id.frag_me_msg_listview);
        back_im = (ImageView) v.findViewById(R.id.frag_me_msg_back);
        back_im.setOnClickListener(this);
        mContext = getContext();
        init();
        return v;
    }

    public void init() {
        String[] str = BaseContent.ARRANGETITLE;
        for(int i = 0;i<str.length;i++){
            if(i!=0) {
                map.put("" + i, str[i]);
            }else {
                map.put("" + i, "");
            }
        }
        msgAdapter = new FragMySelfMsgAdapter(mContext,map);
        fragMeMsgListview.setAdapter(msgAdapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag_me_msg_back:
                mFragmentManager.popBackStack();
                break;
        }
    }

}
