package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pathteam.hikeitv2.HikeApplication;
import com.pathteam.hikeitv2.R;
import com.pathteam.hikeitv2.Stages.DifficultyDetailsStage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;

/**
 * Created by JoshuaMabry on 11/18/16.
 */

public class CaloriesBurnedView extends RelativeLayout {
    Double MET;
    Double totalCaloriesBurned;

    @Bind(R.id.selectedWeight)
    EditText selectedWeight;
    @Bind(R.id.durationOfHike)
    EditText durationOfHike;
    @Bind(R.id.lightButton)
    RadioButton lightButton;
    @Bind(R.id.moderateButton)
    RadioButton moderateButton;
    @Bind(R.id.intenseButton)
    RadioButton intenseButton;
    @Bind(R.id.displayCaloriesBurned)
    TextView displayCaloriesBurned;
    @Bind(R.id.calculateButton)
    Button calculateButton;
    @Bind(R.id.getDetailsButton)
    Button getDetailsButton;
    public CaloriesBurnedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }
    private Double calculateCaloriesBurned(){
        if(lightButton.isChecked()){
            MET = 2.3;
        }
        if(moderateButton.isChecked()){
            MET = 3.6;
        }
        if(intenseButton.isChecked()){
            MET = 5.3;
        }
        String Time = durationOfHike.getText().toString();
        Double totalTime = Double.parseDouble(Time);

        String weight = selectedWeight.getText().toString();
        Double totalWeight = Double.parseDouble(weight);

        totalCaloriesBurned = ((totalTime*60) *(MET *(totalWeight/2.2)))/200;
        totalCaloriesBurned = Math.round(totalCaloriesBurned *100.0)/100.0;
        return totalCaloriesBurned;
    }
    @OnClick(R.id.calculateButton)
    public void Calculate(){
        calculateCaloriesBurned();
        String caloriesBurned = totalCaloriesBurned.toString();
        displayCaloriesBurned.setText(caloriesBurned);
    }

    @OnClick(R.id.getDetailsButton)
    public void getDetails(){
        Flow flow = HikeApplication.getMainFlow();
        History newHistory = flow.getHistory().buildUpon()
                .push(new DifficultyDetailsStage())
                .build();
        flow.setHistory(newHistory, Flow.Direction.FORWARD);

    }
}
