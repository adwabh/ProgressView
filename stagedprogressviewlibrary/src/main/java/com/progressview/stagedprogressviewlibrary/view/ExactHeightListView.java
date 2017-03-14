package com.progressview.stagedprogressviewlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by adwait on 05/01/17.
 */

public class ExactHeightListView extends ListView {
    private int mChildren = 1;

    public ExactHeightListView(Context context) {
        super(context);
    }

    public ExactHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExactHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        if(null!=getAdapter()&&getChildCount()>0){
            expandSpec = MeasureSpec.makeMeasureSpec(Math.round(getAdapter().getCount()*getChildAt(0).getHeight()),MeasureSpec.EXACTLY);
        }
        setMeasuredDimension(widthMeasureSpec,expandSpec);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        mChildren = adapter.getCount();
    }
}
