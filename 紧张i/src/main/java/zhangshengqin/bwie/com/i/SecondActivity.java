package zhangshengqin.bwie.com.i;

import android.content.Intent;

import android.os.Bundle;

import android.os.Environment;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.ProgressBar;

import android.widget.TextView;

import zhangshengqin.bwie.com.i.utils.DownloadUtil;

public class SecondActivity extends AppCompatActivity {


        private static final String TAG = MainActivity.class.getSimpleName();
        private ProgressBar mProgressBar;
        private Button start;
        private Button pause;


        private TextView total;

        private int max;

        private DownloadUtil mDownloadUtil;

        @Override

        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_second);

            total= (TextView) findViewById(R.id.textView);

            start= (Button) findViewById(R.id.start);

            pause= (Button) findViewById(R.id.delete);

            mProgressBar= (ProgressBar) findViewById(R.id.progressBar);

            Intent intent = getIntent();

            String urlString = intent.getStringExtra("mm");

//        String urlString = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";

            String localPath = Environment.getExternalStorageDirectory()

                    .getAbsolutePath() + "/local";

            mDownloadUtil = new DownloadUtil(2, localPath, "adc.mp4", urlString,

                    this);

            mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {


                @Override

                public void downloadStart(int fileSize) {

                    // TODO Auto-generated method stub

                    Log.w(TAG, "fileSize::" + fileSize);

                    max = fileSize;

                    mProgressBar.setMax(fileSize);

                }



                @Override

                public void downloadProgress(int downloadedSize) {

                    // TODO Auto-generated method stub

                    Log.w(TAG, "Compelete::" + downloadedSize);

                    mProgressBar.setProgress(downloadedSize);

                    total.setText((int) downloadedSize * 100 / max + "%");

                }



                @Override

                public void downloadEnd() {

                    // TODO Auto-generated method stub

                    Log.w(TAG, "ENd");

                }

            });

            start.setOnClickListener(new View.OnClickListener() {



                @Override

                public void onClick(View arg0) {

                    // TODO Auto-generated method stub

                    mDownloadUtil.start();

                }

            });

            pause.setOnClickListener(new View.OnClickListener() {



                @Override

                public void onClick(View arg0) {

                    // TODO Auto-generated method stub

                    mDownloadUtil.pause();

                }

            });







        }
}
