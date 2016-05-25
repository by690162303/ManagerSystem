package com.bcx.managersystem.parse;

import com.bcx.managersystem.entity.Person;
import com.bcx.managersystem.net.BaseParse;
import com.google.gson.Gson;

/**
 * Created by 白杨 on 2016/5/5.
 */
public class Personpserse extends BaseParse {
    @Override
    public Object myParse(String str) {
        Gson gson = new Gson();
        return  gson.fromJson(str, Person.class);
    }
}
