package com.bwie.test.fjd.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.test.fjd.R;
import com.bwie.test.fjd.acrivity.RegActivity;
import com.bwie.test.fjd.acrivity.UserSetActivity;
import com.bwie.test.fjd.bean.shujubean.UserBean;
import com.bwie.test.fjd.dingdanguanli.MyOrderActivity;
import com.bwie.test.fjd.presenter.UserPresenter;
import com.bwie.test.fjd.view.UserView;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/31.
 */

public class MyFragment extends Fragment implements UserView {

    private static final int REGRESULT = 2222;
    @BindView(R.id.ll_my_reg)
    LinearLayout llMyReg;
    @BindView(R.id.my_user)
    RoundedImageView myUser;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.rl_reg)
    RelativeLayout rlReg;
    @BindView(R.id.tv_myOrder)
    TextView tvMyOrder;
    private View view;
    private SharedPreferences userAll;
    private UserPresenter up;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_item5, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        userAll = getActivity().getSharedPreferences("userAll", Context.MODE_PRIVATE);
        String uid = userAll.getString("uid", null);

        if (!TextUtils.isEmpty(uid)) {
            rlReg.setBackgroundResource(R.drawable.reg_bg);
            initData(uid);

        } else {
            rlReg.setBackgroundResource(R.drawable.normal_regbg);
            myUser.setImageResource(R.drawable.user);
        }
    }

    private void initData(String uid) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        up = new UserPresenter(this, getActivity());
        up.gainUserInfo(map);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    @OnClick(R.id.ll_my_reg)
    public void onViewClicked() {
        String uid = userAll.getString("uid", null);
        if (TextUtils.isEmpty(uid)) {
            startActivityForResult(new Intent(getActivity(), RegActivity.class), REGRESULT);
        } else {
            startActivity(new Intent(getActivity(), UserSetActivity.class));
        }

    }

    @Override
    public void gainFail() {

    }

    @Override
    public void gainSuc(String datas) {


    }

    @Override
    public void loaginFail(String msg) {

    }

    @Override
    public void gainUserInfoFail() {


    }

    @Override
    public void gainUserSuc(String data) {

        System.out.println("fragmentdata=====data====" + data);
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(data, UserBean.class);
        UserBean.DataBean datas = userBean.getData();
        if (!TextUtils.isEmpty(datas.getIcon())) {
            System.out.println("touxiang=========" + datas.getIcon().toString());
            myUser.setOval(true);
            if (getActivity() != null) {
                Glide.with(getActivity().getApplicationContext())
                        .load(datas.getIcon().toString())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .error(R.mipmap.ic_launcher_round)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .dontAnimate()
                        .into(myUser);
            }

        }
        if (datas.getNickname() != null) {
            tvNickname.setText(datas.getNickname().toString());
        } else {
            tvNickname.setText("小秘密");
        }


    }

    @Override
    public void gainaddSuc(String data) {

    }

    @Override
    public void unloadSuc(String msg) {

    }

    @Override
    public void unloadFail(String msg) {

    }

    @Override
    public void setNickSuc(String msg) {

    }

    @OnClick(R.id.tv_myOrder)
    public void onOrderClicked() {
        startActivity(new Intent(getActivity(), MyOrderActivity.class));
    }
}

//另外一种布局
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        userAll = getActivity().getSharedPreferences("userAll", Context.MODE_PRIVATE);
//        String uid = userAll.getString("uid", null);
//        if (!TextUtils.isEmpty(uid)) {
//         //   rlReg.setBackgroundResource(R.drawable.reg_bg);
//            initData(uid);
//
//        } else {
//           // rlReg.setBackgroundResource(R.drawable.normal_regbg);
//            my_title_img.setImageResource(R.drawable.user);
//        }
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//    }
//
//    private void initData(String uid) {
//        Map<String, String> map = new HashMap<>();
//        map.put("uid", uid);
//        UserPresenter up = new UserPresenter(this, getActivity());
//        up.gainUserInfo(map);
//    }
//
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        myreg = (LinearLayout) view.findViewById(R.id.ll_my_reg);
////            myreg.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    String uid = userAll.getString("uid", null);
////                    if (TextUtils.isEmpty(uid)) {
////                        startActivityForResult(new Intent(getActivity(), RegActivity.class), REGRESULT);
////                    } else {
////                        startActivity(new Intent(getActivity(), UserSetActivity.class));
////                    }
////                }
////            });
//
//        //设置第一个recyclerview的适配器和item点击事件
//
//        initview();
//        GridLayoutManager gmanager = new GridLayoutManager(getActivity(), 5);
//        my_recycle_one.setLayoutManager(gmanager);
//        oneadapter = new MyFragmentSJ();
//        my_recycle_one.setAdapter(oneadapter);
//
//
//        oneadapter.setOnItemTwoClickLintener(new MyFragmentSJ.OnItemTwoClickLintener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                if (iflogin) {
//                    Toast.makeText(getContext(), "已经登陆了", Toast.LENGTH_SHORT).show();
//                } else {
//
//////                    Intent intent = new Intent(getActivity(), RegActivity.class);
//////                    startActivityForResult(intent, REQUESTCODE);
//                }
//            }
//        });
//        //点击头部实现跳转登陆页面
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), RegActivity.class);
//                startActivityForResult(intent, REQUESTCODE);
//            }
//        });
//        //设置第二个recyclerview的适配器和item点击事件
//        GridLayoutManager gmanagertwo = new GridLayoutManager(getActivity(), 4);
//        my_recycle_two.setLayoutManager(gmanagertwo);
//        twoadapter = new MyFragmentXBBbb();
//        twoadapter.setOnItemOneClickLintener(new MyFragmentXBBbb.OnItemOneClickLintener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                if (iflogin) {
//                    Toast.makeText(getContext(), "已经登陆了", Toast.LENGTH_SHORT).show();
//                    /*Intent intent=new Intent();
//                    startActivity(intent);*/
//                } else {
//                    Intent intent = new Intent(getActivity(), RegActivity.class);
//                    startActivityForResult(intent, REQUESTCODE);
//                }
//            }
//        });
//    }
//
//    private void initview() {
//        layout = (RelativeLayout) view.findViewById(R.id.my_linearlayout);
//        my_recycle_one = (RecyclerView) view.findViewById(R.id.my_recycle_one);
//        my_recycle_two = (RecyclerView) view.findViewById(R.id.my_recycle_two);
//        my_title_img = (ImageView) view.findViewById(R.id.my_title_img);
//        dengzhu = (TextView) view.findViewById(R.id.dengzhu);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 520 && requestCode == 521) {
//            String imgtitle = data.getStringExtra("imgtitle");
//            iflogin = data.getBooleanExtra("iflogin", false);
//            loadCirclePic(getContext(), imgtitle, my_title_img);
//        }
//    }
//
//    public static void loadCirclePic(final Context context, String url, final ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .placeholder(R.drawable.b3h)
//                .error(R.mipmap.ic_launcher)
//                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
//                .into(new BitmapImageViewTarget(imageView) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);
//                        imageView.setImageDrawable(circularBitmapDrawable);
//                    }
//                });
//    }
//
//    @Override
//    public void gainFail() {
//
//    }
//
//    @Override
//    public void gainSuc(String data) {
//
//    }
//
//    @Override
//    public void loaginFail(String msg) {
//
//    }
//
//    @Override
//    public void gainUserInfoFail() {
//
//    }
//
//    @Override
//    public void gainUserSuc(String data) {
//        Gson gson = new Gson();
//        UserBean userBean = gson.fromJson(data, UserBean.class);
//        UserBean.DataBean datas = userBean.getData();
//        if (!TextUtils.isEmpty(datas.getIcon())) {
//
//
//            if (getActivity() != null) {
//                Glide.with(getActivity().getApplicationContext())
//                        .load(datas.getIcon().toString())
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true)
//                        .error(R.mipmap.ic_launcher_round)
//                        .placeholder(R.mipmap.ic_launcher_round)
//                        .dontAnimate()
//                        .into(my_title_img);
//            }
//
//        }
//        if (datas.getNickname() != null) {
//            dengzhu.setText(datas.getNickname().toString());
//        } else {
//            dengzhu.setText("果果小夏");
//        }
//    }
//
//    @Override
//    public void gainaddSuc(String data) {
//
//    }
//
//    @Override
//    public void unloadSuc(String msg) {
//
//    }
//
//    @Override
//    public void unloadFail(String msg) {
//
//    }
//
//    @Override
//    public void setNickSuc(String msg) {
//
//    }

