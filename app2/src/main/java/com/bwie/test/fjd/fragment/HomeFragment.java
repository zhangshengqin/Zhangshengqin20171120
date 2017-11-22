package com.bwie.test.fjd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.test.fjd.R;
import com.bwie.test.fjd.shouyeshouye.Auto_AlpaTitle;
import com.bwie.test.fjd.shouyeshouye.BannerBean;
import com.bwie.test.fjd.shouyeshouye.BannerPrecenter;
import com.bwie.test.fjd.shouyeshouye.BannerView;
import com.bwie.test.fjd.shouyeshouye.KindAdapter;
import com.bwie.test.fjd.shouyeshouye.KindBean;
import com.bwie.test.fjd.shouyeshouye.MiaoshaAdapter;
import com.bwie.test.fjd.shouyeshouye.TuijianAdapter;
import com.bwie.test.fjd.shouyeshouye.Vp_kind_head_Adapter;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/10/31.
 */

public class HomeFragment extends Fragment implements BannerView, ViewPager.OnPageChangeListener {


    @BindView(R.id.ll_allboor)
    LinearLayout llAllboor;
    private View view;
    private int maxPage = 10;
    private LinearLayout ll_sao;
    private BannerPrecenter bannerPrecenter;
    @BindView(R.id.tv_tuijian)
    TextView tv_tuijian;
    @BindView(R.id.iv_banner)
    XBanner iv_banner;
    private List<String> list_img;
    @BindView(R.id.ll_dot)
    LinearLayout ll_dot;
    @BindView(R.id.vp_head_kind)
    ViewPager vp_head_kind;
    @BindView(R.id.rcv_show)
    RecyclerView rcv_show;
    @BindView(R.id.rcv_miaosha)
    RecyclerView rcv_miaosha;
    @BindView(R.id.tv_miaosha_shi)
    TextView tv_miaosha_shi;
    @BindView(R.id.tv_miaosha_minter)
    TextView tv_miaosha_minter;
    @BindView(R.id.tv_miaosha_second)
    TextView tv_miaosha_second;
    @BindView(R.id.tv_miaosha_time)
    TextView tv_miaosha_time;
    private List<ImageView> img_list;
    @BindView(R.id.nest_scroll)
    Auto_AlpaTitle nest_scroll;
    @BindView(R.id.ll_nest_toolBar)
    LinearLayout ll_nest_toolBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment_item1, null);
        ButterKnife.bind(this, view);
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        int marig = getResources().getDimensionPixelSize(resourceId);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // initView();

    }

//    private void initView() {
//
//        //ll_sao = view.findViewById(R.id.ll_sao);
//        ll_sao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentIntegrator iit = new IntentIntegrator(getActivity());
//
////修改前后摄像头
//                iit.setCameraId(0);
//                iit.setPrompt("欢迎进入京东扫码环节");
//                iit.setTimeout(5000);
//                iit.setOrientationLocked(false)
//                   //     .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
//                        .initiateScan();// 初始化扫描
//
//            }
//        });
//
//
//    }

    @Override
    public void onResume() {
        super.onResume();


        ImageView iv_sao = (ImageView) view.findViewById(R.id.iv_sao);
        ImageView iv_msg = (ImageView) view.findViewById(R.id.iv_msg);
        TextView tv_sao = (TextView) view.findViewById(R.id.tv_sao);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        ll_dot.removeAllViews();
        nest_scroll.setTitleAndHead(ll_nest_toolBar, iv_banner, iv_sao, iv_msg, tv_msg, tv_sao);
        initData();
    }

    private void initData() {
        bannerPrecenter = new BannerPrecenter(this);
        //调用precenter方法
        bannerPrecenter.gainBanner();
        bannerPrecenter.gainKind();

    }


    @Override
    public void gainSuc(final String data) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pareseData(data);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void pareseData(String data) {
        list_img = new ArrayList<>();
        Gson gson = new Gson();
        BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
        if (bannerBean == null) {
            return;
        }
        //获得秒杀数据
        BannerBean.MiaoshaBean miaosha = bannerBean.getMiaosha();

        List<BannerBean.MiaoshaBean.ListBeanX> miaosha_list = miaosha.getList();
        MiaoshaAdapter miaoshaAdapter = new MiaoshaAdapter(getActivity(), miaosha_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置水平滑动
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcv_miaosha.setLayoutManager(linearLayoutManager);
        rcv_miaosha.setAdapter(miaoshaAdapter);
        //获得为你推荐的数据
        BannerBean.TuijianBean tuijian = bannerBean.getTuijian();

        List<BannerBean.TuijianBean.ListBean> list = tuijian.getList();
        //设置recyclerview适配器
        TuijianAdapter tuijianAdapter = new TuijianAdapter(getActivity(), list);
        rcv_show.setLayoutManager(new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rcv_show.setAdapter(tuijianAdapter);
        String name = tuijian.getName();
        tv_tuijian.setText(name);
//轮播图数据
        final List<BannerBean.DataBean> been = bannerBean.getData();
        for (int i = 0; i < been.size(); i++) {
            int type = been.get(i).getType();
            if (type == 0) {
                list_img.add(been.get(i).getIcon());

            }
        }
        iv_banner.setData(list_img, null);
        iv_banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(list_img.get(position)).into((ImageView) view);
            }
        });

    }

    @Override
    public void gainFail() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Banner请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void kindSuc(final String data) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //解析分类
                    pareseKindData(data);
                }
            });
        }
    }

    private void pareseKindData(String data) {
        Gson gson = new Gson();
        KindBean kindBean = gson.fromJson(data, KindBean.class);
        if (kindBean != null) {
            List<KindBean.DataBean> kind = kindBean.getData();
            Iterator<KindBean.DataBean> iterator = kind.iterator();
            while (iterator.hasNext()) {
                KindBean.DataBean next = iterator.next();
                if (next.getIshome().equals("0")) {
                    iterator.remove();
                }
            }
            if (getActivity() != null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());

                //向上取整
                int totalPage = (int) Math.ceil(kind.size() * 1.0 / maxPage);
                System.out.println("totalPage" + totalPage);
                List<View> viewList = new ArrayList<>();
                for (int i = 0; i < totalPage; i++) {
                    RecyclerView rcv = (RecyclerView) inflater.inflate(R.layout.head_rcv_kind, vp_head_kind, false);

                    rcv.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                    KindAdapter kindAdapter = new KindAdapter(getActivity(), kind, i, maxPage);
                    rcv.setAdapter(kindAdapter);
                    viewList.add(rcv);
                }


                //为viewpager发送recycleview集合
                vp_head_kind.setAdapter(new Vp_kind_head_Adapter(viewList));
                //设置监听
                vp_head_kind.addOnPageChangeListener(this);

            }
        }

    }

    @Override
    public void kindFail() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "分类请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < 2; i++) {
            if (i == position) {
                img_list.get(i).setImageResource(R.drawable.shape_select);
            } else {
                img_list.get(i).setImageResource(R.drawable.shape_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
