package com.fheebiy.trying.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.fheebiy.trying.util.CommonUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Lenovo on 15-3-12.
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("msg");
        if(StringUtils.isNotEmpty(str)){
            CommonUtil.toast(context, str);
        }

    }


}
