package com.progressview.progressview;

import android.content.Context;

import com.progressview.progressview.model.ProgressModel;
import com.progressview.stagedprogressviewlibrary.controller.ProgressViewController;
import com.progressview.stagedprogressviewlibrary.view.ProgressView;

import java.util.List;

/**
 * Created by adwait on 23/09/16.
 */
public class Controller extends ProgressViewController<ProgressModel,ProgressView.Holder>{

    public Controller(Context context, int resource, List<ProgressModel> objects) {
        super(context, resource, objects);
    }

    @Override
    public ProgressView.Holder onCreateViewHolder(int position) {
        return new ProgressView.Holder();
    }

    @Override
    public void onBindViewHolder(ProgressView.Holder holder, ProgressModel model, int position) {
        holder.setDrawable(R.drawable.ic_perm_media_black_24dp);
        holder.setForegroundColor(R.color.colorAccent);
        holder.setTextColor(R.color.colorAccent);
    }

}
