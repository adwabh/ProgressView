package com.progressview.stagedprogressviewlibrary.view;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by adwait on 20/12/16.
 */
public class CommonUtil {
    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
