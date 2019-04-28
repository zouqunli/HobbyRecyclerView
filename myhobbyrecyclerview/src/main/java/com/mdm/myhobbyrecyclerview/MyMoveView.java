package com.mdm.myhobbyrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyMoveView extends View{

    private PointF pointF;
    private Paint mPaint;
    private RectF rectF ;

    public MyMoveView(Context context) {
        super(context);
    }

    public MyMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *
     * @param context
     */
    private void init(Context context) {
        pointF = new PointF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
