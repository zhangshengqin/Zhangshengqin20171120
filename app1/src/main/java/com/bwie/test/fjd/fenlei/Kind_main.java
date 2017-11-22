package com.bwie.test.fjd.fenlei;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.test.fjd.R;

import java.util.List;



/**
 * Created by asus on 2017/10/7.
 */

public class Kind_main extends BaseAdapter {
    private final Context context;
    private final List<com.bwie.test.fjd.shouyeshouye.KindBean.DataBean> list;
    private int position;



    public Kind_main(Context context, List<com.bwie.test.fjd.shouyeshouye.KindBean.DataBean> list) {
        this.context=context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null)
        {
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.kind_name_item,null);
            holder.tv_kind_name= (TextView) view.findViewById(R.id.tv_kind_name);
            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }
        if(i==position)
        {
            //改变选中背景
           holder.tv_kind_name.setSelected(true);
            holder.tv_kind_name.setTextColor(Color.RED);
        }
        else {
            holder.tv_kind_name.setSelected(false);
            holder.tv_kind_name.setTextColor(Color.BLACK);
        }
        String name = list.get(i).getName();
        holder.tv_kind_name.setText(name);
        return view;
    }

    public void changePosition(int i) {

        if(i!=position) {
            position = i;
            notifyDataSetChanged();
        }
    }

    class ViewHolder{
      TextView tv_kind_name;
    }
}
