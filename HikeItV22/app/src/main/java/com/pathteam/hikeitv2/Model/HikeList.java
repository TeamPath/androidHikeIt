package com.pathteam.hikeitv2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by alexhughes on 11/16/16.
 */

public class HikeList {

    @SerializedName("title")
    private String title;

    @SerializedName("markers")
    public ArrayList<hMarker> hmarker;

    @SerializedName("hikeNotes")
    public String hikeNotes;

    @SerializedName("imagestring")
    public String imageString;


    public HikeList(String title, ArrayList<hMarker> hmarker, String hikenotes, String imageString) {
        this.title = title;
        this.hmarker = hmarker;
        this.hikeNotes = hikenotes;
        this.imageString = imageString;
    }

    public HikeList(String title, ArrayList<hMarker> hmarker) {
        this.title = title;
        this.hmarker = hmarker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHikeNotes() {
        return hikeNotes;
    }

    public void setHikeNotes(String hikeNotes) {
        this.hikeNotes = hikeNotes;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public ArrayList<hMarker> getHmarker() {
        return hmarker;
    }

    public void setHmarker(ArrayList<hMarker> hmarker) {
        this.hmarker = hmarker;
    }
}
