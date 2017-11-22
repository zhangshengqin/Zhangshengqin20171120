package com.bwie.test.fjd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.test.fjd.R;
import com.bwie.test.fjd.fenlei.BannerView;
import com.bwie.test.fjd.fenlei.Fragment1_shop;
import com.bwie.test.fjd.fenlei.Kind_main;
import com.bwie.test.fjd.shouyeshouye.BannerPrecenter;
import com.bwie.test.fjd.shouyeshouye.KindBean;
import com.google.gson.Gson;

import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/31.
 */

public class DiscoverFragment extends Fragment implements BannerView, com.bwie.test.fjd.shouyeshouye.BannerView {

    private View view;
    @BindView(R.id.kind_name)
    ListView lv_kind_name;

    private BannerPrecenter bannerPrecenter;
    private Kind_main kind_main;

    private ParamsCid paramsCid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_item2, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Fragment1_shop shop = new Fragment1_shop();
        Bundle bundle = new Bundle();
        bundle.putString("cid", 1 + "");
        shop.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_child_kind, shop).commit();
        initData();

    }

    private void initData() {
        bannerPrecenter = new BannerPrecenter(this);
        bannerPrecenter.gainKind();
    }

    @Override
    public void gainSuc(String data) {

    }

    @Override
    public void gainFail() {

    }

    @Override
    public void kindSuc(final String data) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    parseData(data);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void parseData(String data) {
        Gson gson = new Gson();
        KindBean kindBean = gson.fromJson(data, KindBean.class);
        final List<KindBean.DataBean> kind = kindBean.getData();


        Iterator<KindBean.DataBean> iterator = kind.iterator();
        while (iterator.hasNext()) {
            KindBean.DataBean next = iterator.next();
            if (next.getIshome().equals("0")) {
                iterator.remove();
            }
        }
        kind_main = new Kind_main(getActivity(), kind);
        lv_kind_name.setAdapter(kind_main);
        lv_kind_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment1_shop shop = new Fragment1_shop();
                //发送下标
                kind_main.changePosition(i);
                int cid = kind.get(i).getCid();

//                paramsCid.gainCid(cid+"");
                Bundle bundle = new Bundle();
                bundle.putString("cid", cid + "");
                shop.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_child_kind, shop).commit();

            }
        });
    }

    public void setParagms(ParamsCid paramsCid) {
        this.paramsCid = paramsCid;
    }

    public interface ParamsCid {
        void gainCid(String cid);
    }

    @Override
    public void kindFail() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
