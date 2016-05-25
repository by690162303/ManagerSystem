package com.bcx.managersystem.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.base.BaseFragment;
import com.bcx.managersystem.entity.Weather;
import com.bcx.managersystem.net.BaseNet;
import com.bcx.managersystem.net.HttpNet;
import com.bcx.managersystem.net.onNetCompleteListener;
import com.bcx.managersystem.parse.WeatherParse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 白杨 on 2016/5/6.
 */
public class Frag_class extends BaseFragment {
    private ViewPager mvp;
    private Class_mon Mon = new Class_mon();
    private Class_tue Tue = new Class_tue();
    private Class_wed Wed = new Class_wed();
    private Class_thu Thu = new Class_thu();
    private Class_fri Fri = new Class_fri();
    private Fragment[] data_frag = {Mon,  Tue,Wed, Thu, Fri};
    private MyDataViewPagerAdapter vp_adapter;
    private TabLayout tabLayout;
    private String appkey = "12d2e2431a483";
    private String city = "北京";
    private String distrct = "朝阳";
    private String[] str;
    //第几周
    private TextView tv_num;
    //星期几
    private TextView tv_zhouji;
    //日期
    private TextView tv_data;
    //天气预报
    private TextView tv_weather;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_data.setText(parseDate());
        }
    };
    private BaseNet net2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag__class, container, false);
        mContext = getContext();
        mvp = (ViewPager) view.findViewById(R.id.frag_class_vp);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tv_data = (TextView) view.findViewById(R.id.frag_class_tv_data);
        tv_num = (TextView) view.findViewById(R.id.frag_class_tv_num);
        tv_zhouji = (TextView) view.findViewById(R.id.frag_class_tv_zhounum);
        tv_weather = (TextView) view.findViewById(R.id.frag_class_tv_weather);
        init();
        return view;
    }

    public void init() {
        vp_adapter = new MyDataViewPagerAdapter(getChildFragmentManager());
        str = getResources().getStringArray(R.array.ViewPager_Title);
        mvp.setAdapter(vp_adapter);
        mvp.setCurrentItem(0);
        mvp.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(mvp);

//        tabLayout.setTabMode();
//        tabLayout.setTabMode(tabLayout.MODE_FIXED);
        //设置tablayout为滑动模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(vp_adapter.getTabView(i));
        }

        tv_data.setText(parseDate());
        parseDate2xinqiji();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mhandler.sendEmptyMessage(1);
            }
        };
        timer.schedule(task, 1000, 1000);
        city = String2URLDecoder(city,"UTF-8");
        distrct = String2URLDecoder(distrct,"UTF-8");
        net2 = new HttpNet("http://apicloud.mob.com/v1/weather/query?key=" + appkey + "&city=" + city + "&province=" + distrct + "/", new WeatherParse(), new mynetlistener());
        net2.netting();
    }

    class mynetlistener implements onNetCompleteListener<Weather> {

        @Override
        public void Net_Ok(Weather weather) {
            Weather.ResultEntity r = weather.getResult().get(0);
//            L(r.toString());
            tv_weather.setText(r.getDistrct() +"  "+r.getWeather()+"   气温："+r.getTemperature()+"  "+r.getWind()
                    +"  穿衣推荐："+r.getDressingIndex());
        }

        @Override
        public void Net_Error() {
            L("错误");
        }

        @Override
        public void Net_Execption() {
            L("异常");
        }
    }

    class MyDataViewPagerAdapter extends FragmentPagerAdapter {

        public MyDataViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return data_frag[position];
        }

        @Override
        public int getCount() {
            return data_frag.length;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return str[position];
//        }
        public View getTabView(int position){
            View v =View.inflate(mContext,R.layout.text_view,null);
            TextView tv = (TextView) v.findViewById(R.id.custon_textview);
            tv.setText(str[position]);
            return v;
        }
    }

    public String parseDate() {
        Date date = new Date();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日  HH:mm:ss");
        String stt = sDateFormat.format(date);
        return stt;
    }

    public void parseDate2xinqiji() {
        Calendar calendar = Calendar.getInstance();
        tv_zhouji.setText("今日：" + int2StringDate(calendar.get(Calendar.DAY_OF_WEEK)));
        tv_num.setText("第" + (calendar.get(Calendar.WEEK_OF_YEAR)) + "周");
    }

    public String int2StringDate(int i) {
        String tt = null;
        if ("1".equals(str)) {
            tt = "星期日";
        } else if ("2".equals(str)) {
            tt = "星期一";
        } else if ("3".equals(str)) {
            tt = "星期二";
        } else if ("4".equals(str)) {
            tt = "星期三";
        } else if ("5".equals(str)) {
            tt = "星期四";
        } else if ("6".equals(str)) {
            tt = "星期五";
        } else if ("7".equals(str)) {
            tt = "星期六";
        }
        switch (i) {
            case 1:
                tt = "星期日";
                break;
            case 2:
                tt = "星期一";
                break;
            case 3:
                tt = "星期二";
                break;
            case 4:
                tt = "星期三";
                break;
            case 5:

                tt = "星期四";
                break;
            case 6:
                tt = "星期五";
                break;
            case 7:
                tt = "星期六";
                break;
        }
        return tt;
    }

    public String String2URLDecoder(String str, String codeformat) {
        String ss = null;
        try {
            ss = URLEncoder.encode(str, codeformat);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ss;
    }
}
