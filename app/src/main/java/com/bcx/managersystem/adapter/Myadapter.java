package com.bcx.managersystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.db.IMPL;
import com.bcx.managersystem.entity.MyEntity;

import java.util.List;

public class Myadapter extends BaseAdapter implements OnClickListener{
	private Context context;
	private List<MyEntity> list;
	private IMPL db;
	private ViewHolder vh;
	private boolean isShow = false;
	public Myadapter(Context contex) {
		super();
		this.context = contex;
		db = new IMPL(context);
		list = db.getAllMessage();
	}
	
	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			vh = new ViewHolder();
			convertView = View.inflate(context, R.layout.listview, null);
			vh.img = (ImageView) convertView.findViewById(R.id.ll_img);
			vh.date = (TextView) convertView.findViewById(R.id.ll_date);
			vh.time = (TextView) convertView.findViewById(R.id.ll_time);
			vh.title = (TextView) convertView.findViewById(R.id.ll_title);
			vh.content = (TextView) convertView.findViewById(R.id.ll_content);
			vh.img.setOnClickListener(this);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
			
		}
	
		MyEntity myen = list.get(position);
		if(isShow){
			vh.img.setVisibility(View.VISIBLE);
			vh.img.setTag(myen);
		}else{
			vh.img.setVisibility(View.GONE);
		}
		vh.date.setText(myen.getDate());
		vh.time.setText(myen.getTime());
		vh.title.setText(myen.getTitle());;
		vh.content.setText(myen.getContent());

		return convertView;
	}
	class ViewHolder{
		private TextView date;
		private TextView time;
		private TextView title;
		private ImageView img;
		private TextView content;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MyEntity entity = (MyEntity) v.getTag();
		db.deleteMessage(entity);
		this.notifyDataSetChanged();
	}
}
