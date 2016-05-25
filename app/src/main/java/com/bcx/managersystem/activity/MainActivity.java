package com.bcx.managersystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bcx.managersystem.Interface.Dosoming;
import com.bcx.managersystem.R;
import com.bcx.managersystem.base.BaseAcitivity;
import com.bcx.managersystem.fragment.Frag_Memo;
import com.bcx.managersystem.fragment.Frag_MySelf;
import com.bcx.managersystem.fragment.Frag_class;
import com.bcx.managersystem.fragment.Frag_notice;
import com.bcx.managersystem.util.PreferenceUtil;
import com.bcx.managersystem.view.CustomViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseAcitivity {
    private Fragment frag_class = new Frag_class();
    private Fragment frag_notice = new Frag_notice();
    private Frag_Memo frag_memo = new Frag_Memo();
    private Frag_MySelf frag_me = new Frag_MySelf();
    private Fragment[] fragments = {frag_class, frag_notice, frag_memo, frag_me};
    private long backtime = System.currentTimeMillis();
    private CustomViewPager mViewPager;
    private viewpagerAdapter adapter;
    private Context mContext;
    private boolean isScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        init();
    }

    public void init() {
        mViewPager = (CustomViewPager) this.findViewById(R.id.ac_main_contant_viewpager);
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(3);
        adapter = new viewpagerAdapter(mFragmentManager);
        mViewPager.setAdapter(adapter);
        isScroll = PreferenceUtil.getBoolean(mContext, "changeview", false);
        mViewPager.setScroll(isScroll);
        frag_me.setDd(new Dosoming() {
            @Override
            public void doit() {
                isScroll = PreferenceUtil.getBoolean(mContext, "changeview", false);
                mViewPager.setScroll(isScroll);
                L("切换动画是否开启？ " + isScroll);
            }
        });
        initLocation();
    }

    public void radioOnclick(View v) {
        RadioButton rb = (RadioButton) v;
        int viewPagerNum = 0;
        switch (rb.getId()) {
            case R.id.ac_main_rb_class:
                viewPagerNum = 0;
                break;
            case R.id.ac_main_rb_msg:
                viewPagerNum = 1;
                break;
            case R.id.ac_main_rb_txt:
                viewPagerNum = 2;
                break;
            case R.id.ac_main_rb_me:
                viewPagerNum = 3;
                break;
        }
        mViewPager.setCurrentItem(viewPagerNum, false);
    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() != 0) {
            super.onBackPressed();
            return;
        }
        if (System.currentTimeMillis() - backtime > 3000) {
            Toast_LONG("3秒内在此点击退出");
            backtime = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }

    class viewpagerAdapter extends FragmentPagerAdapter {

        public viewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    /**
     * 、
     * 定位
     */
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    df.format(date);//定位时间
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    L("省:" + amapLocation.getProvince() + "\n" +//省信息
                            "城市:" + amapLocation.getCity() + "\n" +//城市信息
                            "城市:" + amapLocation.getDistrict());//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    public void initLocation() {
//初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }
}
