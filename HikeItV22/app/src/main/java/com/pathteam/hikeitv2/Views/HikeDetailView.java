package com.pathteam.hikeitv2.Views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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

public class HikeDetailView extends RelativeLayout implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    public LatLng Home;
    private double lat = 0;
    private double lng = 0;
    public String name;
    public String id;
    Location oldLocation = new Location("none");
    Location newLocation = new Location("newLocation");
    float distance = 0;
    float totalDis = 0;
    LatLng oldcoord;


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

        mapView.getMapAsync(this);
        mapView.onCreate(((MainActivity) getContext()).savedInstanceState);
        mapView.onResume();

        hike = hikelist.get(position);
        title.setText(hike.getTitle());


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        UiSettings UiSettings = mMap.getUiSettings();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        mMap.setMyLocationEnabled(true);
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home, 18));
    }
}