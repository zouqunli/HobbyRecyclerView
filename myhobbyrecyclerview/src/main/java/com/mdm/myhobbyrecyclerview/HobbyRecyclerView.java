package com.mdm.myhobbyrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author mdm
 * @Description  HobbyRecyclerView
 * @Version 1.0
 */
public class HobbyRecyclerView extends RecyclerView {

    private RectF mRangeRectf;
    private Paint mRangPaint;
    private int showMode = 1;
    //滚动条宽高
    private float scrollBarHeight;
    private float scrollBarWidth;
    //柱间隙
    private float scrollWidthSpace ;
    //滚动条宽度的等分比例
    private int scrollWidthWeight = 10;
    //Y轴的偏移值
    private float yScrollOffset = 0;
    //所有的子view的总高度 也就是这个
    private float childViewAllHeight;
    //可视区域的高度 其实这里就是View高度
    private float visualHeight;
    //可视区域的高度/所有的子view的总高度 得出的比例
    float range;
    //recyclerView的每个Item项的宽度
    private int childWidth;
    //判断触摸焦点
    private boolean isFocus = false;

    //手触摸时点的x,y坐标
    float x = 0;
    float y = 0;

    public HobbyRecyclerView(@NonNull Context context) {
        super(context);
    }
    public HobbyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public HobbyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs);
    }
    /**
     * 初始化
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        mRangeRectf = new RectF();
//        region = new Region();
        mRangPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRangPaint.setStyle(Paint.Style.FILL);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.HobbyRecyclerView);
        showMode = ta.getInteger(R.styleable.HobbyRecyclerView_scrollBarMode,1);
        ta.recycle();
        addOnScrollListener(onScrollListener);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if(showMode != 1){
            int width = MeasureSpec.getSize(widthSpec);
            scrollBarWidth = width / scrollWidthWeight;//取10分之一
            scrollWidthSpace = scrollBarWidth / 10; //获取间隙
            childWidth = (int) (width - scrollBarWidth);
            scrollBarWidth = scrollBarWidth - 2 * scrollWidthSpace;
            for (int i = 0; i < getChildCount(); i++) {
                measureChild(getChildAt(i),childWidth,heightSpec);
                getChildAt(i).getLayoutParams().width = childWidth;
            }
            setMeasuredDimension(width,heightSpec);
        }else {
            super.onMeasure(widthSpec, heightSpec);
        }

    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        visualHeight = getMeasuredHeight();
        childViewAllHeight = getChildAt(2).getHeight() * getAdapter().getItemCount();
        range = 0;
        if(childViewAllHeight != 0){
            range = visualHeight / childViewAllHeight;
        }
        scrollBarHeight = range * visualHeight;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawRange(canvas);
    }

    private void drawRange(Canvas canvas){
        if(canvas == null) return;
        mRangeRectf.set(childWidth + scrollWidthSpace,yScrollOffset,childWidth + scrollBarWidth ,yScrollOffset + scrollBarHeight);
        if(isFocus) {
            mRangPaint.setColor(Color.parseColor("#2386BF"));
        }else{
            mRangPaint.setColor(Color.parseColor("#2EB3FF"));
        }
        canvas.drawRect(mRangeRectf,mRangPaint);
        Log.i("tag" , "yScrollOffset ------- " + yScrollOffset);
        Log.i("tag" , "scrollBarHeight ------- " + scrollBarHeight);
        Log.i("tag" , "yScrollOffset ------- " + yScrollOffset);
    }


    private RecyclerView.OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            yScrollOffset += dy * range;
        }
    };
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //获取屏幕上点击的坐标
                x = event.getX();
                y = event.getY();
                if(x >= mRangeRectf.left
                        && x <= mRangeRectf.right
                        && y >= mRangeRectf.top
                        && y <= mRangeRectf.bottom){
                    isFocus = true;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(x >= mRangeRectf.left
                        && x <= mRangeRectf.right
                        && y >= mRangeRectf.top
                        && y <= mRangeRectf.bottom){
                    float diffValue = event.getY() - y;
                    scrollBy(0, (int) ((diffValue/visualHeight) * childViewAllHeight));
                    y = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                isFocus = false;
                invalidate();
                break;
        }
        if(x >= childWidth
                && x <= getMeasuredWidth()
                && y >= 0
                && y <= getMeasuredHeight()){
            return true;
        }else return super.onTouchEvent(event);
    }

    /**
     *  //当前RcyclerView显示区域的高度。水平列表屏幕从左侧到右侧显示范围
     int extent = this.computeHorizontalScrollExtent();

     //整体的高度，注意是整体，包括在显示区域之外的。
     int range = this.computeHorizontalScrollRange();

     //已经向下滚动的距离，为0时表示已处于顶部。
     int offset = this.computeHorizontalScrollOffset();
     */
}
