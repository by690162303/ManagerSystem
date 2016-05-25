package com.bcx.managersystem.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bcx.managersystem.base.BaseAcitivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by 白杨 on 2016/5/5.
 */
public abstract class BaseNet extends BaseAcitivity {
    public static final int NET_OK = 1;
    public static final int NET_ERROR = 0;
    public static final int NET_EXECPTION = -1;
    public URL mUrl;
    public long nowTime = System.currentTimeMillis();
    public BaseParse mbaseParse;
    public onNetCompleteListener mOnNetCompleteListener;
    public Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = new Bundle();
             bundle = msg.getData();
            long time = bundle.getLong("time_xx");
            if(time!= nowTime  ){
                return;
            }else{
            }
            if(msg.what == NET_OK){
                mOnNetCompleteListener.Net_Ok(msg.obj);
            }else if(msg.what == NET_ERROR){
                mOnNetCompleteListener.Net_Error();
            }else if(msg.what == NET_EXECPTION){
                mOnNetCompleteListener.Net_Execption();
            }
        }
    };
    public abstract void netting();

    public BaseNet( String mURL, Map<String, String> map, BaseParse mbaseParse, onNetCompleteListener mOnNetCompleteListener) {
        this.mbaseParse = mbaseParse;
        this.mOnNetCompleteListener = mOnNetCompleteListener;
        StringBuilder builder = new StringBuilder(mURL);
        Set<String> set = map.keySet();
        for (String s : set) {
            builder.append(s + "=" + map.get(s) + "&");
        }
        builder.deleteCharAt(builder.length() - 1);
        mURL = builder.toString();
        try {
            this.mUrl =  new URL(mURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mhandler.sendEmptyMessage(NET_ERROR);
        }


    }

    public BaseNet(String mURL, BaseParse mbaseParse, onNetCompleteListener mOnNetCompleteListener) {
        this.mbaseParse = mbaseParse;
        this.mOnNetCompleteListener = mOnNetCompleteListener;
        try {
            this.mUrl =  new URL(mURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mhandler.sendEmptyMessage(NET_ERROR);
        }
    }
}
