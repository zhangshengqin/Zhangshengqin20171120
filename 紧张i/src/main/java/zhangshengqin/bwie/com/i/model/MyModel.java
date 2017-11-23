package zhangshengqin.bwie.com.i.model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zhangshengqin.bwie.com.i.app.ApiService;
import zhangshengqin.bwie.com.i.bean.Bean;

/**
 * Created by 额头发 on 2017/11/23.
 */

public class MyModel implements MyModelInfo{
    @Override
    public void getData(String url, final RequestListener requestListener) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                //结合rxjava
        Observable<Bean> data = apiService.getData();
        data.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Bean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                requestListener.OnError(e.getMessage().toString());
                            }

//                            @Override
//                            public void onNext(MyBean songListBean) {
//                                List<MyBean.SongListBean> list = songListBean.getSong_list();
//                                onClientListener.OnSuccess(list);
//                            }

                            @Override
                            public void onNext(Bean bean) {
                                List<Bean.DataBean> list = bean.getData();
                                requestListener.OnSuccess(list);
                            }
                        });
    }
}
