package com.sirmamobile.saltlux.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rusib_000 on 23.7.2015 ?..
 */
public class CustomProgressBar extends View {
    public CustomProgressBar(Context context) {
        super(context);
        init(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        strokeHeight = 40;
        maxValue = 100;
        color = Color.BLACK;
    }

    private Paint paint;
    private int width;
    private int initWidth;
    private int maxValue;
    private int progressValue;
    private int strokeHeight;
    private int color;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(this.strokeHeight);
        this.paint.setColor(color);
        canvas.drawLine(0, 0, (int) (this.progressValue*(initWidth / (double)maxValue)), 0, this.paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        initWidth = width;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        invalidate();
    }

    public void setProgressValue(int progressValue) {
        if (progressValue < 0) {
            this.progressValue = 0;
        }

        if (progressValue > maxValue) {
            this.progressValue = maxValue;
        } else if (progressValue > 0 && progressValue <= maxValue) {
            this.progressValue = progressValue;
        }
        invalidate();
    }

    public void setStrokeHeight(int strokeHeight) {
        this.strokeHeight = strokeHeight;
        invalidate();
    }

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
        invalidate();
	}
    
    
}
