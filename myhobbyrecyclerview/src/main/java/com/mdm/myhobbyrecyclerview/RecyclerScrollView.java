package com.mdm.myhobbyrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @date 创建时间 2019/4/29
 * @author qlzou
 * @Description  RecyclerView的滚动条
 * @Version 1.0
 */
public class RecyclerScrollView extends View {

    private Context mContext;
    private RectF mRectAngle;
    private Paint mPaint;
    private float mRvShowHeight;
    private float mRvAllHeight;
    private float mDistance;
    private float mScrollBarWidth;
    private float mScrollBarSpace;
    private float mScrollBarHeight;
    private int mPaintColor = PAINT_COLOR;
    private int mPaintColorFocus = PAINT_COLOR_FOCUS;
    private boolean isFocus = false;
    private OnScrollListener listener;
    private static int PAINT_COLOR = Color.parseColor("#2EB3FF");
    private static int PAINT_COLOR_FOCUS = Color.parseColor("#2386BF");
    float x ;
    float y ;

    public RecyclerScrollView(Context context) {
        this(context,null);
    }

    public RecyclerScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecyclerScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectAngle = new RectF();
    }

    /**
     * 设置矩形框的color
     * @param color 普通状态下颜色
     * @param focusColor 点击状态下颜色
     */
    public void setRectColor(int color,int focusColor){
        if(color == 0) {
            mPaintColor = PAINT_COLOR;
        }else{
            mPaintColor = color;
        }
        if(focusColor == 0) {
            mPaintColorFocus = PAINT_COLOR_FOCUS;
        }else{
            mPaintColorFocus = focusColor;
        }
        invalidate();
    }


    /**
     * 设置RecyclerView的可见高度和总视图高度
     * @param rvShowHeight
     * @param rvAllHeight
     */
    public void setRecyclerHeight(float rvShowHeight,float rvAllHeight){
        this.mRvShowHeight = rvShowHeight;
        this.mRvAllHeight = rvAllHeight;
        mDistance = 0;
        invalidate();
    }

    /**
     * 滑动距离
     */
    public void setScrollDistance(float distance){
        this.mDistance += (distance * mRvShowHeight/mRvAllHeight);
//        this.mDistance += distance;
        invalidate();
    }

    /**
     * 设置监听事件
     * @param listener
     */
    public void setListener(OnScrollListener listener){
        this.listener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mRvShowHeight == 0 && mRvAllHeight == 0) return;
        int width = getMeasuredWidth();
        mScrollBarSpace = width / 6;
        mScrollBarWidth = mScrollBarSpace * 4;
        mScrollBarHeight = (mRvShowHeight / mRvAllHeight) * mRvShowHeight;

        mRectAngle.set(mScrollBarSpace,mDistance,mScrollBarSpace + mScrollBarWidth,mScrollBarHeight + mDistance);
        if(isFocus){
            mPaint.setColor(mPaintColorFocus);
        }else{
            mPaint.setColor(mPaintColor);
        }
        canvas.drawRect(mRectAngle,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                if(x >= mRectAngle.left
                        && x <= mRectAngle.right
                        && y >= mRectAngle.top
                        && y <= mRectAngle.bottom) {
                    isFocus = true;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(mRectAngle.top < 0){
                    mRectAngle.top = 0;
                    mDistance = 0;
                }
                if(mRectAngle.bottom > mRvShowHeight){
                    mRectAngle.bottom = mRvShowHeight;
                    mDistance = mRvShowHeight - mScrollBarHeight;
                }
                if(0 <= mRectAngle.top
                        && mRvShowHeight >= mRectAngle.bottom) {
                    isFocus = true;
//                    mDistance += event.getY() - y;
                    if(listener != null){
                        listener.getScrollDistance(((Math.round(event.getY() - y)/mRvShowHeight) * mRvAllHeight));
                        Log.i("tag","y1 - y--:" + Math.round(event.getY() - y));
                    }
                    y = event.getY();
//                    invalidate();
                }else{
                    isFocus = false;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isFocus = false;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 监听回调
     */
    public interface OnScrollListener{
        void getScrollDistance(float distance);
    }

    /**
     * 获取可见的高度
     * @return
     */
    public float getRvShowHeight() {
        return mRvShowHeight;
    }

    /**
     * 获取总的高度
     * @return
     */
    public float getRvAllHeight() {
        return mRvAllHeight;
    }
}
