package com.marcobarragan.thoughtmusic.models;

import java.util.List;

public class Song {

    private final String title;
    private final String type;
    private final String description;

    public Song(String title, String type, String description){

        this.title = title;
        this.type = type;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
