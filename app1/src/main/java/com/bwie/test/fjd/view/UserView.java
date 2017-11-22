package com.bwie.test.fjd.view;

/**
 * Created by Administrator on 2017/10/31.
 */

public interface UserView {
    void gainFail();

    void gainSuc(String data);

    void loaginFail(String msg);

    void gainUserInfoFail();

    void gainUserSuc(String data);

    void gainaddSuc(String data);

    void unloadSuc(String msg);

    void unloadFail(String msg);

    void setNickSuc(String msg);
}
