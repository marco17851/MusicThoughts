package com.marcobarragan.thoughtmusic.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Genre {

    @SerializedName("name")
    private String category;

    @SerializedName("id")
    private String categoryId;

    @SerializedName("song_ids")
    private List<Integer> songIds;

    public Genre(String category, String categoryId, List<Integer> songIds){

        this.category = category;
        this.categoryId = categoryId;
        this.songIds = songIds;
    }

    @Override
    public String toString() {
        String genre = category + "{id:" + categoryId + ", " +
                "songIds:" + songIds + "}";

        return genre;
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
}
