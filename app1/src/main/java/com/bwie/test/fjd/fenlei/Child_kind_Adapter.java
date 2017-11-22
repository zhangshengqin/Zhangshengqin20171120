package com.bwie.test.fjd.fenlei;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.test.fjd.R;

import java.util.List;



/**
 * Created by asus on 2017/10/8.
 */

public class Child_kind_Adapter extends BaseAdapter {
    private final Context context;
    private final List<ChildKindBean.DataBean> list;

    public Child_kind_Adapter(Context context, List<ChildKindBean.DataBean> list) {
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
        ViewHolder vh=null;
        if(view==null)
        {
            vh=new ViewHolder();
            view=View.inflate(context, R.layout.kind_child,null);
            vh.tv_kind_chidname= (TextView) view.findViewById(R.id.tv_child_name_kind);
            vh.gv_child= (RecyclerView) view.findViewById(R.id.gv_child_kind);
            view.setTag(vh);
        }
        else
        {
            vh= (ViewHolder) view.getTag();
        }
        ChildKindBean.DataBean dataBean = list.get(i);
        String name = dataBean.getName();
        vh.tv_kind_chidname.setText(name);
        //子分类的recyclerView
        Child_gv_kindAdapter child_gv_kindAdapter=new Child_gv_kindAdapter(context,dataBean.getList());
        vh.gv_child.setLayoutManager(new GridLayoutManager(context,3));
        vh.gv_child.setAdapter(child_gv_kindAdapter);
        return view;
    }
    class ViewHolder{
         TextView tv_kind_chidname;
        RecyclerView gv_child;
    }
}
