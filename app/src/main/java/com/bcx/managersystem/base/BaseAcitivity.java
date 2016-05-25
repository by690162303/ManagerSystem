package com.bcx.managersystem.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.bcx.managersystem.util.L;

/**
 * Created by 白杨 on 2016/5/5.
 */
public class BaseAcitivity extends FragmentActivity {
    private String TAG;
    public FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        mFragmentManager = this.getSupportFragmentManager();
    }
    public void L(String str){
        L.L("aa:"+TAG,str);
    }
    public void MyStartActivity(Class className){
        Intent intent = new Intent(this,className);
        startActivity(intent);
    }
    public void Toast_SHORT(String str){
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
    public void Toast_LONG(String str){
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }
}
