package com.vilyever.drawingview.brush.drawing;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
public class CircleBrushs extends ShapeBrush {
    final CircleBrushs self = this;
    Paint _paint;


    /* #Constructors */
    public CircleBrushs() {

    }

    public CircleBrushs(float size, int color) {
        this(size, color, FillType.Hollow);
    }

    public CircleBrushs(float size, int color, FillType fillType) {
        this(size, color, fillType, false);
    }

    public CircleBrushs(float size, int color, FillType fillType, boolean edgeRounded) {
        super(size, color, fillType, edgeRounded);
        init();
    }

    private void init() {
    }

    /* Public Methods */
    public static CircleBrushs defaultBrush() {
        return new CircleBrushs(Resource.getDimensionPixelSize(R.dimen.drawingViewBrushDefaultSize), Color.BLACK);
    }

    /* #Overrides */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Frame drawPath(Canvas canvas, @NonNull DrawingPath drawingPath, @NonNull DrawingState state) {
        updatePaint();
        Frame pathFrame = super.drawPath1(canvas, drawingPath, state,40);
        DrawingPoint beginPoint = drawingPath.getPoints().get(0);
        if (state.isFetchFrame() || canvas == null) {
            return pathFrame;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        RectF oval = new RectF(15,15,75,75);
        oval.offset(beginPoint.getX() - 25, beginPoint.getY() - 25);

        if (state.isCalibrateToOrigin()) {
            oval.offset(-pathFrame.left, -pathFrame.top);
        }
        canvas.drawOval(oval, paint);
        return pathFrame;
    }

}