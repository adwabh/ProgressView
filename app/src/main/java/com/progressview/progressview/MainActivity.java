package com.progressview.progressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.progressview.progressview.model.ProgressModel;
import com.progressview.stagedprogressviewlibrary.controller.ProgressViewController;
import com.progressview.stagedprogressviewlibrary.view.ProgressView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressView mProgressView;
    private LinkedList<ProgressModel> mList;
    private Controller mProgressController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressView = (ProgressView)findViewById(R.id.progress_view);
        mList =new LinkedList<>();
        mList.add(new ProgressModel());
        mList.add(new ProgressModel());
        mList.add(new ProgressModel());
        mList.add(new ProgressModel());


        mProgressController = new Controller(this,0,mList);
        mProgressView.setController(mProgressController);
    }



}
