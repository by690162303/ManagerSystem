package com.bcx.managersystem.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bcx.managersystem.R;
import com.bcx.managersystem.base.BaseFragment;

/**
 * 三
 */
public class Class_wed extends BaseFragment {


    public Class_wed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_class_wed, container, false);
        mContext = getContext();
        init_tv_add_class(v);
        return v;
    }


}
