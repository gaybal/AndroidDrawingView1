package com.vilyever.androiddrawingview;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vilyever.drawingview.DrawingView;
import com.vilyever.drawingview.brush.drawing.ArrowBrush;
import com.vilyever.drawingview.brush.drawing.CenterCircleBrush;
import com.vilyever.drawingview.brush.drawing.CircleBrush;
import com.vilyever.drawingview.brush.drawing.DrawingBrush;
import com.vilyever.drawingview.brush.drawing.LocationBrush;
import com.vilyever.drawingview.brush.drawing.PenBrush;
import com.vilyever.drawingview.brush.drawing.EllipseBrush;
import com.vilyever.drawingview.brush.drawing.PolygonBrush;
import com.vilyever.drawingview.brush.drawing.RectangleBrush;
import com.vilyever.drawingview.brush.drawing.IsoscelesTriangleBrush;
import com.vilyever.drawingview.brush.drawing.LineBrush;
import com.vilyever.drawingview.brush.drawing.RhombusBrush;
import com.vilyever.drawingview.brush.drawing.RightAngledTriangleBrush;
import com.vilyever.drawingview.brush.drawing.RoundedRectangleBrush;
import com.vilyever.drawingview.brush.drawing.ShapeBrush;
import com.vilyever.drawingview.brush.drawing.SquareBrush;
import com.vilyever.drawingview.brush.drawing.TriangleBrush;
import com.vilyever.drawingview.brush.drawing.TriangleStrokeBrush;
import com.vilyever.drawingview.brush.text.TextBrush;
import com.vilyever.drawingview.model.DrawingStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingFragment1 extends Fragment implements View.OnClickListener {
    private final DrawingFragment1 self = this;

    private DrawingView drawingView;

    private Button undoButton;
    private Button redoButton;
    private Button clearButton;

    private Button penButton, rectButton, lineButton, dxButton, ydButton, ltButton, locationButton, arrowButton, isoTriangleButton, rightTriangleButton, circleButton;
    //    private Button shapeButton;
    private Button textButton;
    private Button backgroundColorButton;

    private Button thicknessButton;
    private Button eraserButton;
    private Button colorButton;
    private Button fillTypeButton;
    private Button edgeRoundedButton;
    private Button oneStrokeOneLayerButton;
    private Button deleteLayerButton;

    private ThicknessAdjustController thicknessAdjustController;

    private List<Button> singleSelectionButtons;

    private List<ShapeBrush> shapeBrushes = new ArrayList<>();
    private PenBrush penBrush;
    private TextBrush textBrush;
    LineBrush lineBrush;
    RectangleBrush rectangleBrush;
    RoundedRectangleBrush roundedRectangleBrush;
    CircleBrush circleBrush;
    RightAngledTriangleBrush rightAngledTriangleBrush;
    IsoscelesTriangleBrush isoscelesTriangleBrush;
    RectangleBrush rhombusBrush;
    CenterCircleBrush centerCircleBrush;
    ArrowBrush arrowBrush;
    LocationBrush locationBrush;
    SquareBrush squareBrush;
    TriangleStrokeBrush triangleStrokeBrush;
    TriangleBrush triangleBrush;
    public DrawingFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.drawing_fragment1, container, false);
        self.drawingView = (DrawingView) rootView.findViewById(R.id.drawingView);
        self.undoButton = (Button) rootView.findViewById(R.id.undoButton);
        self.undoButton.setEnabled(false);
        self.undoButton.setOnClickListener(this);
        self.redoButton = (Button) rootView.findViewById(R.id.redoButton);
        self.redoButton.setEnabled(false);
        self.redoButton.setOnClickListener(this);
        self.clearButton = (Button) rootView.findViewById(R.id.clearButton);
        self.clearButton.setOnClickListener(this);
        self.penButton = (Button) rootView.findViewById(R.id.penButton);
        self.penButton.setSelected(true);
        self.penButton.setOnClickListener(this);
        self.textButton = (Button) rootView.findViewById(R.id.textButton);
        self.textButton.setOnClickListener(this);
        self.eraserButton = (Button) rootView.findViewById(R.id.eraserButton);
        self.eraserButton.setOnClickListener(this);
        self.oneStrokeOneLayerButton = (Button) rootView.findViewById(R.id.oneStrokeOneLayerButton);
        self.oneStrokeOneLayerButton.setOnClickListener(this);
        self.deleteLayerButton = (Button) rootView.findViewById(R.id.deleteLayerButton);
        self.deleteLayerButton.setOnClickListener(this);

        self.ydButton = (Button) rootView.findViewById(R.id.ydButton);
        self.ydButton.setOnClickListener(this);
        self.ltButton = (Button) rootView.findViewById(R.id.ltButton);
        self.ltButton.setOnClickListener(this);
        self.dxButton = (Button) rootView.findViewById(R.id.dxButton);
        self.dxButton.setOnClickListener(this);
        self.lineButton = (Button) rootView.findViewById(R.id.lineButton);
        self.lineButton.setOnClickListener(this);
        self.rectButton = (Button) rootView.findViewById(R.id.rectButton);
        self.rectButton.setOnClickListener(this);
        self.rightTriangleButton = (Button) rootView.findViewById(R.id.rightTriangleButton);
        self.rightTriangleButton.setOnClickListener(this);
        self.isoTriangleButton = (Button) rootView.findViewById(R.id.isoTriangleButton);
        self.isoTriangleButton.setOnClickListener(this);
        self.arrowButton = (Button) rootView.findViewById(R.id.arrowButton);
        self.arrowButton.setOnClickListener(this);
        self.locationButton = (Button) rootView.findViewById(R.id.locationButton);
        self.locationButton.setOnClickListener(this);
        self.circleButton = (Button) rootView.findViewById(R.id.circleButton);
        self.circleButton.setOnClickListener(this);
        initData();
        return rootView;
    }

    private void initData() {
        self.textBrush = TextBrush.defaultBrush().setTypefaceStyle(Typeface.ITALIC);
        lineBrush = LineBrush.defaultBrush();
        self.shapeBrushes.add(lineBrush);
        rectangleBrush = RectangleBrush.defaultBrush();
        self.shapeBrushes.add(rectangleBrush);
        roundedRectangleBrush = RoundedRectangleBrush.defaultBrush();
        self.shapeBrushes.add(roundedRectangleBrush);
        circleBrush = CircleBrush.defaultBrush();
        self.shapeBrushes.add(circleBrush);
        rightAngledTriangleBrush = RightAngledTriangleBrush.defaultBrush();
        self.shapeBrushes.add(rightAngledTriangleBrush);
        isoscelesTriangleBrush = IsoscelesTriangleBrush.defaultBrush();
        self.shapeBrushes.add(isoscelesTriangleBrush);
        rhombusBrush = RhombusBrush.defaultBrush();
        self.shapeBrushes.add(rhombusBrush);
        centerCircleBrush = CenterCircleBrush.defaultBrush();
        self.shapeBrushes.add(centerCircleBrush);
        arrowBrush = ArrowBrush.defaultBrush();
        self.shapeBrushes.add(arrowBrush);
        locationBrush = LocationBrush.defaultBrush();
        self.shapeBrushes.add(locationBrush);
        squareBrush = SquareBrush.defaultBrush();
        self.shapeBrushes.add(squareBrush);
        triangleStrokeBrush = TriangleStrokeBrush.defaultBrush();
        self.shapeBrushes.add(triangleStrokeBrush);
        triangleBrush = TriangleBrush.defaultBrush();
        self.shapeBrushes.add(triangleBrush);
        self.penBrush = PenBrush.defaultBrush();
        self.drawingView.setBrush(self.penBrush);
        self.singleSelectionButtons = new ArrayList<>();
        self.singleSelectionButtons.add(self.penButton);
        self.singleSelectionButtons.add(self.textButton);
        self.singleSelectionButtons.add(self.ydButton);
        self.singleSelectionButtons.add(self.ltButton);
        self.singleSelectionButtons.add(self.dxButton);
        self.singleSelectionButtons.add(self.lineButton);
        self.singleSelectionButtons.add(self.rectButton);
        self.singleSelectionButtons.add(self.isoTriangleButton);
        self.singleSelectionButtons.add(self.rightTriangleButton);
        self.singleSelectionButtons.add(self.arrowButton);
        self.singleSelectionButtons.add(self.locationButton);
        self.singleSelectionButtons.add(self.circleButton);
        self.singleSelectionButtons.add(self.eraserButton);
//        self.singleSelectionButtons.add(self.textButton);
        self.drawingView.setUndoRedoStateDelegate(new DrawingView.UndoRedoStateDelegate() {
            @Override
            public void onUndoRedoStateChange(DrawingView drawingView, boolean canUndo, boolean canRedo) {
                self.undoButton.setEnabled(canUndo);
                self.redoButton.setEnabled(canRedo);
            }
        });
        self.drawingView.setInterceptTouchDelegate(new DrawingView.InterceptTouchDelegate() {
            @Override
            public void requireInterceptTouchEvent(DrawingView drawingView, boolean isIntercept) {

            }
        });

        self.drawingView.setDrawingStepDelegate(new DrawingView.DrawingStepDelegate() {
            @Override
            public void onDrawingStepBegin(DrawingView drawingView, DrawingStep step) {
            }

            @Override
            public void onDrawingStepChange(DrawingView drawingView, DrawingStep step) {

            }

            @Override
            public void onDrawingStepEnd(DrawingView drawingView, DrawingStep step) {
            }

            @Override
            public void onDrawingStepCancel(DrawingView drawingView, DrawingStep step) {
            }
        });

        self.drawingView.setBackgroundDatasource(new DrawingView.BackgroundDatasource() {
            @Override
            public Drawable gainBackground(DrawingView drawingView, String identifier) {
                return null;
            }
        });
//        self.getThicknessAdjustController().setThickness((int) self.penBrush.getSize());
//        self.getThicknessAdjustController().popupFromView(v, BasePopupController.PopupDirection.Left, true, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.undoButton:
                self.drawingView.undo();
                break;
            case R.id.redoButton:
                self.drawingView.redo();
                break;
            case R.id.clearButton:
                self.drawingView.clear();
                break;
            case R.id.penButton:
                self.selectButton(self.singleSelectionButtons, self.penButton);
                self.drawingView.setBrush(self.penBrush);
                break;
            case R.id.textButton:
                self.selectButton(self.singleSelectionButtons, self.textButton);
                self.drawingView.setBrush(self.textBrush);
                break;
            case R.id.eraserButton:
                self.selectButton(self.singleSelectionButtons, self.eraserButton);
                self.penBrush.setIsEraser(true);
                self.penBrush.setSize(15);
                self.drawingView.setBrush(self.penBrush);
                break;
            case R.id.deleteLayerButton:
                self.drawingView.deleteHandlingLayer();
                break;
            case R.id.oneStrokeOneLayerButton:
                v.setSelected(!v.isSelected());
                self.penBrush.setOneStrokeToLayer(v.isSelected());
                self.textBrush.setOneStrokeToLayer(v.isSelected());
                for (DrawingBrush brush : self.shapeBrushes) {
                    brush.setOneStrokeToLayer(v.isSelected());
                }
                break;
            case R.id.ydButton:
                self.selectButton(self.singleSelectionButtons, self.ydButton);
                self.drawingView.setBrush(self.squareBrush);
                break;
            case R.id.ltButton:
                self.selectButton(self.singleSelectionButtons, self.ltButton);
                self.drawingView.setBrush(self.triangleBrush);
                break;
            case R.id.dxButton:
                self.selectButton(self.singleSelectionButtons, self.dxButton);
                self.drawingView.setBrush(self.triangleStrokeBrush);
                break;
            case R.id.lineButton:
                self.selectButton(self.singleSelectionButtons, self.lineButton);
                self.drawingView.setBrush(self.lineBrush);
                break;
            case R.id.rectButton:
                self.selectButton(self.singleSelectionButtons, self.rectButton);
                self.drawingView.setBrush(self.rectangleBrush);
                break;
            case R.id.locationButton:
                self.selectButton(self.singleSelectionButtons, self.locationButton);
                self.drawingView.setBrush(self.locationBrush);
                break;
            case R.id.isoTriangleButton:
                self.selectButton(self.singleSelectionButtons, self.isoTriangleButton);
                self.drawingView.setBrush(self.isoscelesTriangleBrush);
                break;
            case R.id.rightTriangleButton:
                self.selectButton(self.singleSelectionButtons, self.rightTriangleButton);
                self.drawingView.setBrush(self.rightAngledTriangleBrush);
                break;
            case R.id.arrowButton:
                self.selectButton(self.singleSelectionButtons, self.arrowButton);
                self.drawingView.setBrush(self.arrowBrush);
                break;
            case R.id.circleButton:
                self.selectButton(self.singleSelectionButtons, self.circleButton);
                self.drawingView.setBrush(self.centerCircleBrush);
                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /* #Accessors */
    public ThicknessAdjustController getThicknessAdjustController() {
        if (self.thicknessAdjustController == null) {
            self.thicknessAdjustController = new ThicknessAdjustController(self.getActivity());
            self.thicknessAdjustController.setThicknessDelegate(new ThicknessAdjustController.ThicknessDelegate() {
                @Override
                public void thicknessDidChangeFromThicknessAdjustController(ThicknessAdjustController controller, int thickness) {
                    self.penBrush.setSize(thickness);
                    self.textBrush.setSize(thickness);
                    for (DrawingBrush brush : self.shapeBrushes) {
                        brush.setSize(thickness);
                    }
                }
            });
        }
        return thicknessAdjustController;
    }

    private void selectButton(List<Button> buttons, Button button) {
        for (Button b : buttons) {
            b.setSelected(b == button);
        }
    }

}