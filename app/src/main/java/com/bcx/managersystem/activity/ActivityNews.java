package com.bcx.managersystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.base.BaseAcitivity;

public class ActivityNews extends BaseAcitivity {
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);
        img = (ImageView) this.findViewById(R.id.frag_me_msg_back);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();//返回
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
