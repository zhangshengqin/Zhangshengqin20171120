package com.bwie.test.fjd.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bwie.test.fjd.R;
import com.bwie.test.fjd.acrivity.RegActivity;
import com.bwie.test.fjd.adapter.MyFragmentSJ;
import com.bwie.test.fjd.adapter.MyFragmentXBBbb;

/**
 * Created by Administrator on 2017/10/31.
 */

public class MyFragment extends Fragment {
    private RecyclerView my_recycle_one;
    private RelativeLayout layout;

    private RecyclerView my_recycle_two;
    private MyFragmentXBBbb twoadapter;
    private ImageView my_title_img;
    private static final int REQUESTCODE = 520;
    private boolean iflogin;
    private MyFragmentSJ oneadapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item5, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//设置第一个recyclerview的适配器和item点击事件
        initview();
        GridLayoutManager gmanager = new GridLayoutManager(getActivity(), 5);
        my_recycle_one.setLayoutManager(gmanager);
        oneadapter = new MyFragmentSJ();
        my_recycle_one.setAdapter(oneadapter);


        oneadapter.setOnItemTwoClickLintener(new MyFragmentSJ.OnItemTwoClickLintener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (iflogin) {
                    Toast.makeText(getContext(), "已经登陆了", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), RegActivity.class);
                    startActivityForResult(intent, REQUESTCODE);
                }
            }
        });
        //点击头部实现跳转登陆页面
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });
        //设置第二个recyclerview的适配器和item点击事件
        GridLayoutManager gmanagertwo = new GridLayoutManager(getActivity(), 4);
        my_recycle_two.setLayoutManager(gmanagertwo);
        twoadapter = new MyFragmentXBBbb();
        twoadapter.setOnItemOneClickLintener(new MyFragmentXBBbb.OnItemOneClickLintener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (iflogin) {
                    Toast.makeText(getContext(), "已经登陆了", Toast.LENGTH_SHORT).show();
                    /*Intent intent=new Intent();
                    startActivity(intent);*/
                } else {
                    Intent intent = new Intent(getActivity(), RegActivity.class);
                    startActivityForResult(intent, REQUESTCODE);
                }
            }
        });
    }

    private void initview() {
        layout = (RelativeLayout) view.findViewById(R.id.my_linearlayout);
        my_recycle_one = (RecyclerView) view.findViewById(R.id.my_recycle_one);
        my_recycle_two = (RecyclerView) view.findViewById(R.id.my_recycle_two);
        my_title_img = (ImageView) view.findViewById(R.id.my_title_img);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==520&&requestCode==521){
            String imgtitle = data.getStringExtra("imgtitle");
            iflogin = data.getBooleanExtra("iflogin", false);
            loadCirclePic(getContext(), imgtitle, my_title_img);
        }
    }

    public static void loadCirclePic(final Context context, String url, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.b3h)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
