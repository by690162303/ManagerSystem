package com.bcx.managersystem.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcx.managersystem.R;
import com.bcx.managersystem.base.BaseContent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 白杨 on 2016/5/9.
 * 标题序列名称不可改变
 * {头像、姓名、年龄、职称、电话、所属**}
 */
public class FragMySelfMsgAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private Map<String,String> map = new HashMap<>();
    private viewHolder vh;
    public FragMySelfMsgAdapter(Context mContext, Map<String, String> map) {
        this.mContext = mContext;
        this.map = map;
    }
    @Override
    public int getCount() {
        return map.size();
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
            convertView = View.inflate(mContext, R.layout.adapter_frag_myself_msg,null);
            vh.tv_left = (TextView) convertView.findViewById(R.id.list_frag_myself_tv_left);
            vh.tv_right = (TextView) convertView.findViewById(R.id.list_frag_myself_tv_right);
            vh.img = (ImageView) convertView.findViewById(R.id.list_frag_me_msg_headview);
            convertView.setTag(vh);
        }else{
            vh = (viewHolder) convertView.getTag();
        }
            vh.tv_left.setText(BaseContent.ARRANGETITLE[position]);
            String str = map.get(""+position);
            if(str.startsWith("/storage/sdcard/managerHeadView/")|| TextUtils.isEmpty(str)){
                vh.img.setVisibility(View.VISIBLE);
                vh.tv_right.setVisibility(View.GONE);
                if(TextUtils.isEmpty(str)){
                    vh.img.setImageResource(R.drawable.morentouxiang);
                }else{
                    Log.e("aa","手机图片");
                    Bitmap btm =  BitmapFactory.decodeFile(str);
                    vh.img.setImageBitmap(btm);
                }
            }else{
                vh.img.setVisibility(View.GONE);
                vh.tv_right.setText(str);
            }
        return convertView;
    }

    @Override
    public void onClick(View v) {
    }

    class viewHolder{
        private TextView tv_left;
        private TextView tv_right;
        private ImageView img;
    }
}
