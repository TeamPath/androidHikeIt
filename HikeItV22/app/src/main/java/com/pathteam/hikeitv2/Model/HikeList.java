package com.pathteam.hikeitv2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by alexhughes on 11/16/16.
 */

public class HikeList {
    @SerializedName("Title")
    private String Title;

    @SerializedName("markers")
    public ArrayList<hMarker> hmarker;

    @SerializedName("HikeNotes")
    public String hikenotes;



    public HikeList(String title, ArrayList<hMarker> hmarker, String hikenotes) {
        Title = title;
        this.hmarker = hmarker;
        this.hikenotes = hikenotes;
    }

    public HikeList(String title, ArrayList<hMarker> hmarker) {
        Title = title;
        this.hmarker = hmarker;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getHikenotes() {
        return hikenotes;
    }

    public void setHikenotes(String hikenotes) {
        this.hikenotes = hikenotes;
    }
}
