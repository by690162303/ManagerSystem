package com.bcx.managersystem.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.entity.MyEntity;

public class InformActivity extends Activity {
	private TextView title;
	private TextView content;
	MyEntity entity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_inform);
		title =(TextView) findViewById(R.id.inform_title);
		content = (TextView) findViewById(R.id.inform_content);
		content.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InformActivity.this.finish();
				
			}
		});
		entity = (MyEntity) this.getIntent().getSerializableExtra("MyEntity");
		System.out.println("��ȡ����Ϣ��"+entity.toString());
		title.setText(entity.getTitle());
		content.setText(entity.getContent());
	}
}
