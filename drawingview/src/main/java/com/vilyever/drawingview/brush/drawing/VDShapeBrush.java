package com.vilyever.drawingview.brush.drawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.vilyever.drawingview.model.VDDrawingPath;
import com.vilyever.drawingview.model.VDDrawingPoint;

/**
 * VDShapeBrush
 * AndroidDrawingView <com.vilyever.drawingview.brush>
 * Created by vilyever on 2015/10/21.
 * Feature:
 * 形状绘制brush
 *
 * Known Direct Subclasses:
 * {@link VDLineBrush}
 * {@link VDRectangleBrush}
 * {@link VDRoundedRectangleBrush}
 * {@link VDEllipseBrush}
 * {@link VDCircleBrush}
 * {@link VDCenterCircleBrush}
 * {@link VDRightAngledTriangleBrush}
 * {@link VDIsoscelesTriangleBrush}
 * {@link VDRhombusBrush}
 * {@link VDPolygonBrush}
 */
public abstract class VDShapeBrush extends VDDrawingBrush {
    final VDShapeBrush self = this;

    /* #Constructors */
    public VDShapeBrush() {

    }

    public VDShapeBrush(float size, int color) {
        this(size, color, FillType.Hollow);
    }

    public VDShapeBrush(float size, int color, FillType fillType) {
        this(size, color, fillType, false);
    }

    public VDShapeBrush(float size, int color, FillType fillType, boolean edgeRounded) {
        super(size, color);
        this.fillType = fillType;
        this.edgeRounded = edgeRounded;
    }

    /* Properties */
    /**
     * 填充样式
     * 镂空，铺满
     */
    public enum FillType {
        Hollow, Solid;
    }
    protected FillType fillType;
    public FillType getFillType() {
        if (self.isEraser()) {
            return FillType.Solid;
        }

        if (self.fillType == null) {
            return FillType.Hollow;
        }
        return fillType;
    }
    public <T extends VDShapeBrush> T setFillType(FillType fillType) {
        this.fillType = fillType;
        self.updatePaint();
        return (T) self;
    }

    /**
     * 边缘交点是否圆角
     */
    protected boolean edgeRounded;
    public boolean isEdgeRounded() {
        return edgeRounded;
    }
    public <T extends VDShapeBrush> T setEdgeRounded(boolean edgeRounded) {
        this.edgeRounded = edgeRounded;
        self.updatePaint();
        return (T) self;
    }

    /* #Overrides */
    @Override
    protected void updatePaint() {
        super.updatePaint();

        switch (self.getFillType()) {
            case Hollow:
                self.getPaint().setStyle(Paint.Style.STROKE);
                break;
            case Solid:
                self.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                break;
        }

        if (self.isEdgeRounded()) {
            self.getPaint().setStrokeCap(Paint.Cap.ROUND);
            self.getPaint().setStrokeJoin(Paint.Join.ROUND);
        }
        else {
            self.getPaint().setStrokeCap(Paint.Cap.SQUARE);
            self.getPaint().setStrokeJoin(Paint.Join.MITER);
        }
    }

    @NonNull
    @Override
    public Frame drawPath(Canvas canvas, @NonNull VDDrawingPath drawingPath, @NonNull DrawingState state) {
        if (drawingPath.getPoints().size() < 2) {
            return Frame.EmptyFrame();
        }
        else {
            VDDrawingPoint beginPoint = drawingPath.getPoints().get(0);
            VDDrawingPoint lastPoint = drawingPath.getPoints().get(drawingPath.getPoints().size() - 1);

            RectF drawingRect = new RectF();

            drawingRect.left = Math.min(beginPoint.getX(), lastPoint.getX());
            drawingRect.top = Math.min(beginPoint.getY(), lastPoint.getY());
            drawingRect.right = Math.max(beginPoint.getX(), lastPoint.getX());
            drawingRect.bottom = Math.max(beginPoint.getY(), lastPoint.getY());

            return self.makeFrameWithBrushSpace(drawingRect);
        }
    }

}