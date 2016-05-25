package com.bcx.managersystem.adapter;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bcx.managersystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 白杨 on 2016/5/9.
 * 标题序列名称不可改变
 * {头像、姓名、年龄、职称、电话、所属**}
 */
public class FragMySelfRingToneAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> ringlist;
    public Cursor cursor;
    public RingtoneManager rm;
    // public Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    public Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    public ListView ringView;
    public int index;
    public boolean firstItemState = true;
    private ViewHolder vh;

    public FragMySelfRingToneAdapter(Context mContext, int index) {
        this.mContext = mContext;
        this.index = index;
        if (firstItemState) {
            firstItemState = false;
            map.put(index, true);
        }
        getRing();
    }

    public void getRing() {
        /* 新建一个arraylist来接收从系统中获取的短信铃声数据 */
        ringlist = new ArrayList<String>();
        /* 添加“跟随系统”选项 */
        ringlist.add("跟随系统");
		/* 获取RingtoneManager */
        rm = new RingtoneManager(mContext);
		/* 指定获取类型为短信铃声 */
        rm.setType(RingtoneManager.TYPE_NOTIFICATION);
		/* 创建游标 */
        cursor = rm.getCursor();
		/* 游标移动到第一位，如果有下一项，则添加到ringlist中 */
        if (cursor.moveToFirst()) {
            do { // 游标获取RingtoneManager的列inde x
                ringlist.add(cursor
                        .getString(RingtoneManager.TITLE_COLUMN_INDEX));
            } while (cursor.moveToNext());
        }
    }

    @Override
    public int getCount() {
        return ringlist.size();
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
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.adapter_frag_myself_ringtone, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.cb.setBackgroundResource(map.get(position) == null ? R.drawable.pressed
                        : R.drawable.checked);
        vh.tv.setText(ringlist.get(position));
        return convertView;
    }


    public static class ViewHolder {
        private TextView tv;
        public ImageView cb;
        public ViewHolder(View v) {
			/* 组件初始化 */
            this.tv = (TextView) v.findViewById(R.id.list_frag_ringtone_tv);
            this.cb = (ImageView) v.findViewById(R.id.list_frag_ringtone_cb);
        }
    }
}
