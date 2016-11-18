package com.pathteam.hikeitv2.Views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pathteam.hikeitv2.MainActivity;
import com.pathteam.hikeitv2.Model.hMarker;
import com.pathteam.hikeitv2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pathteam.hikeitv2.R.id.map;

public class MapsView extends RelativeLayout implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private Context context;
    public LatLng Home;
    private double lat = 0;
    private double lng = 0;
    public String name;
    public String id;
   // Date date = new Date();
    Location oldLocation = new Location("none");
    Location newLocation = new Location("newLocation");
    float distance = 0;
    float totalDis = 0;
    LatLng oldcoord;
    double value;

    Handler handler = new Handler();

    @Bind(map)
    MapView mapView;

<<<<<<< HEAD
    java.util.Date date = new java.util.Date();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    //System.out.println(sdf.format(date));

    public ArrayList<String> markers = new ArrayList<>();
=======
    public ArrayList<hMarker> markers = new ArrayList<>();
>>>>>>> master

    public MapsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        ButterKnife.bind(this);
        super.onFinishInflate();
        mapView.getMapAsync(this);
        mapView.onCreate(((MainActivity) getContext()).savedInstanceState);
        mapView.onResume();
    }

    //This is the timer for dropping markers-- Alex edit of marker arrays
    int i = 0;
    final Runnable r = new Runnable() {
        public void run() {
            i = i + 1;

            Integer markerNum = i;
            if (Home != null) {
                mMap.addMarker(new MarkerOptions()
                        .snippet("Marker: " + markerNum)
                        .zIndex(i)
                        .draggable(true)
                        .title(date.toString())
                        .position(Home));
                Log.d("@@@@@@@", "Hello");
<<<<<<< HEAD
                markers.add(markerNum);
                markers.add(Home.toString());
                markers.add(sdf.format(date).toString());
                //System.out.println(sdf.format(date));
               // markers.add(sdf.format(date).toString());
=======
                hMarker currentMarker = new hMarker(markerNum, Home, date);
                markers.add(currentMarker);
>>>>>>> master

                for (int x = 0; x < markers.size(); x++) {
                    Log.i("@@MARKER@@: ", markers.get(x).getMarkerId().toString());
                    Log.i("@@MARKER@@: ", markers.get(x).getDate().toString());
                    Log.i("@@MARKER@@: ", markers.get(x).getMarkerPos().toString());
                }

            }
            handler.postDelayed(this, 30000);
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        UiSettings UiSettings = mMap.getUiSettings();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        mMap.setMyLocationEnabled(true);
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
        LatLng current = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(current).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        return true;
    }

    protected GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            // This if statement make sre oldcoord isn't null or starting at some random location
            if (lat < 1) {
                lat = location.getLatitude();
                lng = location.getLongitude();
                Home = new LatLng(lat, lng);
                oldcoord = Home;
            } else {
                lat = location.getLatitude();
                lng = location.getLongitude();
                Home = new LatLng(lat, lng);
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home, 18));
            // this if statement waits for the first marker tobe dropped before starting to draw the polyline
            if (i > 0) {
                mMap.addPolyline((new PolylineOptions())
                        .add(Home, oldcoord)
                        .color(Color.RED)
                        .width(25));

                // newLocation is your current location
                newLocation.setLatitude(lat);
                newLocation.setLongitude(lng);
                // This if, make sure oldLocation isn't 0,0 (the middle of the earth)
                if (oldLocation.toString().equals("Location[none 0.000000,0.000000 acc=??? t=?!? et=?!?]")) {
                    oldLocation.setLatitude(lat);
                    oldLocation.setLongitude(lng);
                }
                oldcoord = Home;
                date = new Date();
                //System.out.println(sdf.format(date));

                //This measures from point to point on the polyline, converts it to miles
                distance = oldLocation.distanceTo(newLocation) / 1609;

                // oldLocation is the location you were at last onChange
                oldLocation.setLatitude(lat);
                oldLocation.setLongitude(lng);

                // adds the total distance of your Hike
                totalDis = totalDis + distance;
                String trip = String.valueOf(totalDis);
                value = Double.parseDouble(trip);
                value = Math.round(totalDis * 100.0)/100.0;
                Log.d("*******", String.valueOf(value));
            }

        }
    };

    //StartButton
    @OnClick(R.id.start_button)
    public void startHike() {
        if (i < 1) {
            // this is where the Start button call the timer to start
            handler.postDelayed(r, 100);
        }
    }
}





