package com.pathteam.hikeitv2.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.pathteam.hikeitv2.MainActivity;
import com.pathteam.hikeitv2.Model.HikeList;
import com.pathteam.hikeitv2.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.pathteam.hikeitv2.MainActivity.position;

/**
 * Created by nicholashall on 11/17/16.
 */

public class HikeDetailView extends RelativeLayout{
    private Context context;
    public HikeList hike;
    public List<HikeList> hikelist;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.mapview)
    MapView mapView;
    @Bind(R.id.back)
    Button back;
    @Bind(R.id.take_a_hike)
    Button takeHike;
    public HikeDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        hikelist= new ArrayList<>(((MainActivity)context).hikelist);
        hike = hikelist.get(position);
        title.setText(hike.getTitle());
    }
}