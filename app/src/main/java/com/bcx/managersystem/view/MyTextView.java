package com.bcx.managersystem.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {
	//可以实现TextView多行显示跑马灯效果
	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	 @Override
	 public boolean isFocused() {
	  return true;
	 }
}
