package com.progressview.stagedprogressviewlibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;



import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adwait on 04/01/17.
 */
public class WeekAdapter extends ArrayAdapter<WeekRange> {

//    private LinkedList<WeekRange> mList;
    public WeekAdapter(Context context, int resource, List<WeekRange> objects) {
        super(context, resource, objects);
//        mList = new LinkedList<>(objects);
    }

    @Override
    public int getCount() {
        return TTDCalenderMonthView.WEEK_COUNT;
    }

//    @Nullable
//    @Override
//    public WeekRange getItem(int position) {
//        return mList.get(position);
//    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = new TTDWeekView(getContext());
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mWeekView.setStartDate(Calendar.getInstance().getTime());
        return convertView;
    }

    public class ViewHolder{
        public TTDWeekView mWeekView;

        public ViewHolder(View view){
            mWeekView = (TTDWeekView) view;
        }
    }
}
