package com.bcx.managersystem.net;

/**
 * Created by 白杨 on 2016/5/5.
 */
public interface onNetCompleteListener <T>{
    public void Net_Ok(T t);
    public void Net_Error();
    public void Net_Execption();
}
