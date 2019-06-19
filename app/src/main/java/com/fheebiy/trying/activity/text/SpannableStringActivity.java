package com.fheebiy.trying.activity.text;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.TextView;

import com.fheebiy.trying.R;

public class SpannableStringActivity extends AppCompatActivity {

    private TextView mTv1;

    private TextView mTv2;


    public static final String FES_STR = "国庆过去姐姐";

    public static final String TAG = "ZHOUWENBO_SS";

    private SparseIntArray mSortArray = new SparseIntArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string);

        mTv1 = findViewById(R.id.text1);
        mTv2 = findViewById(R.id.text2);

        String normalStr = getString(R.string.fes_str_normal, FES_STR);

        SpannableString spannableString = new SpannableString(normalStr);
        AbsoluteSizeSpan sizeSpan01 = new AbsoluteSizeSpan(16, true);

        StyleSpan bold = new StyleSpan(Typeface.BOLD);

        int start = 4;
        int end = start + FES_STR.length();
        spannableString.setSpan(sizeSpan01, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(bold, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTv1.setText(spannableString);

        int x = mSortArray.get(1);
        Log.d(TAG, "X = " + x);

        mSortArray.put(2, 29);

        Log.d(TAG, "y = " + mSortArray.get(2));

        mSortArray.put(1, 2003);


        Log.d(TAG, "x = " + mSortArray.get(1));
    }
}
