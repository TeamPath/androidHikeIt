package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pathteam.hikeitv2.Components.Constants;
import com.pathteam.hikeitv2.Components.Utils;
import com.pathteam.hikeitv2.HikeApplication;
import com.pathteam.hikeitv2.MainActivity;
import com.pathteam.hikeitv2.Model.HikeList;
import com.pathteam.hikeitv2.R;
import com.pathteam.hikeitv2.Stages.MainMenuStage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;


/**
 * Created by jeremiahlewis on 11/18/16.
 */

public class SaveHikeView extends RelativeLayout {

    private Context context;

    public ArrayList <HikeList> currentHike = new ArrayList<>();


    @Bind(R.id.saveButton)
    Button saveButton;

    @Bind(R.id.pick_photo_button)
    Button choosePhoto;

    @Bind(R.id.hike_title)
    EditText hikeTitle;

    @Bind(R.id.hike_notes)
    EditText hikeNotes;

    @Bind(R.id.galleryPicture)
    ImageView galleryPicture;




    // formats date to just hours and min.
    java.util.Date date = new java.util.Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");




    public SaveHikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    @OnClick(R.id.pick_photo_button)
    public void getPic() {
        ((MainActivity) getContext()).getImage();

    }
    @OnClick(R.id.saveButton)
    public void save() {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.camera);
        Date startDate = Constants.markersArray.get(0).getDate();
        int i = Constants.markersArray.size()-1;
        Date endDate = Constants.markersArray.get(i).getDate();
        String time = "";
        try {
          Date  d1 = startDate;
            Date d2 = endDate;

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            time = "Duration: "+ String.valueOf(diffHours)+":"+String.valueOf(diffMinutes)+":"+String.valueOf(diffSeconds);

        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText title   = (EditText)findViewById(R.id.hike_title);
        EditText note   = (EditText)findViewById(R.id.hike_notes);
        try {
            ((MainActivity) getContext()).hikelist.add(new HikeList(title.getText().toString() + "\n" + "Distance: " + Constants.distance + "\n" + time, Constants.markersArray, note.getText().toString(), Utils.encodeTobase64(Constants.me)));
        } catch (Exception e) {
            ((MainActivity) getContext()).hikelist.add(new HikeList(title.getText().toString() + "\n" + "Distance: " + Constants.distance + "\n" + time, Constants.markersArray, note.getText().toString(), Utils.encodeTobase64(bitmap)));
            Toast.makeText(context, "You Did Not Choose an Image!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        ((MainActivity) getContext()).writeHikes();
            Flow flow = HikeApplication.getMainFlow();
        flow.setHistory(History.single(new MainMenuStage()),
                Flow.Direction.BACKWARD);

        }

    }


