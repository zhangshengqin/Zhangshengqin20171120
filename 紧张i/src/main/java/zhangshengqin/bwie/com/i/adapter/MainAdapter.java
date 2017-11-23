package zhangshengqin.bwie.com.i.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.List;

import zhangshengqin.bwie.com.i.R;
import zhangshengqin.bwie.com.i.bean.Bean;

/**
 * Created by 额头发 on 2017/11/23.
 */

public class MainAdapter extends RecyclerView.Adapter{

    private ImageLoader imageLoader;
    List<Bean.DataBean> list;
    Context context;
    private OnClickLisener onClickLisener;
    private final DisplayImageOptions options;
    private MyViewHolder myViewHolder;


    public OnClickLisener getOnClickLisener() {
        return onClickLisener;

    }

    public void setOnClickLisener(OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;

    }

    public interface OnClickLisener {
        void OnDainji(View v, int position);
        void OnLong(View v, int position);

    }

    public MainAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        imageLoader = ImageLoader.getInstance();

        File file = new File(Environment.getExternalStorageDirectory(), "Bwei");

        if (!file.exists())

            file.mkdirs();



        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)

                .diskCache(new UnlimitedDiskCache(file))

                .build();



        imageLoader.init(configuration);


        options = new DisplayImageOptions.Builder()

                .showImageOnLoading(R.mipmap.ic_launcher)

                .cacheOnDisk(true)

                .build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        myViewHolder = (MyViewHolder) holder;
        myViewHolder.textView.setText(list.get(position).getContent());
        getimage(list.get(position).getImage_url(), myViewHolder.imageView);

        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                onClickLisener.OnDainji(view, position);

            }

        });

        myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override

            public boolean onLongClick(View view) {

                onClickLisener.OnLong(view, position);

                return true;

            }

        });

        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                onClickLisener.OnDainji(view, position);

            }

        });

        myViewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override

            public boolean onLongClick(View view) {

                onClickLisener.OnLong(view, position);

                return true;

            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {





        private final TextView textView;
        private final ImageView imageView;
        public MyViewHolder(View itemView) {

            super(itemView);

            textView = itemView.findViewById(R.id.text);

            imageView = itemView.findViewById(R.id.image);





        }

    }


    public void getimage(String path, ImageView imageView) {



        DisplayImageOptions options = new DisplayImageOptions.Builder()

                .cacheOnDisk(true)

                .cacheInMemory(true)

                .build();

        ImageLoader.getInstance().displayImage(path, imageView, options);





    }

}
