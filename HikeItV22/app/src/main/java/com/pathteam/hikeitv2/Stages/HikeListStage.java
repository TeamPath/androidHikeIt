package com.pathteam.hikeitv2.Stages;

import android.app.Application;
import com.davidstemmer.screenplay.stage.Stage;
import com.pathteam.hikeitv2.HikeApplication;
import com.pathteam.hikeitv2.R;
import com.pathteam.hikeitv2.Riggers.SlideRigger;
/**
 * Created by nicholashall on 11/16/16.
 */
public class HikeListStage extends IndexedStage {
    private final SlideRigger rigger;
    public HikeListStage(Application context){
        super(HikeListStage.class.getName());
        this.rigger = new SlideRigger(context);
    }
    public HikeListStage(){
        this(HikeApplication.getInstance());
    }
    @Override
    public int getLayoutId() {
        return R.layout.hike_list_view;
    }
    @Override
    public Stage.Rigger getRigger() {
        return rigger;
    }
}
