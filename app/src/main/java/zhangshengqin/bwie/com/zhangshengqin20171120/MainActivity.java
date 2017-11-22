package zhangshengqin.bwie.com.zhangshengqin20171120;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhangshengqin.bwie.com.zhangshengqin20171120.app.Api;
import zhangshengqin.bwie.com.zhangshengqin20171120.bean.Bean;
import zhangshengqin.bwie.com.zhangshengqin20171120.presenter.MyPresenter;
import zhangshengqin.bwie.com.zhangshengqin20171120.view.MyViewInfo;

public class MainActivity extends AppCompatActivity implements MyViewInfo{

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyPresenter myPresenter = new MyPresenter(this);
        myPresenter.GetData(Api.PATH);

    }

    @Override
    public void showSuccess(List<Bean.RetBean.ListBean> list) {

    }

    @Override
    public void failError(String msg) {

    }
}
