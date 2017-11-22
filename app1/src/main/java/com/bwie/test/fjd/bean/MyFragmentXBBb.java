package com.bwie.test.fjd.bean;

/**
 * Created by Administrator on 2017/10/31.
 */

public class MyFragmentXBBb {
    private String twoname;
    private int twoimgresources;
    private String bottomname;

    public MyFragmentXBBb(String twoname, int twoimgresources, String bottomname) {
        this.twoname = twoname;
        this.twoimgresources = twoimgresources;
        this.bottomname = bottomname;
    }

    public String getTwoname() {
        return twoname;
    }

    public void setTwoname(String twoname) {
        this.twoname = twoname;
    }

    public int getTwoimgresources() {
        return twoimgresources;
    }

    public void setTwoimgresources(int twoimgresources) {
        this.twoimgresources = twoimgresources;
    }

    public String getBottomname() {
        return bottomname;
    }

    public void setBottomname(String bottomname) {
        this.bottomname = bottomname;
    }
}
