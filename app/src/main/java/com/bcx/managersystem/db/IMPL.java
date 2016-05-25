package com.bcx.managersystem.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bcx.managersystem.entity.MyEntity;

import java.util.ArrayList;
import java.util.List;

public class IMPL implements IDB {
	private Context context;
	private static List<MyEntity> list = new ArrayList<MyEntity>();
	private DBopen data;
	public IMPL(Context context) {
		super();
		this.context = context;
		data = new DBopen(context);
	}
	static {
		MyEntity  m = new MyEntity(101, "2012-05-10", "10:22:12", "Title", "���ݡ�������");
		MyEntity m1 = new MyEntity(102, "2012-05-10", "10:22:12", "Title", "���ݡ�������");
		MyEntity m2 = new MyEntity(103, "2012-05-10", "10:22:12", "Title", "���ݡ�������");
		MyEntity m3 = new MyEntity(104, "2012-05-10", "10:22:12", "Title", "���ݡ�������");
		MyEntity m4 = new MyEntity(105, "2012-05-10", "10:22:12", "Title", "���ݡ�������");
		MyEntity m5 = new MyEntity(106, "2012-05-10", "10:22:12", "Title", "���ݡ�������");
		MyEntity m6 = new MyEntity(107, "2012-05-10", "10:22:12", "Title", "���ݡ�������");
		list.add(m);
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		list.add(m6);
	}
	@Override
	public List<MyEntity> getAllMessage() {
		// TODO Auto-generated method stub
		list.clear();
		SQLiteDatabase db = data.getReadableDatabase();
		String sql ="select * from Memo";
		Cursor cursor = db.rawQuery(sql, null);
		MyEntity entity = null;
		while(cursor.moveToNext()){
			entity = new MyEntity();
			entity.setId(cursor.getInt(cursor.getColumnIndex("id")));
			entity.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			entity.setDate(cursor.getString(cursor.getColumnIndex("date")));
			entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
			entity.setContent(cursor.getString(cursor.getColumnIndex("content")));
			list.add(entity);
		}
		return list;
	}

	@Override
	public void UpdataMessage(MyEntity entity) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = data.getWritableDatabase();
		String sql = "update Memo set title=?,date=?,time=?,content=? where id=?";
		db.execSQL(sql, new Object[]{entity.getTitle(),entity.getDate(),entity.getTime(),entity.getContent(),entity.getId()});
	}

	@Override
	public void saveMessage(MyEntity entity) {
		// TODO Auto-generated method stub
		list.add(entity);
		SQLiteDatabase db = data.getWritableDatabase();
		String sql = "insert into Memo(title,date,time,content)values(?,?,?,?)";
		db.execSQL(sql, new Object[]{entity.getTitle(),entity.getDate(),entity.getTime(),entity.getContent()});
	}

	@Override
	public void deleteMessage(MyEntity entity) {
		// TODO Auto-generated method stub
		list.remove(entity);
		SQLiteDatabase db = data.getWritableDatabase();
		String sql = "delete from Memo where id=?";
		db.execSQL(sql, new Object[]{entity.getId()});
	}

}
