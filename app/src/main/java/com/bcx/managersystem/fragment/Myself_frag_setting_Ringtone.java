package com.bcx.managersystem.fragment;


import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.adapter.FragMySelfRingToneAdapter;
import com.bcx.managersystem.base.BaseFragment;
import com.bcx.managersystem.util.PreferenceUtil;

public class Myself_frag_setting_Ringtone extends BaseFragment {
    private ListView mListview;
    private FragMySelfRingToneAdapter ringToneAdapter;
    private Button bt_save;
    private ImageView img;
    private int checkPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myself_frag_setting_ringtone, container, false);
        v.setOnTouchListener(this);
        mContext = getContext();
        mListview = (ListView) v.findViewById(R.id.frag_me_ringtone_listview);
        bt_save = (Button) v.findViewById(R.id.bt_frag_me_ringtone_save);
        img = (ImageView) v.findViewById(R.id.frag_me_ringtone_back);
        init();
        return v;
    }

    private void init() {
        /**
         * 初始化闹铃 为系统闹领 值为0
         */
        ringToneAdapter = new FragMySelfRingToneAdapter(mContext,PreferenceUtil.getInt(mContext,"ring",0));
        mListview.setAdapter(ringToneAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkPosition = position;
                FragMySelfRingToneAdapter.ViewHolder mHolder = new FragMySelfRingToneAdapter.ViewHolder(parent);
			/*设置Imageview不可被点击*/
                mHolder.cb.setClickable(false);
			/*清空map*/
                ringToneAdapter.map.clear();
                // mAdapter.map.put(position, 1);
			/*将所点击的位置记录在map中*/
                ringToneAdapter.map.put(position, true);
			/*刷新数据*/
                ringToneAdapter.notifyDataSetChanged();
			/*判断位置不为0则播放的条目为position-1*/
                if (position != 0) {
                    try {

                        RingtoneManager rm = new RingtoneManager(getActivity());
                        rm.setType(RingtoneManager.TYPE_NOTIFICATION);
                        rm.getCursor();
                        rm.getRingtone(position - 1).play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
			/*position为0是跟随系统，先得到系统所使用的铃声，然后播放*/
                if (position == 0) {
                    Uri uri = RingtoneManager.getActualDefaultRingtoneUri(
                           mContext, RingtoneManager.TYPE_NOTIFICATION);
                    RingtoneManager.getRingtone(mContext, uri).play();
                }
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtil.saveInt(mContext,"ring",checkPosition);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.popBackStack();
            }
        });
    }


}
