package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pathteam.hikeitv2.Model.HikeList;
import com.pathteam.hikeitv2.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

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

//        ((MapsView)context.)
//
//        for (int x = 0; x < markerHolder.size(); x++) {
//            Log.i("@@EVENT BUS MARKER@@: ", markerHolder.get(x).getMarkerId().toString());
//            Log.i("@@EVENT BUS MARKER@@: ", markerHolder.get(x).getDate().toString());
//            Log.i("@@EVENT BUS MARKER@@: ", markerHolder.get(x).getMarkerPos().toString());
//        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


}
