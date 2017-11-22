package com.bwie.test.fjd.fenlei01;


import java.util.Comparator;

/**
 * Created by asus on 2017/10/15.
 */

public class SaleOrder implements Comparator<ShopChildKindBean.DataBean>{
    private boolean flag;

    public SaleOrder(boolean flag) {
        this.flag = flag;
    }


    @Override
    public int compare(ShopChildKindBean.DataBean c1, ShopChildKindBean.DataBean c2) {
        if (flag) {
            return c1.getSalenum() - c2.getSalenum();
        } else {
            return c2.getSalenum() - c1.getSalenum();
        }
    }
}
