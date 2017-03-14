package com.progressview.stagedprogressviewlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.progressview.stagedprogressviewlibrary.R;

import java.util.Date;

/**
 * Created by adwait on 03/01/17.
 */

public class TTDCalenderMonthView extends FrameLayout implements TTDWeekView.OnDateSelectListener{
    public static final int WEEK_COUNT = 5;
    private ListView mListView;
    private WeekAdapter mWeekAdapter;

    public void setOnDateSelectedListener(TTDWeekView.OnDateSelectListener DateSelectedListener) {
        this.mDateSelectedListener = DateSelectedListener;
    }

    private TTDWeekView.OnDateSelectListener mDateSelectedListener;

    @Override
    public void onDateSelected(Date date) {
        if(mDateSelectedListener!=null){
            mDateSelectedListener.onDateSelected(date);
        }
    }

    public TTDCalenderMonthView(Context context) {
        super(context);
        init(context,null,0);
    }

    public TTDCalenderMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public TTDCalenderMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,null,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_calender_month,this,true);
        mListView = (ListView) view.findViewById(R.id.week_list);
        mWeekAdapter = new WeekAdapter(context,0,null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mListView.setAdapter(mWeekAdapter);
    }
}
