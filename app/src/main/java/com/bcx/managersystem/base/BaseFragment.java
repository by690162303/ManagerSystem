package com.bcx.managersystem.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bcx.managersystem.R;
import com.bcx.managersystem.fragment.Class_Dialogfragment;
import com.bcx.managersystem.util.L;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 白杨 on 2016/5/5.
 */
public class BaseFragment extends Fragment implements View.OnTouchListener ,View.OnClickListener{
    private String TAG;
    public Context mContext;
    public int mWidht;
    public int mHeight;
    public FragmentManager mFragmentManager;
    public FragmentTransaction transaction ;
    public TextView tv_1;
    public TextView tv_2;
    public TextView tv_3;
    public TextView tv_4;
    public TextView tv_5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getName();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = this.getContext();
        mFragmentManager = this.getFragmentManager();
        transaction = mFragmentManager.beginTransaction();
        DisplayMetrics dpm = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dpm);
        mWidht = dpm.widthPixels;
        mHeight = dpm.heightPixels;
    }

    public void L(String str){
        L.L("aa:"+TAG,str);
    }
    //转换时间、将毫秒值转换为时间格式
    public String getDate(long mil) {
        Date date = new Date();
        date.setTime(mil);
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
//        loge("接收到的值：" + date.getTime());
        return simple.format(date);
    }
    public void Toast_SHORT(String str){
        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
    }
    public void init_tv_add_class(View v){
        tv_1 = (TextView) v.findViewById(R.id.scheduleInsert_et1_2);
        tv_2 = (TextView) v.findViewById(R.id.scheduleInsert_et2_2);
        tv_3 = (TextView) v.findViewById(R.id.scheduleInsert_et3_2);
        tv_4 = (TextView) v.findViewById(R.id.scheduleInsert_et4_2);
        tv_5 = (TextView) v.findViewById(R.id.scheduleInsert_et5_2);
        tv_1.setOnClickListener((View.OnClickListener) this);
        tv_2.setOnClickListener((View.OnClickListener) this);
        tv_3.setOnClickListener((View.OnClickListener) this);
        tv_4.setOnClickListener((View.OnClickListener) this);
        tv_5.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onClick(View v) {
        int i = 0;
        String str ;
        switch (v.getId()) {
            case R.id.scheduleInsert_et1_2:
                i = 1;
                break;
            case R.id.scheduleInsert_et2_2:
                i = 2;
                break;
            case R.id.scheduleInsert_et3_2:
                i = 3;
                break;
            case R.id.scheduleInsert_et4_2:
                i = 4;
                break;
            case R.id.scheduleInsert_et5_2:
                i = 5;
                break;
        }
//        str = getResources().getStringArray(R.array.ViewPager_Title)[(i-1)];
        Class_Dialogfragment dialog = new Class_Dialogfragment(i,TAG);
        dialog.show(mFragmentManager, "dialog_one");
    }
}
