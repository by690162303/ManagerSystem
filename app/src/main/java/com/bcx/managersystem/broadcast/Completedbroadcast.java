package com.bcx.managersystem.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bcx.managersystem.activity.InformActivity;
import com.bcx.managersystem.db.IDB;
import com.bcx.managersystem.db.IMPL;
import com.bcx.managersystem.entity.MyEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Completedbroadcast extends BroadcastReceiver {
	// 开机广播 接受广播以后 设置提醒时间 循环遍历 获取所有的信息的时间 将其转化
	private List<MyEntity> list = new ArrayList();
	private IDB db;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		db = new IMPL(context);
		list = db.getAllMessage();
		AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intents = null;
		System.out.println("开机启动");
		for (MyEntity entity : list) {
			intents = new Intent(context, InformActivity.class);
			intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intents.putExtra("MyEntity", entity);
			PendingIntent pi = PendingIntent.getActivity(context, entity.getId(), intents, 0);
			Date time = new Date();
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				time = simple.parse(entity.getDate() + " " + entity.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long longtime = time.getTime();
			if (longtime >= System.currentTimeMillis()) {
				alarm.set(AlarmManager.RTC_WAKEUP, longtime, pi);
			}

		}
	}

}
