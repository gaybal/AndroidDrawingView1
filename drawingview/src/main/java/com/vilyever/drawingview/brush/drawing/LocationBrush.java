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
 * 点位绘制
 */
public class LocationBrush extends ShapeBrush {
    final LocationBrush self = this;
    Paint _paint;
    private int num;


    /* #Constructors */
    public LocationBrush() {

    }

    public LocationBrush(float size, int color) {
        this(size, color, FillType.Hollow);
    }

    public LocationBrush(float size, int color, FillType fillType) {
        this(size, color, fillType, false);
    }

    public LocationBrush(float size, int color, FillType fillType, boolean edgeRounded) {
        super(size, color, fillType, edgeRounded);
        init();
    }

    private void init() {
    }
    /* Public Methods */
    public static LocationBrush defaultBrush() {
        return new LocationBrush(Resource.getDimensionPixelSize(R.dimen.drawingViewBrushArrowSize), Color.BLACK);
    }
    /* #Overrides */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Frame drawPath(Canvas canvas, @NonNull DrawingPath drawingPath, @NonNull DrawingState state) {
        updatePaint();
        Frame pathFrame = super.drawPath1(canvas, drawingPath, state,50);
        DrawingPoint beginPoint = drawingPath.getPoints().get(0);
        if (state.isFetchFrame() || canvas == null) {
            return pathFrame;
        }
        Path path = new Path();
        path.moveTo(beginPoint.getX(), beginPoint.getY());

        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(34);
        paint.setAntiAlias(true);

        float textWidth = paint.measureText(num+"#");
        RectF rectF = new RectF(beginPoint.getX(),beginPoint.getY(),beginPoint.getX()+textWidth+1,beginPoint.getY());
        path.addRect(rectF, Path.Direction.CCW);
        if (state.isCalibrateToOrigin()) {
            path.offset(-pathFrame.left, -pathFrame.top+50);
        }

//        canvas.drawPath(path,paint);
        canvas.drawTextOnPath(num+"#",path, 0f, 0f, paint);
        return pathFrame;
    }
    public void setNum(int num){
        this.num = num;
    }
}