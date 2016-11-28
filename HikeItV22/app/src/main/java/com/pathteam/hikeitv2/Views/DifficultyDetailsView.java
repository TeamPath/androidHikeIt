package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pathteam.hikeitv2.HikeApplication;
import com.pathteam.hikeitv2.R;
import com.pathteam.hikeitv2.Stages.HikeItMapStage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;

/**
 * Created by jeremiahlewis on 11/22/16.
 */

public class DifficultyDetailsView extends LinearLayout {

    @Bind(R.id.got_it_button)
    Button confirmButton;
    public DifficultyDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.got_it_button)
    public void goBack(){
        Flow flow = HikeApplication.getMainFlow();
        History newHistory = flow.getHistory().buildUpon()
                .push(new HikeItMapStage())
                .build();
        flow.setHistory(newHistory, Flow.Direction.REPLACE);

    }
    }
