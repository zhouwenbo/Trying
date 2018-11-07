package com.fheebiy.trying.activity.async;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fheebiy.trying.R;
import com.fheebiy.trying.activity.IndexActivity;
import com.fheebiy.trying.util.Log;

public class AyncHttpNPEActivity extends Activity {

    public static final String TAG = "ZWBAyncHttpNPEActivity";

    private TextView mTextView;

    private Handler mHandler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage");
            if (AyncHttpNPEActivity.this.isFinishing()) {
                Log.d(TAG, "isFinishing");
                return;
            }

            if (AyncHttpNPEActivity.this.isDestroyed()) {
                Log.d(TAG, "isDestroyed");
                return;
            }
            mTextView.setText("same the back!!!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aync_http_npe);
        mTextView = findViewById(R.id.textview);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyThread().start();
            }
        });

        findViewById(R.id.btn_to_index).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AyncHttpNPEActivity.this, IndexActivity.class));
            }
        });
    }


    public class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mHandler.sendEmptyMessage(10);
            Log.d(TAG, "send msg");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler.removeMessages(10);
        Log.d(TAG, "removeCallbacksAndMessages");
    }
}
