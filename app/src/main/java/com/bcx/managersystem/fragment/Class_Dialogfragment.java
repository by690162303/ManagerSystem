package com.bcx.managersystem.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bcx.managersystem.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 白杨 on 2016/5/23.
 */
public class Class_Dialogfragment extends DialogFragment implements OnItemSelectedListener {
    private EditText et_class;
    private EditText et_room;
    private Spinner start_sp;
    private Spinner over_sp;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter_2;
    private String[] str;
    private List<String> list = new ArrayList<>();
    private int s_w = 0;
    private int o_w = 0;
    private int cless_jie = 0;
    private String tag;
    public Class_Dialogfragment(int cless_jie,String tt) {
        this.cless_jie = cless_jie;
        this.tag = tt;
        Log.e("aa",tt+" 第"+cless_jie);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);  //去除标题栏
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.class_dialog_frag, null);
        init_view(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view).setNeutralButton("取消", null).setPositiveButton("确认", null);
        return builder.create();
    }
    public void init_view(View v) {
        et_room = (EditText) v.findViewById(R.id.dialog_frag_et_room);
        et_class = (EditText) v.findViewById(R.id.dialog_frag_et_class);
        start_sp = (Spinner) v.findViewById(R.id.start_week);
        over_sp = (Spinner) v.findViewById(R.id.over_week);
        str = getResources().getStringArray(R.array.Spinner_num);
        list = Arrays.asList(str); // 数组转换为集合
        //适配器
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        //设置样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        start_sp.setAdapter(adapter);
        start_sp.setOnItemSelectedListener(this);
        over_sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        s_w = position + 1;
            List<String> l = new ArrayList<>();
            for (int i = s_w; i <= 20; i++) {
                l.add("" + i);
            }
            adapter_2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, l);
            over_sp.setAdapter(adapter_2);
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getContext(), "什么都没有选择", Toast.LENGTH_SHORT).show();

    }
}
