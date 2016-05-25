package com.bcx.managersystem.base;

import android.app.Application;

import com.mob.mobapi.MobAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 白杨 on 2016/5/5.
 */
public class BaseApplication extends Application {
    private static ExecutorService es =   Executors.newFixedThreadPool(3);
    public static ExecutorService getExe(){
        return  es ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobAPI.initSDK(this,"12d2e2431a483");
    }
}
