package zhangshengqin.bwie.com.i;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import zhangshengqin.bwie.com.i.adapter.MainAdapter;
import zhangshengqin.bwie.com.i.app.Api;
import zhangshengqin.bwie.com.i.bean.Bean;
import zhangshengqin.bwie.com.i.bean.MyString;
import zhangshengqin.bwie.com.i.presenter.MyPresenter;
import zhangshengqin.bwie.com.i.view.MyViewInfo;

public class MainActivity extends AppCompatActivity implements MyViewInfo {

    private RecyclerView rlv;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPresenter myPresenter = new MyPresenter(this);
        myPresenter.Data(Api.URL);
        rlv = (RecyclerView) findViewById(R.id.rlv);
    }


    @Override
    public void showSuccess(final List<Bean.DataBean> list) {
        rlv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(list, this);
        rlv.setAdapter(adapter);
        adapter.setOnClickLisener(new MainAdapter.OnClickLisener() {
            @Override
            public void OnDainji(View v, int position) {
                EventBus.getDefault().postSticky(new MyString(list.get(position).getVedio_url()));

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);

//                intent.putExtra("url", list.get(position).getVedio_url());

                startActivity(intent);
            }

            @Override
            public void OnLong(View v, int position) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("mm",list.get(position).getVedio_url());

                startActivity(intent);
            }
        });
    }

    @Override
    public void failError(String msg) {

    }
}
