package com.progressview.stagedprogressviewlibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;


import com.progressview.stagedprogressviewlibrary.R;
import com.progressview.stagedprogressviewlibrary.controller.ProgressViewController;

/**
 * Created by adwait on 21/09/16.
 */
public class ProgressView extends View {

    private ProgressViewController mController;
    private Paint mBackgroundPaint;


    public ProgressView(Context context) {
        super(context);
        init(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        drawBackground(canvas,mBackgroundPaint);
        if (mController!=null) {
            drawBackgroundCircle(canvas,mController);
            drawForegroundDrawable(canvas,mController);
            drawForegroundText(canvas,mController);
        }
    }

    private void drawBackground(Canvas canvas,@NonNull Paint paint){
        int width = getMeasuredWidth();
        int diameter = width/mController.getCount();;
        int radius = diameter/2;
        canvas.drawRect(0,0,width,diameter,paint);
    }

    private void drawBackgroundCircle(Canvas canvas,ProgressViewController controller){
        int width = getMeasuredWidth();
        int diameter = width/mController.getCount();;
        int radius = diameter/2;
        int cx = radius;
        for(int i=0;i<controller.getCount();i++) {
            canvas.drawCircle(cx,radius,radius,controller.getItemHolder(i).getTextPaint());
            cx +=diameter;
        }
    }

    private void drawForegroundDrawable(Canvas canvas,ProgressViewController controller){
        int width = getMeasuredWidth();
        int diameter = width/mController.getCount();
        int radius = diameter/2;
        int cx = radius;
        for(int i=0;i<controller.getCount();i++) {
            double reductionFactor = radius/Math.sqrt(2);
            Bitmap image = BitmapFactory.decodeResource(getResources(),controller.getItemHolder(i).getDrawable());

            long top = Math.round(radius-image.getHeight()/2);
            long left = Math.round(cx-image.getWidth()/2);
            canvas.drawBitmap(image,left,top,controller.getItemHolder(i).getDrawablePaint());
            cx += diameter;
        }
    }

    private void drawForegroundText(Canvas canvas,ProgressViewController controller){

    }

    public void setController(ProgressViewController controller){
        this.mController = controller;
        controller.setView(this);
        invalidate();
    }

    public static class Holder extends ProgressViewController.ViewHolder {

    }
}
