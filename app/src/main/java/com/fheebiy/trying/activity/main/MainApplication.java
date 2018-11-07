package com.fheebiy.trying.activity.main;

import android.app.Application;
import android.content.Intent;
import com.fheebiy.trying.service.PushService;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by bob zhou on 15-3-24.
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
