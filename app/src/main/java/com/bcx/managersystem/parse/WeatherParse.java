package com.bcx.managersystem.parse;

import com.bcx.managersystem.entity.Weather;
import com.bcx.managersystem.net.BaseParse;
import com.google.gson.Gson;

/**
 * Created by 白杨 on 2016/5/16.
 */
public class WeatherParse extends BaseParse<Weather> {
    @Override
    public Weather myParse(String str) {
        Gson gson = new Gson();
        return  gson.fromJson(str, Weather.class);
    }
}
