/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


// Need the following import to get access to the app resources, since this
// class is in a sub-package.

package com.fheebiy.trying.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fheebiy.trying.R;


/**
 * Created on 2019/2/22.
 *
 * @author bob zhou.
 * Description: View的测量，即onMeasure
 * 请看measureWidth 方法
 *
 */
public class LabelView extends View {
    private static final String TAG = "LabelView";
    private Paint mTextPaint;
    private String mText;
    private int mAscent;

    /**
     * 调整这个值，查看效果
     * 当width = 'wrap_content'的时候
     * 当这个值是true的时候，比较符合预期
     * 当这个值是false的时候，发现一个labelView的宽度会特别宽
     * 看代码就知道为什么了
     */
    private boolean measureWidth = false;

    /**
     * Constructor.  This version is only needed if you will be instantiating
     * the object manually (not from a layout XML file).
     * @param context
     */
    public LabelView(Context context) {
        super(context);
        initLabelView();
    }

    /**
     * Construct object, initializing with any attributes we understand from a
     * layout file. These attributes are defined in
     * SDK/assets/res/any/classes.xml.
     *
     * @see android.view.View#View(android.content.Context, android.util.AttributeSet)
     */
    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLabelView();

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.LabelView);

        CharSequence s = a.getString(R.styleable.LabelView_labelText);
        if (s != null) {
            setText(s.toString());
        }

        // Retrieve the color(s) to be used for this view and apply them.
        // Note, if you only care about supporting a single color, that you
        // can instead call a.getColor() and pass that to setTextColor().
        setTextColor(a.getColor(R.styleable.LabelView_labelTextColor, 0xFF000000));

        int textSize = a.getDimensionPixelOffset(R.styleable.LabelView_labelTextSize, 0);
        if (textSize > 0) {
            setTextSize(textSize);
        }

        a.recycle();
    }

    private final void initLabelView() {
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        // Must manually scale the desired text size to match screen density
        mTextPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
        mTextPaint.setColor(0xFF000000);
        setPadding(3, 3, 3, 3);
    }

    /**
     * Sets the text to display in this label
     * @param text The text to display. This will be drawn as one line.
     */
    public void setText(String text) {
        mText = text;
        requestLayout();
        invalidate();
    }

    /**
     * Sets the text size for this label
     * @param size Font size
     */
    public void setTextSize(int size) {
        // This text size has been pre-scaled by the getDimensionPixelOffset method
        mTextPaint.setTextSize(size);
        requestLayout();
        invalidate();
    }

    /**
     * Sets the text color for this label.
     * @param color ARGB value for the text
     */
    public void setTextColor(int color) {
        mTextPaint.setColor(color);
        invalidate();
    }

    /**
     * @see android.view.View#measure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            // 这里很重要哦，可以清晰的看到measure的作用，以后涉及到的都可以参考这个来做
            if (measureWidth) {
                result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
                        + getPaddingRight();
                Log.d(TAG, "measureWidth result = " + result + " ,specSize = " + specSize);
                if (specMode == MeasureSpec.AT_MOST) {
                    // Respect AT_MOST value if that was what is called for by measureSpec
                    result = Math.min(result, specSize);
                }
            } else {
                result = specSize;
            }

        }

        return result;
    }

    /**
     * Determines the height of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        mAscent = (int) mTextPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (-mAscent + mTextPaint.descent()) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Render the text
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, getPaddingLeft(), getPaddingTop() - mAscent, mTextPaint);
    }
}