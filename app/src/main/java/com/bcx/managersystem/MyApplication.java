package com.bcx.managersystem;

import android.app.Application;

/**
 * Created by 白杨 on 2016/5/25.
 */
public class MyApplication extends Application {
    private String u_name;
    private static MyApplication application;
    public static MyApplication getMyApplication(){
        if(application == null){
            return new MyApplication();
        }else{
            return application;
        }
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
