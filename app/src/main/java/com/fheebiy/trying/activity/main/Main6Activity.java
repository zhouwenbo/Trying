package com.fheebiy.trying.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.fheebiy.trying.R;

/**
 * Created on 2019/2/20.
 *
 * @author bob zhou.
 * Description:
 */
public class Main6Activity extends AppCompatActivity {


    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();



    }
}
