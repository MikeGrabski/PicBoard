package com.g.mike.orbdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.opencv.core.Mat;

/**
 * Created by iosuser11 on 8/1/16.
 */
public class MyCustomView extends View {

    private Bitmap image;
    private Paint paint;
    private Matrix mat;
    boolean drawingState;

    public MyCustomView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mat = new Matrix();
        paint = new Paint();
        drawingState = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(drawingState) {
            Log.d("ondraw", "onDraw: called");
            image = BitmapFactory.decodeResource(getResources(), R.drawable.penguin);
            mat.setValues(new float[]{1,0,220,0,1,350,0,0,1});
            canvas.drawBitmap(image, mat, paint);
        }
    }

    void setDrawingState(boolean state) {
        drawingState = state;
    }

    void setTransformMatrix(Mat homographymat) {
        mat.setValues(new float[]{homographymat.step1(0), homographymat.step1(1), homographymat.step1(2), homographymat.step1(3), homographymat.step1(4), homographymat.step1(5), homographymat.step1(6), homographymat.step1(7), homographymat.step1(8)});
    }
}
