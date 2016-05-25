package com.bcx.managersystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bcx.managersystem.R;

/**
 * Created by 白杨 on 2016/5/9.
 */
public class FragNoticeAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private String[] str;
    private viewHolder vh;
    public FragNoticeAdapter(Context mContext, String[] str) {
        this.mContext = mContext;
        this.str = str;
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            vh = new viewHolder();
            convertView = View.inflate(mContext, R.layout.adapter_frag_notice,null);
            vh.tv = (TextView) convertView.findViewById(R.id.notice_adapter_author);
            vh.tv.setOnClickListener(this);
            convertView.setTag(vh);
        }else{
            vh = (viewHolder) convertView.getTag();
        }
            vh.tv.setText(str[position]);
        return convertView;
    }

    @Override
    public void onClick(View v) {
    }

    class viewHolder{
        private TextView tv;
    }
}
