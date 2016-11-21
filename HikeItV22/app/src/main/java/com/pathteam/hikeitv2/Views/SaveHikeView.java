package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pathteam.hikeitv2.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jeremiahlewis on 11/18/16.
 */

public class SaveHikeView extends RelativeLayout {
    private Context context;

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
    }


}
