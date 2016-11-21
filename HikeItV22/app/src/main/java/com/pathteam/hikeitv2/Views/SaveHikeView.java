package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

<<<<<<< HEAD
import com.pathteam.hikeitv2.Components.Constants;
import com.pathteam.hikeitv2.Components.Utils;
import com.pathteam.hikeitv2.MainActivity;
import com.pathteam.hikeitv2.Model.MarkerLoadedEvent;
import com.pathteam.hikeitv2.Model.hMarker;
=======
import com.pathteam.hikeitv2.Model.HikeList;
>>>>>>> master
import com.pathteam.hikeitv2.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
<<<<<<< HEAD
        EventBus.getDefault().register(this);

        for (int x = 0; x < Constants.markersArray.size(); x++) {
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getMarkerId().toString());
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getDate().toString());
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getMarkerPos().toString());
        }

    }
=======
>>>>>>> master

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

<<<<<<< HEAD
    @OnClick(R.id.pick_photo_button)
    public void getPic() {
        ((MainActivity) getContext()).getImage();

    }
    @OnClick(R.id.saveButton)
    public void save() {

        Log.d("PIC", Utils.encodeTobase64(Constants.me));
    }


=======
>>>>>>> master

}
