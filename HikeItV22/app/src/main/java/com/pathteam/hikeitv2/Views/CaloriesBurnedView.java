package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

public class CaloriesBurnedView extends LinearLayout {

    private Context context;
    Double MET;
    Double totalCaloriesBurned;
    Integer totalTimeInHours=0;
    Integer totalTimeInMinutes=0;
    Double totalWeight;

    @Bind(R.id.selectedWeight)
    EditText selectedWeight;

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

    @Bind(R.id.selectedMinutes)
    EditText selectedMinutes;

    @Bind(R.id.selectedHours)
    EditText selectedHours;

    public CaloriesBurnedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    private Double calculateCaloriesBurned() {

        InputMethodManager imm = (InputMethodManager)context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(selectedWeight.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(selectedHours.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(selectedMinutes.getWindowToken(), 0);

        if (lightButton.isChecked()) {
            MET = 2.3;
        }
        if (moderateButton.isChecked()) {
            MET = 3.6;
        }
        if (intenseButton.isChecked()) {
            MET = 5.3;
        }

        String timeInMinutes = selectedMinutes.getText().toString();
        if(timeInMinutes.isEmpty()){
            totalTimeInMinutes = 0;
        } else {
            totalTimeInMinutes = Integer.parseInt(timeInMinutes);
        }

        String timeInHours = selectedHours.getText().toString();
        if(timeInHours.isEmpty()){
            totalTimeInHours = 0;
        } else {
            totalTimeInHours = Integer.parseInt(timeInHours);
        }

        String weight = selectedWeight.getText().toString();
         totalWeight = Double.parseDouble(weight);

        totalCaloriesBurned = (((totalTimeInHours*60)+totalTimeInMinutes) *(MET *(totalWeight/2.2)))/200;
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
    public void getDetails() {
        Flow flow = HikeApplication.getMainFlow();
        History newHistory = flow.getHistory().buildUpon()
                .push(new DifficultyDetailsStage())
                .build();
        flow.setHistory(newHistory, Flow.Direction.FORWARD);

    }
}
