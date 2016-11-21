package com.pathteam.hikeitv2.Stages;

import android.app.Application;

import com.davidstemmer.screenplay.stage.Stage;
import com.pathteam.hikeitv2.HikeApplication;
import com.pathteam.hikeitv2.R;
import com.pathteam.hikeitv2.Riggers.SlideRigger;

/**
 * Created by jeremiahlewis on 11/18/16.
 */

public class StopHikeStage extends IndexedStage {
    private final SlideRigger rigger;
    public StopHikeStage(Application context){
        super(HikeListStage.class.getName());
        this.rigger = new SlideRigger(context);
    }
    public StopHikeStage(){
        this(HikeApplication.getInstance());
    }
    @Override
    public int getLayoutId() {
        return R.layout.stop_hike_view;
    }
    @Override
    public Stage.Rigger getRigger() {
        return rigger;
    }
    
}
