package com.bcx.managersystem.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.bcx.managersystem.R;
import com.bcx.managersystem.base.BaseAcitivity;
import com.bcx.managersystem.net.BaseNet;
import com.bcx.managersystem.net.HttpNet;
import com.bcx.managersystem.net.onNetCompleteListener;
import com.bcx.managersystem.util.PreferenceUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseAcitivity implements View.OnClickListener {
    private Button bt_login;
    private BaseNet httpNet;
    private EditText et_username;
    private EditText et_userpwd;
    private CheckBox cb_rember;
    private CheckBox cb_auto_login;
    private boolean isAutoLogin = false;
    private boolean isRemberPWD = false;
    private boolean isLogin = false;
    private ProgressDialog pgd;
    private Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void AutoLogin() {
        init_usermsg();
        boolean b = PreferenceUtil.getBoolean(this, "isLogin", false);
        if (b) {
            Login();
            isAutoLogin = true;
//            bt_login.performClick();//模拟控件点击
        }

    }

    public void init() {
        bt_login = (Button) this.findViewById(R.id.ac_login_bt_login);
        et_username = (EditText) this.findViewById(R.id.log_name);
        et_userpwd = (EditText) this.findViewById(R.id.log_pwd);
        cb_rember = (CheckBox) this.findViewById(R.id.ac_login_cd_rember);
        cb_auto_login = (CheckBox) this.findViewById(R.id.ac_login_cd_auto);
        cb_rember.setOnClickListener(this);
        cb_auto_login.setOnClickListener(this);
        pgd = new ProgressDialog(this);
        pgd.setMessage("正在登陆");
        pgd.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                httpNet.nowTime = System.currentTimeMillis();
            }
        });
        AutoLogin();
    }

    public void init_usermsg() {
        String str_name = PreferenceUtil.getString(this, "username", "xxx");
        String str_pwd = PreferenceUtil.getString(this, "userpwd", "xxx");
        et_username.setText(str_name);
        et_userpwd.setText(str_pwd);
        if (!TextUtils.isEmpty(str_pwd)) {
            cb_rember.setChecked(true);
        }
    }

    public void loading(View view) {
        check_tv();
        bt_login.setText("正在登陆");
        Login();
    }
    public void check_tv(){
        if (TextUtils.isEmpty(et_username.getText().toString())) {
            Toast_SHORT("请输入用户名");
            shake(et_username);
            return;
        }
        if (TextUtils.isEmpty(et_userpwd.getText().toString())) {
            Toast_SHORT("请输入密码");
            shake(et_userpwd);
            return;
        }
    }
    public void Login() {
        //?uid=" + mNameEt.getText().toString().trim() + "&passwd=" + mPwdEt.getText().toString().trim()
        map.put("username", et_username.getText().toString());
        map.put("wserpwd", et_userpwd.getText().toString());
        httpNet = new HttpNet("http://www.baidu.com/", null, new NetListener());
        httpNet.nowTime = System.currentTimeMillis();
        httpNet.netting();
        pgd.show();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ac_login_cd_rember) {
            if (((CheckBox) v).isChecked()) {
                isRemberPWD = true;
            } else {
                isRemberPWD = false;
            }
        } else if (v.getId() == R.id.ac_login_cd_auto) {
            if (((CheckBox) v).isChecked()) {
                isAutoLogin = true;
            } else {
                isAutoLogin = false;
            }
        }
    }

    class NetListener implements onNetCompleteListener<String> {

        @Override
        public void Net_Ok(String s) {
            isLogin = true;
            if (isRemberPWD) {
                PreferenceUtil.saveString(LoginActivity.this, "username", et_username.getText().toString());
                PreferenceUtil.saveString(LoginActivity.this, "userpwd", et_userpwd.getText().toString());
            } else {
                PreferenceUtil.saveString(LoginActivity.this, "username", et_username.getText().toString());
                PreferenceUtil.saveString(LoginActivity.this, "userpwd", "");
            }
            if (isAutoLogin) {
                PreferenceUtil.saveBoolean(LoginActivity.this, "isLogin", true);
            } else {
                PreferenceUtil.saveBoolean(LoginActivity.this, "isLogin", false);
            }
            MyStartActivity(MainActivity.class);
        }

        @Override
        public void Net_Error() {
            Toast_SHORT("用户名或密码错误");
            bt_login.setText("请重新登陆");
            bt_login.setBackgroundColor(Color.RED);
            if (pgd.isShowing())
                pgd.cancel();
        }

        @Override
        public void Net_Execption() {
            Toast_SHORT("网络异常，请检查网络");
            bt_login.setText("请重新登陆");
            if (pgd.isShowing())
                pgd.cancel();
        }
    }

    public void shake(View v) {
        Animation ani = AnimationUtils.loadAnimation(this, R.anim.shake);
        v.startAnimation(ani);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pgd.isShowing()) {
            httpNet.nowTime = System.currentTimeMillis();
            pgd.cancel();
        }
        if (isLogin) {
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
