package com.marcobarragan.thoughtmusic.models;

public class Genre {

    private final String category;
    private final String categoryId;

    public Genre(String category, String categoryId){

        this.category = category;
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
