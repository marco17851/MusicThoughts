package com.marcobarragan.thoughtmusic.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Song {

    @SerializedName("name")
    private String title;

    @SerializedName("type")
    private String[] type;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("cover_url")
    private String cover;

    public Song(String title, String[] type, String description, int id, String cover){

        this.title = title;
        this.type = type;
        this.description = description;
        this.id = id;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type[0];
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getCover() {
        return cover;
    }
}
