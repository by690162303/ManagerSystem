package com.bcx.managersystem.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bcx.managersystem.R;
import com.bcx.managersystem.db.IDB;
import com.bcx.managersystem.db.IMPL;
import com.bcx.managersystem.entity.MyEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddActivity extends Activity {
	TextView title;
	TextView time;
	TextView date;
	TextView content;
	Time ti;
	private IDB db;
	private boolean isUpdata = false;
	private MyEntity myentity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		date = (TextView) this.findViewById(R.id.add_date);
		content = (TextView) this.findViewById(R.id.add_content);
		time = (TextView) this.findViewById(R.id.add_time);
		title = (TextView) this.findViewById(R.id.add_title);
		ti = new Time();
		ti.setToNow();
		date.setText(ti.format("%Y-%m-%d"));
		time.setText(ti.format("%H:%M"));
		db = new IMPL(this);
		myentity = (MyEntity) this.getIntent().getSerializableExtra("MyEntity");
		if (myentity == null) {
			isUpdata = false;
		} else {
			title.setText(myentity.getTitle());
			date.setText(myentity.getDate());
			time.setText(myentity.getTime());
			content.setText(myentity.getContent());
			isUpdata = true;
		}
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		Intent intents = new Intent("com.inform");
		intents.putExtra("MyEntity", myentity);
		PendingIntent pi = PendingIntent.getActivity(this, myentity.getId(), intents, 0);
		Date time = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			time = simple.parse(myentity.getDate() + " " + myentity.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long longtime = time.getTime();
		alarm.set(AlarmManager.RTC_WAKEUP, longtime, pi);
	}
	public void UpdataMyEntity() {
		myentity.setDate(date.getText().toString());
		myentity.setTime(time.getText().toString());
		myentity.setTitle(title.getText().toString());
		myentity.setContent(content.getText().toString());
		db.UpdataMessage(myentity);
	}

	public MyEntity saveMyEntity() {
		myentity = new MyEntity();
		myentity.setDate(date.getText().toString());
		myentity.setTime(time.getText().toString());
		myentity.setTitle(title.getText().toString());
		myentity.setContent(content.getText().toString());
		return myentity;
	}

	public void go(View v) {
		if (v.getId() == R.id.add_finish) {
			if (isUpdata) {
				AlertDialog.Builder build = new AlertDialog.Builder(this);
				build.setTitle("是否退出？");
				build.setPositiveButton("是", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						AddActivity.this.finish();
						UpdataMyEntity();
						dialog.cancel();
					}
				});
				build.setNegativeButton("否", null);
				build.create().show();
			} else {
				AlertDialog.Builder build = new AlertDialog.Builder(this);
				build.setTitle("是否继续添加？");
				build.setPositiveButton("是", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						db.saveMessage(saveMyEntity());
						dialog.cancel();
					}
				});
				build.setNegativeButton("否", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						AddActivity.this.finish();
						db.saveMessage(saveMyEntity());

					}
				});
				build.create().show();
			}
		} else {
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle("是否保存退出？");
			build.setPositiveButton("是", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if (isUpdata) {
						UpdataMyEntity();
					} else {
						db.saveMessage(saveMyEntity());
					}
					dialog.cancel();
					AddActivity.this.finish();
				}
			});
			build.setNegativeButton("否", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
					AddActivity.this.finish();
				}
			});
			build.create().show();

		}
	}

	public void setDate(View v) {
		if (v.getId() == R.id.add_date) {

			DatePickerDialog pick = new DatePickerDialog(this, new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int day) {
					// TODO Auto-generated method stub
					String stt = year + "-" +( month+1) + "-" + day;
					date.setText(stt);
				}
			}, ti.year, ti.month, ti.monthDay);
			pick.show();
		} else {
			TimePickerDialog pick = new TimePickerDialog(this, new OnTimeSetListener() {

				public void onTimeSet(TimePicker view, int hour, int minute) {
					// TODO Auto-generated method stub
					String stt = hour + ":" + minute;
					time.setText(stt);
				}
			}, ti.hour, ti.minute, true);
			pick.show();
		}
	}
}
