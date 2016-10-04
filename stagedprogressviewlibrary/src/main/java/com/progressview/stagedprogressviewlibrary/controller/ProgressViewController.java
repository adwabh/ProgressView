package com.progressview.stagedprogressviewlibrary.controller;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ArrayAdapter;

import com.progressview.stagedprogressviewlibrary.view.ProgressView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adwait on 21/09/16.
 */
public abstract class ProgressViewController<T,VH extends ProgressViewController.ViewHolder> extends ArrayAdapter<T>{
    public ProgressView getProgressView() {
        return mView;
    }

    public void setView(ProgressView mView) {
        this.mView = mView;
    }

    protected ProgressView mView;
    protected HashMap<T,VH> mBindMap;
    private LinkedList<T> mList;
    private ProgressViewController mController;

    public ProgressViewController(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        mBindMap = new HashMap<>();
        mList = new LinkedList<>(objects);
    }

    public VH getItemHolder(int position){
        VH holder = onCreateViewHolder(position);
        T item = mList.get(position);
        mBindMap.put(item,holder);
        onBindViewHolder(holder,item,position);
        return mBindMap.get(getItem(position));
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        VH holder = onCreateViewHolder(position);
//        mBindMap.put(mList.get(position),holder);
//        return holder.getView();
//    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public abstract VH onCreateViewHolder(int position);

    public  abstract void onBindViewHolder(VH holder,T model,int position);


    public static class ViewHolder{
        private View mView;

        private int drawable;

        public Paint getForegroundPaint() {
            return mForegroundPaint;
        }

        public Paint getTextPaint() {
            return mTextPaint;
        }

        public Paint getDrawablePaint() {
            return mDrawablePaint;
        }

        private Paint mForegroundPaint;
        private Paint mTextPaint;
        private Paint mDrawablePaint;
        private int textColor;
        private int foregroundColor;

        public int getDrawable() {
            return drawable;
        }

        public void setDrawable(int drawable) {
            this.drawable = drawable;
        }

        public int getForegroundColor() {
            return foregroundColor;
        }

        public void setForegroundColor(int foregroundColor) {
            this.foregroundColor = foregroundColor;
            this.mForegroundPaint.setColor(foregroundColor);
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
            this.mTextPaint.setColor(textColor);
        }




        public ViewHolder(){
            init();
        }

        private void init(){
            mForegroundPaint = new Paint();
            mTextPaint = new Paint();
            mDrawablePaint = new Paint();
        }


//        public ViewHolder(View view){
//            this.mView = view;
//        }
//
//        public View getView(){
//            return mView;
//        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if(mView!=null){
            mView.invalidate();
        }
    }
}
