package com.fheebiy.trying.activity.router;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fheebiy.interfaces.RouterUri;
import com.fheebiy.trying.R;

/**
 * Created on 2018/12/6.
 *
 * @author bob zhou.
 * Description: router测试类
 */

@RouterUri(scheme = "http", host = "fheebiy.com", path = "/jump_test")
public class RouterMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router_main);
    }
}
