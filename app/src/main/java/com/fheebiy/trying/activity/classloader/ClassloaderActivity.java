package com.fheebiy.trying.activity.classloader;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.Log;

import java.io.File;
import java.util.Date;

import dalvik.system.DexClassLoader;

public class ClassloaderActivity extends Activity {

    public static final String TAG = "ClassloaderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classloader);

        showClassloader();

        loadClass();
    }

    private void showClassloader() {
        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassLoader cl = getClassLoader();
                String dest = "";
                while (cl != null) {
                    dest += cl.getClass().getName() + "->";
                    cl = cl.getParent();
                }

                if (cl == null) {
                    dest += "null";
                }
                Log.d(TAG, dest);


                ClassLoader baseParent = ClassLoader.getSystemClassLoader().getParent();

                Log.d(TAG, "baseParent = " + baseParent);

            }
        });
    }

    private void loadClass() {
        findViewById(R.id.opt_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final File jarFile =
                        new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "sayhello_dex.jar");

                // 如果没有读权限,确定你在 AndroidManifest 中是否声明了读写权限
                Log.d(TAG, jarFile.canRead() + "");

                if (!jarFile.exists()) {
                    Log.e(TAG, "sayhello_dex.jar not exists");
                    return;
                }

                // getCodeCacheDir() 方法在 API 21 才能使用,实际测试替换成 getExternalCacheDir() 等也是可以的
                // 只要有读写权限的路径均可
                DexClassLoader dexClassLoader =
                        new DexClassLoader(jarFile.getAbsolutePath(), getExternalCacheDir().getAbsolutePath(), null, getClassLoader());
                try {
                    // 加载 HelloAndroid 类
                    Class clazz = dexClassLoader.loadClass("com.fheebiy.java.classloader.MyClsData");
                    // 强转成 ISayHello, 注意 ISayHello 的包名需要和 jar 包中的一致
                    String string = clazz.newInstance().toString();
                    Date date = (Date) clazz.newInstance();
                    Log.d(TAG, "STR = " + string);
                    Log.d(TAG, "date = " + date.getTime());

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
