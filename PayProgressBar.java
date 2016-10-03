package com.ucmap.sesame_credit_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: Justson
 * 时间:2016/9/18 19:26.
 * 邮箱: cenxiaozhong.qqcom@qq.com
 * 公司: YGS
 */
public class PayProgressBar extends View {

    private int DEFWIDTH;
    private int DEFHEIGHT;
    private Paint paint;
    private int colorGray;
    private int colorWhite;
    private int density;

    public PayProgressBar(Context context) {
        super(context);
    }

    public PayProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);

        colorGray = Color.parseColor("#888888");
        colorWhite = Color.parseColor("#eeeeee");


        TypedArray ta = context.getResources().obtainAttributes(attrs, R.styleable.PayProgressBar);
        int count = ta.getIndexCount();
        for (int i = 0; i < count; i++) {

            int index = ta.getIndex(i);
            switch (index) {

                case R.styleable.PayProgressBar_mainColor:
                    colorWhite = ta.getColor(index, Color.WHITE);
                    break;
                case R.styleable.PayProgressBar_commonColor:
                    colorGray = ta.getColor(index, Color.GRAY);
                    break;

            }

        }
        ta.recycle();
        paint.setColor(colorGray);

        density = (int) context.getResources().getDisplayMetrics().density;

        margin = smallRaduis *= density;
        DEFHEIGHT = DEFWIDTH = 120 * density;
        paint.setAlpha(60);
    }

    private int smallRaduis = 5;
    private int margin;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        this.setMeasuredDimension(widthMode != View.MeasureSpec.EXACTLY ? DEFWIDTH : View.MeasureSpec.getSize(widthMeasureSpec),
                heightMode != View.MeasureSpec.EXACTLY ? DEFHEIGHT : View.MeasureSpec.getSize(heightMeasureSpec));
    }

    private int index;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = this.getWidth();
        int height = this.getHeight();

        int cx = width / 2;
        int cy = height / 2;

        int startX = cx - smallRaduis * 2 - margin;
        for (int i = 0; i < 3; i++) {
            paint.setColor(colorGray);
            if (index % 3 == i) {
                paint.setColor(colorWhite);
            }
            canvas.drawCircle(startX + (smallRaduis * 2 * i + (i * margin)), cy, smallRaduis, paint);
        }


        if (index >= Integer.MAX_VALUE) {
            index = Integer.MAX_VALUE % index;
        }
        index++;
        postInvalidateDelayed(300);
    }
}
