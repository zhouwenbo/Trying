package com.fheebiy.trying.activity.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import com.fheebiy.trying.R;
import com.fheebiy.trying.util.CommonUtil;

import java.io.File;

/**
 * Created by zhouwenbo on 15/8/20.
 */
public class CameraActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Log.d(CommonUtil.LOG_TAG_S, "CameraActivity onCreate");
    }


    public void startGo(View view) {
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String name = System.currentTimeMillis() + ".jpg";
        File file = new File(path, name);
        Uri mSavedPicUri = Uri.fromFile(file);
        Log.d("CameraActivity", "output pic uri =" + mSavedPicUri);
        intent.putExtra("output", mSavedPicUri);
        intent.addCategory("android.intent.category.DEFAULT");
        this.startActivityForResult(intent, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}