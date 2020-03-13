package com.vilyever.androiddrawingview;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.vilyever.drawingview.DrawingView;
import com.vilyever.drawingview.brush.drawing.ArrowBrush;
import com.vilyever.drawingview.brush.drawing.CenterCircleBrush;
import com.vilyever.drawingview.brush.drawing.CircleBrush;
import com.vilyever.drawingview.brush.drawing.DrawingBrush;
import com.vilyever.drawingview.brush.drawing.LocationBrush;
import com.vilyever.drawingview.brush.drawing.PenBrush;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class DrawingFragment1 extends Fragment implements View.OnClickListener ,Spinner.OnItemSelectedListener{
    private final DrawingFragment1 self = this;

    private DrawingView drawingView;

    private Button undoButton;
    private Button redoButton;
    private Button clearButton;

    private Button penButton, rectButton, lineButton, dxButton, ydButton, ltButton, arrowButton, isoTriangleButton, rightTriangleButton, circleButton;
    //    private Button shapeButton;
    private Button textButton,saveButton,legendButton;
    private Button backgroundColorButton;
    private Spinner locationSpinner;
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
    RelativeLayout legend;
    int locationNum = 1;
    public DrawingFragment1() {
    }
    boolean showLegendTv = false;
Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    try {
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                showLegendTv = true;
                                break;
                            }
                        }
                        legend.findViewById(R.id.tv_legend).setVisibility(showLegendTv ?View.VISIBLE:View.GONE);
                        legend.findViewById(R.id.ll_yd).setVisibility(checkedItems[0]?View.VISIBLE:View.GONE);
                        legend.findViewById(R.id.ll_lt).setVisibility(checkedItems[1]?View.VISIBLE:View.GONE);
                        legend.findViewById(R.id.ll_dx).setVisibility(checkedItems[2]?View.VISIBLE:View.GONE);
                        legend.findViewById(R.id.ll_arrow).setVisibility(checkedItems[3]?View.VISIBLE:View.GONE);
                        drawingView.addView(legend);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) legend.getLayoutParams();
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        showLegendTv = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.drawing_fragment1, container, false);
        legend = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.legend, null);
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
        self.legendButton = (Button) rootView.findViewById(R.id.legendButton);
        self.legendButton.setOnClickListener(this);
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
        self.locationSpinner = (Spinner) rootView.findViewById(R.id.locationSpinner);
        locationSpinner.setOnItemSelectedListener(this);
        self.circleButton = (Button) rootView.findViewById(R.id.circleButton);
        self.circleButton.setOnClickListener(this);
        self.saveButton = (Button) rootView.findViewById(R.id.saveButton);
        self.saveButton.setOnClickListener(this);
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
        self.singleSelectionButtons.add(self.circleButton);
        self.singleSelectionButtons.add(self.eraserButton);

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
        drawingView.setOntouchListener(new DrawingView.OntouchListener() {
            @Override
            public void touch(boolean isTouchUp) {
                locationNum++;
                self.locationBrush.setNum(locationNum);
            }
        });
        getBackground();
    }
    private void getBackground() {
        try {
            String picName = SharedPreferencesUtils.getString(getActivity(), "picName", null);
            if (!TextUtils.isEmpty(picName)) {
                FileInputStream fs = new FileInputStream("/sdcard/aa/"+picName);
                Bitmap bitmap  = BitmapFactory.decodeStream(fs);
                Drawable drawable = new BitmapDrawable(bitmap);
                drawingView.setBackground(drawable);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void setBackground() {
        try {
            drawingView.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    float size = -1;
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
                setBackground();
                self.drawingView.clear();
                drawingView.removeView(legend);
//                drawingView.setDrawingStepDelegate(null);
                break;
            case R.id.penButton:
                self.penBrush.setIsEraser(false);
                if (size!=-1) {
                self.penBrush.setSize(size);
                }
                self.selectButton(self.singleSelectionButtons, self.penButton);
                self.drawingView.setBrush(self.penBrush);
                break;
            case R.id.textButton:
                self.selectButton(self.singleSelectionButtons, self.textButton);
                self.drawingView.setBrush(self.textBrush);
                break;
            case R.id.eraserButton:
                size = penBrush.getSize();
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
            case R.id.saveButton:
                //避免保存时图片有键盘光标
                self.selectButton(self.singleSelectionButtons, self.ydButton);
                self.drawingView.setBrush(self.squareBrush);
                savePic();
                break;
            case R.id.legendButton:
                legendDialog();
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        self.selectButton(self.singleSelectionButtons, null);
        self.drawingView.setBrush(self.locationBrush);
        locationNum = position + 1;
        self.locationBrush.setNum(locationNum);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    boolean[] checkedItems;
    private void legendDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择要生成的图例！");
        final String items[] = {"移动","联通","电信","箭头"};
        checkedItems = new boolean[]{false,false,false,false};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                drawingView.removeView(legend);
                handler.sendEmptyMessage(0);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void selectButton(List<Button> buttons, Button button) {
        for (Button b : buttons) {
            b.setSelected(b == button);
        }
    }

    private void savePic() {
        /** 保存可编辑状态的示意图 */
        File picPath = new File("/sdcard/aa/");
        getFileDir("/sdcard/aa/");

        UUID uuid = UUID.randomUUID();
        drawingView.removeView(legend);
        String nonFileName = uuid + ".png";
        File nonEditPicFile = new File(picPath, nonFileName);
        FileOutputStream efs = null;
        try {
            Bitmap mBitmap = viewConversionBitmap(drawingView);
            if (mBitmap == null) return;
            efs = new FileOutputStream(nonEditPicFile);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, efs);
            SharedPreferencesUtils.saveString(getActivity(),"picName",nonFileName);
        } catch (Exception e) {
            Log.e(TAG, "Export error - " + e.getMessage());
        } finally {
            try {
                if (efs != null) {
                    efs.flush();
                    efs.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Export error - " + e.getMessage());
            }
        }
    }
    public Bitmap viewConversionBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }
    public void getFileDir(String path) {
        // 创建文件夹
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

}