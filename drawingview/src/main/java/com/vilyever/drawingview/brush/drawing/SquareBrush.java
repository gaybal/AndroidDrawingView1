package com.vilyever.drawingview.brush.drawing;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;

import com.vilyever.drawingview.R;
import com.vilyever.drawingview.model.DrawingPath;
import com.vilyever.drawingview.model.DrawingPoint;
import com.vilyever.resource.Resource;

/**
 * LineBrush
 * AndroidDrawingView <com.vilyever.drawingview.brush>
 * Created by vilyever on 2015/10/21.
 * Feature:
 * 直线绘制
 */
public class SquareBrush extends ShapeBrush {
    final SquareBrush self = this;
    Paint _paint;


    /* #Constructors */
    public SquareBrush() {

    }

    public SquareBrush(float size, int color) {
        this(size, color, FillType.Hollow);
    }

    public SquareBrush(float size, int color, FillType fillType) {
        this(size, color, fillType, false);
    }

    public SquareBrush(float size, int color, FillType fillType, boolean edgeRounded) {
        super(size, color, fillType, edgeRounded);
        init();
    }

    private void init() {
    }

    /* Public Methods */
    public static SquareBrush defaultBrush() {
        return new SquareBrush(Resource.getDimensionPixelSize(R.dimen.drawingViewBrushDefaultSize), Color.BLACK);
    }

    /* #Overrides */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Frame drawPath(Canvas canvas, @NonNull DrawingPath drawingPath, @NonNull DrawingState state) {
        updatePaint();
        Frame pathFrame = super.drawPath1(canvas, drawingPath, state);
        DrawingPoint beginPoint = drawingPath.getPoints().get(0);
        if (state.isFetchFrame() || canvas == null) {
            return pathFrame;
        }

        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(34);
        Paint paint1 = new Paint();
        paint1.setARGB(255,255,255,255);
        RectF rectF = new RectF(15,15,55,55);
//        RectF rectF1 = new RectF(21,21,49,49);
        rectF.offset(beginPoint.getX()-30,beginPoint.getY()-30);
//        rectF1.offset(beginPoint.getX()-30,beginPoint.getY()-30);
        canvas.drawRect(rectF, paint);
//        canvas.drawRect(rectF1, paint1);
        return pathFrame;
    }

}