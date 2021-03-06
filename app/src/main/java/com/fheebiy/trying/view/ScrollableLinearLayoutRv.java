package com.fheebiy.trying.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.CommonUtil;
import com.jaeger.library.StatusBarUtil;

/**
 * 首页内容外层的linearlayout，主要处理banner和listview的联动
 * 
 */
public class ScrollableLinearLayoutRv extends LinearLayout {

    public static final String TAG = "Scrollable_zwb";

    /**
     * 最大滚动距离
     */
    private int mMaxScrollDistance;

    /**
     * 滚动的回掉
     */
    private OnScrollYChangeListener mOnScrollListener;

    /**
     * 滑动的临界值
     */
    private int mTouchSlop;

    /**
     * 当前监听的滑动手指
     */
    private int mActivePointerId;

    /**
     * 不合法的位置
     */
    private static final int INVALID_POINTER = -1;

    /**
     * 第一次触摸x的位置
     */
    private float mDownMotionX;

    /**
     * 第一次触摸y的位置
     */
    private float mDownMotionY;

    /**
     * 最后一次触摸x的位置
     */
    @SuppressWarnings("unused")
    private float mLastMotionX;

    /**
     * 最后一次触摸y的位置
     */
    private float mLastMotionY;

    /**
     * 是否垂直滑动
     */
    private boolean mIsVerticalScroll;

    /**
     * 是否处于banner展开的状态
     */
    private boolean mIsBeingExpand;

    /**
     * 手指是否向上滑
     */
    private boolean mIsUp;

    /**
     * 滑动速度监听
     */
    private VelocityTracker mVelocityTracker;

    /**
     * banner区域惯性滚动
     */
    private Scroller mHeadScroller;

    /**
     * 手指向下快速滑动
     */
    private Scroller mDownFlingScroller;

    /**
     * 手指向上快速滑动的时间
     */
    private long mUpFlingStartTime;

    /**
     * 向上快速滑动 banner的scrollY
     */
    private int mUpFlingScrollY;

    /**
     * 手指向下快速滑动的时间
     */
    private long mDownFlingStartTime;

    /**
     * 手指向上快速滑动的listview的惯性
     */
    private Scroller mUpFlingScroller;

    /**
     * 手指向上快速滑动的最终位置
     */
    private int mUpFinalY;

    /**
     * 手指向上快速滑动的总时间
     */
    private int mUpDuration;

    /**
     * 手指向下快速滑动的最终位置
     */
    private int mDownFinalY;

    /**
     * 手指向下快速滑动的时间
     */
    private int mDownDuration;

    /**
     * 最小滑动速度
     */
    private int mMinimumVelocity;

    /**
     * 最大滑动速度
     */
    private int mMaximumVelocity;


    public static final String SHRINK_TAG = "shrink";

    private View mShrinkView;

    private int searchBarHeight;

    /**
     * 构造方法
     *
     * @param context
     *            context
     */
    public ScrollableLinearLayoutRv(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造方法
     *
     * @param context
     *            context
     * @param attrs
     *            attrs
     */
    public ScrollableLinearLayoutRv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * 
     * @param context
     *            context
     */
    @SuppressLint("NewApi")
    private void init(Context context) {
        final ViewConfiguration configuration = ViewConfiguration.get(context);

        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

        mHeadScroller = new Scroller(context, new DecelerateInterpolator());

        mDownFlingScroller = new Scroller(context);

        mUpFlingScroller = new Scroller(context);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.setMotionEventSplittingEnabled(false);
        }

       /* mShrinkView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mShrinkView.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });*/

       searchBarHeight = CommonUtil.dip2px(getContext(), 46) + CommonUtil.getStatusBarHeight(getContext());


    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mShrinkView = findViewById(R.id.ll_shrink);
        mShrinkView.post(new Runnable() {
            @Override
            public void run() {
                int height = mShrinkView.getHeight();
                Log.d(TAG, "height = " + height + " ,mMaxScrollDistance = " + mMaxScrollDistance);
                mMaxScrollDistance = height - searchBarHeight;
                requestLayout();
            }
        });


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + mMaxScrollDistance, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 获取最大滚动距离
     *
     * @return int
     */
    public int getMaxScrollDistance() {

       /* if (mShrinkView.getHeight() != mMaxScrollDistance - searchBarHeight) {
            mMaxScrollDistance = mShrinkView.getHeight() - searchBarHeight;
            requestLayout();
        }*/

        if (mMaxScrollDistance != mShrinkView.getHeight() - searchBarHeight) {
            mMaxScrollDistance = mShrinkView.getHeight() - searchBarHeight;
            requestLayout();
        }
        return mMaxScrollDistance;
    }

    /**
     * 设置最大滚动距离
     * 
     * @param maxScrollDistance
     *            maxScrollDistance
     */
    public void setMaxScrollDistance(int maxScrollDistance) {
        //this.mMaxScrollDistance = maxScrollDistance;
    }

    /**
     * 获取滑动监听
     * 
     * @return OnScrollYChangeListener
     */
    public OnScrollYChangeListener getOnScrollListener() {
        return mOnScrollListener;
    }

    /**
     * 设置滑动监听
     * 
     * @param onScrollListener
     *            onScrollListener
     */
    public void setOnScrollListener(OnScrollYChangeListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public void computeScroll() {
        if (mOnScrollListener == null) {
            return;
        }

        boolean mNeedInvalidate = false;
        
        if (mUpFlingScroller.computeScrollOffset()) {
            if (!mOnScrollListener.canScrollUp(1)) {
                mUpDuration -= (System.currentTimeMillis() - mUpFlingStartTime);
                if (mUpDuration < 0) {
                    mUpDuration = 0;
                }
                mUpFinalY -= mUpFlingScroller.getCurrY();
                if (mUpFinalY < 0) {
                    mUpFinalY = 0;
                }
                mUpFlingScroller.forceFinished(true);
                RecyclerView lv = mOnScrollListener.getRecyclerView();
                if (lv != null) {
                    lv.smoothScrollBy(mUpFinalY, mUpDuration);
                }
            } else {
                int currentY = mUpFlingScroller.getCurrY() + mUpFlingScrollY;
                if (currentY > mMaxScrollDistance) {
                    currentY = mMaxScrollDistance;
                }
                scrollTo(0, currentY);
                mOnScrollListener.onScrollChanged(currentY);
            }
            mNeedInvalidate = true;
        }
        
        if (mHeadScroller.computeScrollOffset()) {
            int currentY = mHeadScroller.getCurrY();
            scrollTo(0, currentY);
            mOnScrollListener.onScrollChanged(currentY);
            mNeedInvalidate = true;
        } 

        if (mDownFlingScroller.computeScrollOffset()) {
            if (mOnScrollListener.canScrollDown()) {
                mDownDuration -= (System.currentTimeMillis() - mDownFlingStartTime);
                if (mDownDuration < 0) {
                    mDownDuration = 0;
                }
                mDownFinalY -= mDownFlingScroller.getCurrY();

                if (mDownFinalY < 0) {
                    mDownFinalY = 0;
                }
                int currentY = getScrollY();
                mDownFlingScroller.forceFinished(true);
                if (mDownFinalY < currentY) {
                    mHeadScroller.startScroll(0, currentY, 0, -mDownFinalY, mDownDuration);
                } else if (mDownFinalY != 0) {
                    mDownDuration = currentY * mDownDuration / mDownFinalY;
                    mHeadScroller.startScroll(0, currentY, 0, -currentY, mDownDuration);
                }

            }
            mNeedInvalidate = true;
        }

        if (mNeedInvalidate) {
            postInvalidate();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mOnScrollListener == null) {
            return super.dispatchTouchEvent(ev);
        }

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);
        final int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {

        case MotionEvent.ACTION_DOWN:
            float y = ev.getY();
            float x = ev.getX();
            mLastMotionY = y;
            mLastMotionX = x;
            mDownMotionX = x;
            mDownMotionY = y;
            mActivePointerId = ev.getPointerId(0);
            mHeadScroller.forceFinished(true);
            mDownFlingScroller.forceFinished(true);
            mUpFlingScroller.forceFinished(true);
            mUpFinalY = 0;
            mUpDuration = 0;
            mUpFlingScrollY = 0;
            mUpFlingStartTime = 0;
            mDownFinalY = 0;
            mDownDuration = 0;
            mDownFlingStartTime = 0;
            break;
        case MotionEvent.ACTION_MOVE:
            final int activePointerId = mActivePointerId;
            if (activePointerId == INVALID_POINTER) {
                break;
            }

            final int pointerIndex = ev.findPointerIndex(activePointerId);
            y = ev.getY(pointerIndex);
            x = ev.getX(pointerIndex);
            final int yDiff = (int) Math.abs(y - mDownMotionY);
            final int xDiff = (int) Math.abs(x - mDownMotionX);
            int deltaY = (int) (y - mLastMotionY);
          
            if (!mIsVerticalScroll && yDiff > mTouchSlop && xDiff < yDiff) {
                mIsVerticalScroll = true;
                ViewPager vp = mOnScrollListener.getViewPager();
                if (vp != null) {
                    vp.requestDisallowInterceptTouchEvent(true);
                }
            }
            mIsUp = deltaY < 0;
            Log.d(TAG, "mIsUp = " + mIsUp + " ,mIsVerticalScroll = " + mIsVerticalScroll + " ,mIsBeingExpand = " + mIsBeingExpand);
            if (mIsVerticalScroll) {
                if (!mIsBeingExpand) {
                    if ((mIsUp && mOnScrollListener.canScrollUp(2)) || (!mIsUp && mOnScrollListener.canScrollDown())) {
                        mIsBeingExpand = true;
                        if (!mIsUp && mOnScrollListener.canScrollDown()) {
                            mLastMotionY = y;
                        }
                    }
                } else {
                    if ((mIsUp && !mOnScrollListener.canScrollUp(3)) || (!mIsUp && !mOnScrollListener.canScrollDown())) {
                        mIsBeingExpand = false;
                    }
                }
                if (mIsBeingExpand) {
                    deltaY = (int) (y - mLastMotionY);
                    mLastMotionY = y;
                    final int oldY = getScrollY();
                    int desY = oldY - deltaY;
                    if (desY > mMaxScrollDistance) {
                        desY = mMaxScrollDistance;
                    } else if (desY < 0) {
                        desY = 0;
                    }
                    scrollTo(0, desY);
                    invalidate();
                    mOnScrollListener.onScrollChanged(desY);
                }

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

            if (mIsVerticalScroll) {
                if (Math.abs(yVelocity) > mMinimumVelocity) {
                    int velocity = -yVelocity;
                    int dis = mMaxScrollDistance - getScrollY();
                    if (velocity > 0 && dis > 0) {
                        mDownFlingScroller.forceFinished(true);
                        mHeadScroller.forceFinished(true);
                        mUpFlingScroller.fling(0, 0, 0, velocity, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
                        mUpDuration = mUpFlingScroller.getDuration();
                        mUpFinalY = mUpFlingScroller.getFinalY();
                        mUpFlingStartTime = System.currentTimeMillis();
                        mUpFlingScrollY = getScrollY();
                        invalidate();
                        mIsVerticalScroll = false;
                        mIsBeingExpand = false;
                        int temp = ev.getAction();
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                        boolean res = super.dispatchTouchEvent(ev);
                        ev.setAction(temp);
                        return res;
                    } else if (velocity < 0) {
                        mUpFlingScroller.forceFinished(true);
                        mDownFlingScroller.fling(0, 0, 0, -velocity, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
                        mDownDuration = mDownFlingScroller.getDuration();
                        mDownFinalY = mDownFlingScroller.getFinalY();
                        mDownFlingStartTime = System.currentTimeMillis();
                        invalidate();
                        mIsVerticalScroll = false;
                        if (mIsBeingExpand) {
                            mIsBeingExpand = false;
                            int temp = ev.getAction();
                            ev.setAction(MotionEvent.ACTION_CANCEL);
                            boolean res = super.dispatchTouchEvent(ev);
                            ev.setAction(temp);
                            return res;
                        }
                    } else {
                        if (mIsVerticalScroll) {
                            mIsVerticalScroll = false;
                            if (mIsBeingExpand) {
                                mIsBeingExpand = false;
                                int temp = ev.getAction();
                                ev.setAction(MotionEvent.ACTION_CANCEL);
                                boolean res = super.dispatchTouchEvent(ev);
                                ev.setAction(temp);
                                return res;
                            }
                        }
                    }
                } else {
                    mIsVerticalScroll = false;
                    if (mIsBeingExpand) {
                        mIsBeingExpand = false;
                        int temp = ev.getAction();
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                        boolean res = super.dispatchTouchEvent(ev);
                        ev.setAction(temp);
                        return res;
                    }

                }
            }
            mIsVerticalScroll = false;
            mIsBeingExpand = false;
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
        Log.d(TAG, "has = " + has);
        return has;
    }

    public void smoothBackToUp() {
        final int startY = getScrollY();
        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction =  animation.getAnimatedFraction();
                int y = (int) (startY * (1 - fraction));
                scrollTo(0, y);
                mOnScrollListener.onScrollChanged(y);
            }
        });

        animator.start();
    }

    /**
     * 滑动监听
     */
    public interface OnScrollYChangeListener {

        /**
         * 是否能向上滑
         * 
         * @return boolean
         */
        boolean canScrollUp(int from);

        /**
         * 是否能向下滑
         * 
         * @return boolean
         */
        boolean canScrollDown();

        /**
         * 获取RecyclerView
         * 
         * @return RecyclerView
         */
        RecyclerView getRecyclerView();
        
        /**
         * 获取viewpager
         * @return viewpager
         */
        ViewPager getViewPager();

        /**
         * 滑动监听
         * 
         * @param scrollY
         *            当前的scrollY
         */
        void onScrollChanged(int scrollY);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
