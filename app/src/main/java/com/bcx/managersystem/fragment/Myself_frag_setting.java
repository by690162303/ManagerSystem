package com.bcx.managersystem.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.bcx.managersystem.Interface.Dosoming;
import com.bcx.managersystem.R;
import com.bcx.managersystem.base.BaseFragment;
import com.bcx.managersystem.util.PreferenceUtil;

public class Myself_frag_setting extends BaseFragment implements View.OnClickListener {
    private ImageView SettingBack;
    private Switch msBell;
    private LinearLayout LayoutShenying;
    private Switch msShake;
    private View vline;
    private Switch changeView;
    private Dosoming dd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myself_frag_setting, container, false);
        v.setOnTouchListener(this);
        SettingBack = (ImageView) v.findViewById(R.id.frag_me_setting_back);
        msBell = (Switch) v.findViewById(R.id.frag_me_set_bell);
        msShake = (Switch) v.findViewById(R.id.frag_me_set_shake);
        LayoutShenying = (LinearLayout) v.findViewById(R.id.frag_me_setting_layout_shenying);
        vline = v.findViewById(R.id.frag_me_set_line);
        changeView = (Switch) v.findViewById(R.id.frag_me_set_other);

        init();
        return v;
    }

    private void init() {
        SettingBack.setOnClickListener(this);
        msShake.setOnClickListener(this);
        msBell.setOnClickListener(this);
        LayoutShenying.setOnClickListener(this);
        changeView.setOnClickListener(this);
        msShake.setChecked(isBoolean("shake"));
        msBell.setChecked(isBoolean("shenying"));
        changeView.setChecked(isBoolean("changeview"));
        layouthide(isBoolean("shenying"));
    }
    public boolean isBoolean(String key){
       return PreferenceUtil.getBoolean(mContext,key,false);
    }
    public void setDd(Dosoming dd) {

        this.dd = dd;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_me_setting_back:
                mFragmentManager.popBackStack();
                break;
            case R.id.frag_me_setting_layout_shenying:
                mFragmentManager = this.getFragmentManager();
                transaction = mFragmentManager.beginTransaction();
                transaction.add(R.id.content,new Myself_frag_setting_Ringtone(),"myself_ring");
                transaction.addToBackStack("myself_ring");
                transaction.commit();
                break;
            case R.id.frag_me_set_bell:
                Switch s = (Switch) v;
                PreferenceUtil.saveBoolean(mContext,"shenying",s.isChecked());
                L("是否开启声音："+s.isChecked());
                layouthide(s.isChecked());
                break;
            case R.id.frag_me_set_shake:
                Switch s2 = (Switch) v;
                PreferenceUtil.saveBoolean(mContext,"shake",s2.isChecked());
                L("是否开启震动："+s2.isChecked());
                break;
            case R.id.frag_me_set_other:
                Switch s3 = (Switch) v;
                PreferenceUtil.saveBoolean(mContext,"changeview",s3.isChecked());
                L("是否开启界面切换动画："+s3.isChecked());
                dd.doit();
                break;
        }
    }
    public void layouthide(boolean f){
        if (f) {
            LayoutShenying.setVisibility(View.VISIBLE);
            vline.setVisibility(View.VISIBLE);
        } else {
            LayoutShenying.setVisibility(View.GONE);
            vline.setVisibility(View.GONE);
        }
    }
}
