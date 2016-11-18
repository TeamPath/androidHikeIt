package com.pathteam.hikeitv2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.davidstemmer.flow.plugin.screenplay.ScreenplayDispatcher;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pathteam.hikeitv2.Model.HikeList;
import com.pathteam.hikeitv2.Model.hMarker;
import com.pathteam.hikeitv2.Stages.HikeItMapStage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.History;

public class MainActivity extends AppCompatActivity {

    String filename = "HikeHistoryFile";
    Gson gson = new Gson();
    public List<HikeList>  hikelist = new ArrayList<>();
    private ArrayList<Object>allItems = new ArrayList<>();
    // these are the basic lat longs that are used to set up first run save file. We don't want an empty
    LatLng setup1 = new LatLng(37.816,-82.809);
    LatLng setup2 = new LatLng(37.818,-82.810);
    LatLng setup3 = new LatLng(37.820,-82.811);
    LatLng setup4 = new LatLng(37.821,-82.811);

   // private ArrayList<mMarker> hikeArray;
    public ArrayList <hMarker> hike = new ArrayList<>();

    private Flow flow;
    private ScreenplayDispatcher dispatcher;

    @Bind(R.id.container)
    RelativeLayout container;
    public Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        flow = HikeApplication.getMainFlow();
        dispatcher=new ScreenplayDispatcher(this, container);
        dispatcher.setUp(flow);
//Get ready for our json save file.
        gson = new Gson();
        // lets go get our saved hikes!
        setupHikes();

        if (Build.VERSION.SDK_INT >= 23){
            if (!(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            if (!(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

    }


    // This sets up the inital list of hikes if there are none that the user has made.
    // This lets us get started. Also it check if the save file exists and if so read that info.
    private void setupHikes(){


        File filesDir = this.getFilesDir();
        File hikeFile = new File(filesDir + File.separator + filename);

        if (hikeFile.exists()) {
            readHikes(hikeFile);
        }else{
            // set up new hike info we will start with 3 hikes and each will have a set of markers.
            hikelist.add(new HikeList("Fist Hike", new ArrayList<hMarker>(),"These are notes and stuff."));
            hikelist.add(new HikeList("Second Hike", new ArrayList<hMarker>(),"These are notes and stuff."));
            hikelist.add(new HikeList("Third Hike", new ArrayList<hMarker>(),"These are notes and stuff."));
//this adds a few points to each of our hikes in our list.
            hikelist.get(0).hmarker.add(new hMarker(1,setup1,new Date()));
            hikelist.get(0).hmarker.add(new hMarker(2,setup4,new Date()));
            hikelist.get(1).hmarker.add(new hMarker(1,setup2,new Date()));
            hikelist.get(2).hmarker.add(new hMarker(1,setup3,new Date()));

            writeHikes();

        }
    }

    // app starts and reads the json file to fill the arrays with sweet sweet data.

    private void readHikes(File todoFile) {
        FileInputStream inputStream = null;
        String hikesText = "";
        try {
            inputStream = openFileInput(todoFile.getName());
            byte[] input = new byte[inputStream.available()];
            while (inputStream.read(input) != -1) {}
            hikesText = new String(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            Type collectionType = new TypeToken<List<HikeList>>(){}.getType();
            List<HikeList> categoryList = gson.fromJson(hikesText, collectionType);
            hikelist = new LinkedList(categoryList);

            updateAllItems();
            testArrays();


        }
    }
// this writes the json file for saving.  and we will call this method each time we end or save a hike.

    private void writeHikes() {
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

            String json = gson.toJson(hikelist);
            byte[] bytes = json.getBytes();
            outputStream.write(bytes);

            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception ignored) {}
        }
    }

    // we may not need this.  mark it for deletion

    private void updateAllItems() {
        allItems.clear();
        for(int i = 0; i < hikelist.size(); i++) {
            allItems.add(hikelist.get(i).getTitle());
            for(int j = 0; j < hikelist.get(i).hmarker.size(); j++) {
                allItems.add(hikelist.get(i).hmarker.get(j));
            }
        }
    }

    // This is how we will get the info from the arrays. we can replace the testArrays method with a production one.
    private void testArrays(){
        Integer listsize = hikelist.size();

        for (int i = 0; i < listsize; i++){
            Log.d("!!!!!", hikelist.get(i).getTitle());
            Log.d("!!!!!", hikelist.get(i).getHikenotes());
            Log.d("!!!!!", hikelist.get(i).hmarker.get(0).getDate().toString());
            for (int x = 0; x < hikelist.get(i).hmarker.size(); x++){
                Log.d("****", hikelist.get(i).hmarker.get(x).getMarkerId().toString());
                Log.d("****", hikelist.get(i).hmarker.get(x).getDate().toString());
                Log.d("****", hikelist.get(i).hmarker.get(x).getMarkerPos().toString());
            }
        }



    }
    @Override
    public void onBackPressed() {
        if (!flow.goBack()){
            flow.removeDispatcher(dispatcher);
            flow.setHistory(History.single(new HikeItMapStage()),
                    Flow.Direction.BACKWARD);
            super.onBackPressed();
        }
    }

}
