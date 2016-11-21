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
import com.pathteam.hikeitv2.MainActivity;
import com.pathteam.hikeitv2.Model.MarkerLoadedEvent;
import com.pathteam.hikeitv2.Model.hMarker;
import com.pathteam.hikeitv2.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jeremiahlewis on 11/18/16.
 */

public class SaveHikeView extends RelativeLayout {

    private Context context;
    private ArrayList <hMarker> loadedMarkers;

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
        EventBus.getDefault().register(this);

        for (int x = 0; x < Constants.markersArray.size(); x++) {
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getMarkerId().toString());
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getDate().toString());
            Log.i("@@ARRAY@@: ", Constants.markersArray.get(x).getMarkerPos().toString());
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadMarkers(MarkerLoadedEvent event){
        loadedMarkers = event.markers;
        for (int x = 0; x < loadedMarkers.size(); x++) {
            Log.i("@@EVENT BUS MARKER@@: ", loadedMarkers.get(x).getMarkerId().toString());
            Log.i("@@EVENT BUS MARKER@@: ", loadedMarkers.get(x).getDate().toString());
            Log.i("@@EVENT BUS MARKER@@: ", loadedMarkers.get(x).getMarkerPos().toString());
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    @OnClick(R.id.pick_photo_button)
    public void getPic() {
        ((MainActivity) getContext()).getImage();

    }
    @OnClick(R.id.saveButton)
    public void save() {

        Log.d("PIC", Utils.encodeTobase64(Constants.me));
    }



}
