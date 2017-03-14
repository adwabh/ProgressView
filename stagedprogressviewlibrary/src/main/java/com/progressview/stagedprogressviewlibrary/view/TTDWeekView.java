package com.progressview.stagedprogressviewlibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import com.progressview.stagedprogressviewlibrary.R;
/**
 * Created by adwait on 03/01/17.
 */

public class TTDWeekView extends View {

    public void setOnDateSelectListener(OnDateSelectListener mListener) {
        this.mListener = mListener;
    }

    private OnDateSelectListener mListener;

    public interface OnDateSelectListener{
        public void onDateSelected(Date date);
    }

    private static final int WEEK_DAY_COUNT = 7;
    private HashMap<Date,Rect> mDateMap;
    private Date mStartDate;
    private Date mEndDate;
    private Paint mBackgroundPaint;
    private Paint mForegroundPaint;
    private Paint mTextPaint;
    private Paint mDrawablePaint;

    public TTDWeekView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        mDateMap = new HashMap<>();

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(context.getResources().getColor(R.color.colorPrimaryLight));
        mForegroundPaint = new Paint();
        mForegroundPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mTextPaint = new Paint();
        mTextPaint.setColor(getResources().getColor(R.color.colorPrimaryText));
        mDrawablePaint = new Paint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for(int i = 0;i<WEEK_DAY_COUNT;i++){
                Date date = CommonUtil.addDays(mStartDate,i);
                Rect dateRect = mDateMap.get(date);
                if(dateRect.contains(Math.round(event.getX()),Math.round(event.getY()))){
                    if(mListener!=null){
                        mListener.onDateSelected(date);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas,mBackgroundPaint);
        drawBackgroundCircle(canvas);
        drawForegroundText(canvas);
    }

    private void drawBackground(Canvas canvas,@NonNull Paint paint){
        int width = getMeasuredWidth();
        int diameter = width/WEEK_DAY_COUNT;;
        int radius = diameter/2;
        canvas.drawRect(0,0,width,diameter,paint);
    }

    private void drawBackgroundCircle(Canvas canvas){
        int width = getMeasuredWidth();
        int diameter = width/WEEK_DAY_COUNT;;
        int radius = diameter/2;
        int cx = radius;
        for(int i=0;i<WEEK_DAY_COUNT;i++) {
            canvas.drawCircle(cx,radius,radius,mBackgroundPaint);
            cx +=diameter;
        }
    }

//    private void drawForegroundDrawable(Canvas canvas){
//        int width = getMeasuredWidth();
//        int diameter = width/WEEK_DAY_COUNT;
//        int radius = diameter/2;
//        int cx = radius;
//        for(int i=0;i<WEEK_DAY_COUNT;i++) {
//            double reductionFactor = radius/Math.sqrt(2);
//            Bitmap image = BitmapFactory.decodeResource(getResources(),controller.getItemHolder(i).getDrawable());
//
//            long top = Math.round(radius-image.getHeight()/2);
//            long left = Math.round(cx-image.getWidth()/2);
//            canvas.drawBitmap(image,left,top,mDrawablePaint);
//            cx += diameter;
//        }
//    }

    private void drawForegroundText(Canvas canvas){
        int width = getMeasuredWidth();
        int diameter = width/WEEK_DAY_COUNT;;
        int radius = diameter/2;
        int cx = radius;
        Date date;
        Rect rect;
        for(int i=0;i<WEEK_DAY_COUNT;i++) {
            date = CommonUtil.addDays(mStartDate,i);
            rect = new Rect(cx,radius,cx+diameter,radius+diameter);
            mDateMap.put(date,rect);
            canvas.drawText(String.valueOf(date.getDate()),cx,radius,mTextPaint);
            cx +=diameter;
        }
    }
    
    
    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date mStartDate) {
        this.mStartDate = mStartDate;
        this.mEndDate = CommonUtil.addDays(mStartDate,6);
    }

    public Date getEndDate() {
        return mEndDate;
    }

}
