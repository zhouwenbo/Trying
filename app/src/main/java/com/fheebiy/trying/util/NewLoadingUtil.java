package com.fheebiy.trying.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;

import com.fheebiy.trying.R;


/**
 * Created on 2018/7/26.
 *
 * @author bob zhou.
 * Description: loading框
 */
public class NewLoadingUtil {

    private Dialog mDialog;
    private boolean mIsShow;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showLoading(Activity context) {
        if (context.isFinishing() || context.isDestroyed()) {
            return;
        }
        if (mIsShow) {
            return;
        }

        if ((mDialog == null)) {
            newLoading(context);
        } else {
            stopLoading();
            newLoading(context);
        }

        mDialog.show();
        mIsShow = true;
    }

    private void newLoading(Activity context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.new_loading_dialog, null);
        mDialog = new Dialog(context, R.style.Loading_dialog);

       /* mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mDialog.getWindow().setStatusBarColor(Color.TRANSPARENT);*/

        mDialog.setContentView(R.layout.new_loading_dialog);
    }

    public void stopLoading() {
        if (mDialog != null) {
            mIsShow = false;
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
