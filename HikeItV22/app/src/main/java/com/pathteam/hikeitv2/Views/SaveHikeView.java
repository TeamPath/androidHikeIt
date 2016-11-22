package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pathteam.hikeitv2.Components.Constants;
import com.pathteam.hikeitv2.Components.Utils;
import com.pathteam.hikeitv2.HikeApplication;
import com.pathteam.hikeitv2.MainActivity;
import com.pathteam.hikeitv2.Model.HikeList;
import com.pathteam.hikeitv2.R;
import com.pathteam.hikeitv2.Stages.MainMenuStage;

import java.util.ArrayList;

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



    public SaveHikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    @Override
    protected void onFinishInflate() {



        super.onFinishInflate();
        ButterKnife.bind(this);
       // EventBus.getDefault().register(this);

        for (int x = 0; x < Constants.markersArray.size(); x++) {
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getMarkerId().toString());
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getDate().toString());
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getMarkerPos().toString());
        }

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
<<<<<<< HEAD
        EditText title   = (EditText)findViewById(R.id.hike_title);
        EditText note   = (EditText)findViewById(R.id.hike_notes);
        ((MainActivity) getContext()).hikelist.add(new HikeList(title.getText().toString(), Constants.markersArray,note.getText().toString(),Utils.encodeTobase64(Constants.me)));
        ((MainActivity) getContext()).writeHikes();
            Flow flow = HikeApplication.getMainFlow();
        flow.setHistory(History.single(new MainMenuStage()),
                Flow.Direction.BACKWARD);
=======

        ((MainActivity) getContext()).hikelist.add(new HikeList("Mayo Lake Trail", Constants.markersArray,"A trail surrounding the lake. Easy to Medium Difficulty level. Some more text to fill space....",Utils.encodeTobase64(Constants.me)));
        if (Constants.me != null) {
            Log.d("PIC", Utils.encodeTobase64(Constants.me));
        }
>>>>>>> master
    }




}
