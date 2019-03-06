package com.fheebiy.trying.fragment.scrollable;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.util.Log;

/**
 * Created on 2019/3/5.
 *
 * @author bob zhou.
 * Description:
 */
public class MyScrollableFrameLayout4 extends FrameLayout {

    private View mLinearLayout;

    private MarginLayoutParams mMarginLayoutParams;

    private int mExpendTopMargin;

    private int mHideTopMargin;


    private ScrollListner mScrollListener;

    private boolean hasSet;


    /**
     * 滑动速度监听
     */
    private VelocityTracker mVelocityTracker;

    public static final String TAG = "My_ScrollableFra_";


    private static final int INVALID_POINTER = -1;

    /**
     * 滑动的临界值
     */
    private int mTouchSlop;
    private float mLastMotionY;
    private float mLastMotionX;
    private float mDownMotionX;
    private float mDownMotionY;
    private int mActivePointerId;
    private int mUpFinalY;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private int mDownTopMargin;

    private float mLastMoveY;
    private boolean isVertical;


    public MyScrollableFrameLayout4(Context context) {
        super(context);
        init();
    }

    public MyScrollableFrameLayout4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollableFrameLayout4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    public void setScrollListener(ScrollListner listner) {
        this.mScrollListener = listner;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLinearLayout = findViewById(R.id.anim_viewpager);
        mMarginLayoutParams = (MarginLayoutParams) mLinearLayout.getLayoutParams();
        mExpendTopMargin = CommonUtil.dip2px(getContext(), 80);  //240
        mHideTopMargin = CommonUtil.dip2px(getContext(), 46);    //138
        Log.d(TAG, "start = " + mExpendTopMargin + " ,end = " + mHideTopMargin);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);
        final int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                float y = ev.getRawY();
                float x = ev.getRawX();
                mLastMotionY = y;
                mLastMotionX = x;
                mDownMotionX = x;
                mDownMotionY = y;
                mActivePointerId = ev.getPointerId(0);
                mUpFinalY = 0;
                mDownTopMargin = mMarginLayoutParams.topMargin;
                hasSet = false;
                isVertical = false;

                break;
            case MotionEvent.ACTION_MOVE:
                final int activePointerId = mActivePointerId;
                if (activePointerId == INVALID_POINTER) {
                    break;
                }

                final int pointerIndex = ev.findPointerIndex(activePointerId);
                y = ev.getRawY();
                x = ev.getRawX();
                final int yDiff = (int) Math.abs(y - mDownMotionY);
                final int xDiff = (int) Math.abs(x - mDownMotionX);
                int deltaY = (int) (y - mLastMotionY);
                boolean mIsVerticalScroll = false;
                if (!mIsVerticalScroll && yDiff > mTouchSlop && xDiff < yDiff) {
                    mIsVerticalScroll = true;
                    //ViewPager vp = mOnScrollListener.getViewPager();
                   /* if (vp != null) {
                        vp.requestDisallowInterceptTouchEvent(true);
                    }*/
                }
                boolean mIsUp = deltaY < 0;
                boolean isCeiling = mScrollListener.isCeiling();
                if (isCeiling && !hasSet) {
                    mLastMotionY = y;
                    hasSet = true;
                }
                if (isCeiling) {
                    Log.e(TAG, "mIsVerticalScroll = " + mIsVerticalScroll + " ,isCeiling = " + isCeiling + " ,xDiff = " + xDiff + " ,yDiff = " + yDiff);
                } else {
                    //Log.d(TAG, "mIsVerticalScroll = " + mIsVerticalScroll + " ,isCeiling = " + isCeiling);
                }

                isVertical = mIsVerticalScroll;

                if (mIsVerticalScroll && isCeiling) {
                    Log.e(TAG, "topMargin = " + mMarginLayoutParams.topMargin + " ,mIsUp = " + mIsUp);
                    if (mIsUp) {
                        if (mMarginLayoutParams.topMargin <= mExpendTopMargin) {

                            deltaY = (int) (y - mLastMotionY);
                            int currentMargin = mDownTopMargin + deltaY;
                            boolean intercept = true;
                            if (currentMargin <= mHideTopMargin) {
                                currentMargin = mHideTopMargin;

                                intercept = false;
                            }

                            if (mMarginLayoutParams.topMargin != currentMargin) {
                                mMarginLayoutParams.topMargin = currentMargin;
                                mLinearLayout.setLayoutParams(mMarginLayoutParams);
                            }
                            Log.e(TAG, "deltaY = " + deltaY + " intercept = " + intercept);
                            if (intercept) {
                                return true;
                            } else {
                                return super.dispatchTouchEvent(ev);
                            }
                        }

                    } else {
                        if (mMarginLayoutParams.topMargin >= mHideTopMargin) {
                            deltaY = (int) (y - mLastMotionY);
                            int currentMargin = mDownTopMargin + deltaY;
                            boolean intercept = true;
                            if (currentMargin >= mExpendTopMargin) {
                                currentMargin = mExpendTopMargin;

                                intercept = false;
                            }

                            if (mMarginLayoutParams.topMargin != currentMargin) {
                                mMarginLayoutParams.topMargin = currentMargin;
                                mLinearLayout.setLayoutParams(mMarginLayoutParams);
                            }
                            Log.e(TAG, "deltaY = " + deltaY + " intercept = " + intercept);
                            if (intercept) {
                                return true;
                            } else {
                                return super.dispatchTouchEvent(ev);
                            }

                        }
                    }
                    mLastMotionY = y;
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // 计算y轴速度
                int yVelocity = 0;
                if (mVelocityTracker != null) {
                    final VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);// SUPPRESS CHECKSTYLE
                    yVelocity = (int) velocityTracker.getYVelocity(mActivePointerId);
                }
                mActivePointerId = INVALID_POINTER;

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                if (isVertical) {
                    if (Math.abs(yVelocity) > mMinimumVelocity) {
                        smoothChangeMarginTopByVelocity(yVelocity);
                    }
                }


                break;
            case MotionEvent.ACTION_POINTER_UP:
                int index = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int pointerId = ev.getPointerId(index);
                if (pointerId == mActivePointerId) {
                    final int newPointerIndex = index == 0 ? 1 : 0;
                    mLastMotionY = ev.getY(newPointerIndex);
                    mLastMotionX = ev.getX(newPointerIndex);
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                    if (mVelocityTracker != null) {
                        mVelocityTracker.clear();
                    }
                }
                break;
            default:
                break;
        }
        boolean has = super.dispatchTouchEvent(ev);
        android.util.Log.d(TAG, "has = " + has);
        return has;
    }


    public boolean isTopMarginTop() {
        return mMarginLayoutParams.topMargin == mExpendTopMargin;
    }


    public interface ScrollListner {

        /**
         * 是否吸顶
         *
         * @return
         */
        boolean isCeiling();

        /**
         * 是否RecyclerView滑动到顶部
         *
         * @return
         */
        boolean isCanScroll();

    }

    private void smoothChangeMarginTopByVelocity(int velocity) {

        Log.d(TAG, "velocity = "+ velocity);

        if (velocity > 0) { //向下滑
            final int currentTopMargin = mMarginLayoutParams.topMargin;
            int desTopMargin = mExpendTopMargin;
            final int deltaMargin = desTopMargin - currentTopMargin;

            ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(300);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float fraction = animation.getAnimatedFraction();
                    mMarginLayoutParams.topMargin = (int) (currentTopMargin + (fraction * deltaMargin));
                    mLinearLayout.setLayoutParams(mMarginLayoutParams);
                }
            });

            animator.start();
        } else { //向上滑
            final int currentTopMargin = mMarginLayoutParams.topMargin;
            int desTopMargin = mHideTopMargin;
            final int deltaMargin = desTopMargin - currentTopMargin;

            ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(300);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float fraction = animation.getAnimatedFraction();
                    mMarginLayoutParams.topMargin = (int) (currentTopMargin + (fraction * deltaMargin));
                    mLinearLayout.setLayoutParams(mMarginLayoutParams);
                }
            });

            animator.start();
        }
    }
}
