package com.mdm.myhobbyrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyRecyclerView extends RecyclerView {
    private static final String TAG = "RecyclerView";
    int mArcLength = 0;
    private GridLayoutManager mLayoutManager;

    private Paint mBgArcPaint;
    private Paint mArcPaint;
    private Paint mCirclePaint;
    private Paint mBgCirclePaint;

    private final int START_ANGLE = 320;
    private final int SWEEP_ANGLE = 80;
    private final int PADDING =12;
    private boolean mIndicatorVisible = true;  // 是否显示滚动条
    private int mVisibleItemCount = 0;
    private Handler mHandler = new Handler();
    private int mYscroll = 0;
    private RecyclerAdapter mAdapter;
    private int mItemHeight = 0;
    private float mStrokeWidth = 6;
    private int topBottomPadding = 30+40 ;


    public MyRecyclerView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }


    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    /**
     *定义画笔样式
     **/
    private void init() {
        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(true);
        mBgArcPaint.setColor(Color.GRAY);
        mBgArcPaint.setStrokeWidth(mStrokeWidth);
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.WHITE);
        mArcPaint.setStrokeWidth(mStrokeWidth);
        mArcPaint.setStyle(Paint.Style.STROKE);
//		setOnScrollListener(mOnScrollListener);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.WHITE);
        mBgCirclePaint = new Paint();
        mBgCirclePaint.setAntiAlias(true);
        mBgCirclePaint.setColor(Color.GRAY);
        setOnScrollListener(mOnScrollListener);
    }


    public void clear() {
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        invalidate();
    }

    @Override
    public void draw(Canvas arg0) {
        // TODO Auto-generated method stub
        super.draw(arg0);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);


        if (mIndicatorVisible) {
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.max(width, height)-PADDING;//diameter should < 200
            int count = getAdapter().getItemCount();
            int first = mLayoutManager.findFirstVisibleItemPosition();
            int last = mLayoutManager.findLastVisibleItemPosition();
            View v = mLayoutManager.getChildAt(first);
            if (v != null) {
                mItemHeight = v.getHeight();
            }
            if (mVisibleItemCount == 0) {
                mVisibleItemCount = last - first;
            }
            Log.e(TAG,"------mItemHeight="+mItemHeight+", width = "+width+",height="+height);
            int totalHeight = mItemHeight * (mAdapter.getItemCount()/mLayoutManager.getSpanCount()+mAdapter.getItemCount()%mLayoutManager.getSpanCount())+ topBottomPadding;
            float offsetAngle = 0;


            float sweepAngle = (height * SWEEP_ANGLE / totalHeight);
            if (totalHeight != 0) {
                offsetAngle = (SWEEP_ANGLE * ((float) mYscroll) / totalHeight);
            } else {
                offsetAngle = (SWEEP_ANGLE * ((float) first) / count);
            }
            RectF rect = new RectF((width - diameter) / 2,
                    (height - diameter) / 2, (width + diameter) / 2,
                    (height + diameter) / 2);
            canvas.drawCircle(
                    (float) (width / 2 + diameter/ 2* Math.cos((START_ANGLE) * Math.PI/ 180)),
                    (float) (height / 2 + diameter/ 2* Math.sin((START_ANGLE) * Math.PI/ 180)), mStrokeWidth / 2, mBgCirclePaint);
            canvas.drawArc(rect, START_ANGLE, SWEEP_ANGLE, false, mBgArcPaint);

            canvas.drawCircle((float) (width / 2 + diameter/ 2* Math.cos((START_ANGLE + SWEEP_ANGLE) * Math.PI/ 180)),
                    (float) (height / 2 + diameter/ 2* Math.sin((START_ANGLE + SWEEP_ANGLE) * Math.PI/ 180)),
                    mStrokeWidth / 2, mBgCirclePaint);
            RectF rect2 = new RectF((width - diameter) / 2,
                    (height - diameter) / 2, (width + diameter) / 2,
                    (height + diameter) / 2);
            Log.d(TAG, "-------sweepAngle=" + sweepAngle + ",offsetAngle="+ offsetAngle);
            canvas.drawCircle(
                    (float) (width / 2 + diameter
                            / 2
                            * Math.cos((START_ANGLE + offsetAngle) * Math.PI
                            / 180)),
                    (float) (height / 2 + diameter
                            / 2
                            * Math.sin((START_ANGLE + offsetAngle) * Math.PI
                            / 180)), mStrokeWidth / 2, mCirclePaint);
            canvas.drawArc(rect2, START_ANGLE + offsetAngle, sweepAngle, false,
                    mArcPaint);

            canvas.drawCircle(
                    (float) (width / 2 + diameter
                            / 2
                            * Math.cos((START_ANGLE + offsetAngle+sweepAngle) * Math.PI
                            / 180)),
                    (float) (height / 2 + diameter
                            / 2
                            * Math.sin((START_ANGLE + offsetAngle+sweepAngle) * Math.PI
                            / 180)), mStrokeWidth / 2, mCirclePaint);
        } else {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPaint(paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }


    }


    @Override
    public void setLayoutManager(LayoutManager layout) {
        // TODO Auto-generated method stub
        super.setLayoutManager(layout);
        if (mLayoutManager != layout) {

//			mLayoutManager = (LinearLayoutManager) layout;
            mLayoutManager = (GridLayoutManager) layout;
        }


    }
    @Override
    protected void onAttachedToWindow() {
        // TODO Auto-generated method stub
        super.onAttachedToWindow();
        if (getAdapter() != null) {
            Log.e(TAG, "-----" + getAdapter().getItemCount());


        }
    }


    Runnable mRun = new Runnable() {
        public void run() {
            mIndicatorVisible = false;
            invalidate();
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        int action = arg0.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            mIndicatorVisible = true;
        } else if (action == MotionEvent.ACTION_UP) {
            mHandler.removeCallbacks(mRun);
            mHandler.postDelayed(mRun, 3000);
        }
        return super.onTouchEvent(arg0);
    }
    @Override
    public void setAdapter(Adapter adapter) {
        // TODO Auto-generated method stub
        super.setAdapter(adapter);
        mAdapter = (RecyclerAdapter) adapter;
    }


    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            // TODO Auto-generated method stub
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            // TODO Auto-generated method stub
            super.onScrolled(recyclerView, dx, dy);
            mYscroll = mYscroll + dy;
        }


    };


}
