package com.vilyever.androiddrawingview;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.vilyever.drawingview.DrawingView;
import com.vilyever.drawingview.brush.drawing.LocationBrush;
import com.vilyever.drawingview.brush.drawing.ShapeBrush;

import java.util.ArrayList;
import java.util.List;

public class DrawingFragment2 extends Fragment {
    private final DrawingFragment2 self = this;

    private DrawingView drawingView;

    private Button lineButton;
    private Button arrowButton;

    private List<Button> singleSelectionButtons;
    int locationNum = 1;
    private List<ShapeBrush> shapeBrushes = new ArrayList<>();
    Spinner sp;

    /* #Constructors */
    public DrawingFragment2() {
        // Required empty public constructor
    }


    /* #Overrides */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.drawing_fragment2, container, false);

        self.drawingView = (DrawingView) rootView.findViewById(R.id.drawingView);
//        self.shapeBrushes.add(LineBrush.defaultBrush());
        final LocationBrush brush = LocationBrush.defaultBrush();
        drawingView.setBrush(brush);
        sp = (Spinner) rootView.findViewById(R.id.sp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationNum  = position+1;
                brush.setNum(locationNum);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        drawingView.setOntouchListener(new DrawingView.OntouchListener() {
            @Override
            public void touch(boolean isTouchUp) {
                locationNum++;
                brush.setNum(locationNum);
            }
        });
        return rootView;
    }
}