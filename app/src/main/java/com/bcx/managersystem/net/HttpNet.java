package com.bcx.managersystem.net;

import android.os.Bundle;
import android.os.Message;

import com.bcx.managersystem.base.BaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
/**
 * Created by 白杨 on 2016/5/5.
 */
public class HttpNet extends BaseNet {
    private Message msg;
      public HttpNet( String mURL, Map<String, String> map, BaseParse mbaseParse, onNetCompleteListener mOnNetCompleteListener) {
        super( mURL, map, mbaseParse, mOnNetCompleteListener);
    }

    public HttpNet( String mURL, BaseParse mbaseParse, onNetCompleteListener mOnNetCompleteListener) {
        super( mURL, mbaseParse, mOnNetCompleteListener);
    }
    @Override
    public void netting() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long time2 = nowTime;
                msg = mhandler.obtainMessage();
                Bundle bundle = new Bundle();//实现数据封装
                bundle.putLong("time_xx", time2);
                msg.setData(bundle);
                StringBuilder builder = new StringBuilder();
                HttpURLConnection conn;
                try {
                    conn = (HttpURLConnection) mUrl.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.setRequestMethod("GET");
                    if(conn.getResponseCode() == 200){
                        InputStream inputStream = conn.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String str = null;
                        while((str = bufferedReader.readLine()) !=null ){
                            builder.append(str);
                        }
                        msg.what = NET_OK;
                        if(mbaseParse == null){
                            msg.obj = builder.toString();
                        }else{
                            msg.obj = mbaseParse.myParse(builder.toString());
                        }
                        mhandler.sendMessage(msg);
                        inputStream.close();
                        bufferedReader.close();
                    }else{
                        msg.what = NET_EXECPTION;
                        mhandler.sendMessage(msg);
                    }
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    msg.what = NET_EXECPTION;
                    mhandler.sendMessage(msg);
                }
            }
        };
        BaseApplication.getExe().execute(runnable);
    }
}
