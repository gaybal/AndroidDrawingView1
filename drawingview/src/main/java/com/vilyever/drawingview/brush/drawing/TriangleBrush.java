package com.vilyever.drawingview.brush.drawing;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
public class TriangleBrush extends ShapeBrush {
    final TriangleBrush self = this;
    Paint _paint;


    /* #Constructors */
    public TriangleBrush() {

    }

    public TriangleBrush(float size, int color) {
        this(size, color, FillType.Hollow);
    }

    public TriangleBrush(float size, int color, FillType fillType) {
        this(size, color, fillType, false);
    }

    public TriangleBrush(float size, int color, FillType fillType, boolean edgeRounded) {
        super(size, color, fillType, edgeRounded);
        init();
    }

    private void init() {
    }

    /* Public Methods */
    public static TriangleBrush defaultBrush() {
        return new TriangleBrush(Resource.getDimensionPixelSize(R.dimen.drawingViewBrushDefaultSize), Color.BLACK);
    }

    /* #Overrides */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Frame drawPath(Canvas canvas, @NonNull DrawingPath drawingPath, @NonNull DrawingState state) {
        updatePaint();
        Frame pathFrame = super.drawPath(canvas, drawingPath, state);
        DrawingPoint beginPoint = drawingPath.getPoints().get(0);
        if (state.isFetchFrame() || canvas == null) {
            return pathFrame;
        }

        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);

        Path path=new Path();
        path.moveTo(beginPoint.getX(),beginPoint.getY());
        path.lineTo(beginPoint.getX()+20,beginPoint.getY()-34);
        path.lineTo(beginPoint.getX()+40,beginPoint.getY());
        path.close();
        canvas.drawPath(path, paint);
        return pathFrame;
    }

}