package com.bcx.managersystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcx.managersystem.R;

/**
 * Created by 白杨 on 2016/5/9.
 */
public class FragMySelfAdapter extends BaseAdapter {
    private Context mContext;
    private String[] str;
    private int[] img_ID;
    private viewHolder vh;
    public FragMySelfAdapter(Context mContext, String[] str,int[] img_id) {
        this.mContext = mContext;
        this.str = str;
        img_ID = img_id;
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
            convertView = View.inflate(mContext, R.layout.adapter_frag_myself,null);
            vh.tv = (TextView) convertView.findViewById(R.id.list_frag_myself_tv);
            vh.im = (ImageView) convertView.findViewById(R.id.list_frag_myself_img);
            convertView.setTag(vh);
        }else{
            vh = (viewHolder) convertView.getTag();
        }
            vh.tv.setText(str[position]);
            vh.im.setImageResource(img_ID[position]);
        return convertView;
    }


    class viewHolder{
        private TextView tv;
        private ImageView im;
    }
}
