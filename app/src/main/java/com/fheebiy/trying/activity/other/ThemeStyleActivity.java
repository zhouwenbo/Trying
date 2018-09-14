package com.fheebiy.trying.activity.other;

import android.app.Activity;
import android.os.Bundle;
import com.fheebiy.trying.R;

/**
 * Created by bob zhou on 14-8-6.
 */
public class ThemeStyleActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.theme1);
        setContentView(R.layout.style_theme);
    }
}
