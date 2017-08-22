package com.marcobarragan.thoughtmusic.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album {
    @SerializedName("name")
    private String category;

    @SerializedName("id")
    private String categoryId;

    @SerializedName("song_ids")
    private List<Integer> songIds;

    @SerializedName("cover_url")
    private String cover;

    public Album(String category, String categoryId, List<Integer> songIds, String cover){

        this.category = category;
        this.categoryId = categoryId;
        this.songIds = songIds;
        this.cover = cover;
    }

    @Override
    public String toString() {
        String album = category + "{id:" + categoryId + ", " +
                "songIds:" + songIds + "}";

        return album;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public List<Integer> getSongIds() {
        return songIds;
    }

    public String getCover() {
        return cover;
    }
}
