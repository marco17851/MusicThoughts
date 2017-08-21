package com.marcobarragan.thoughtmusic.retrofit;

import com.marcobarragan.thoughtmusic.models.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ThoughtMusicApiClient {

    @GET("/api/1/category/tag/{tag_id}")
    Call<List<Genre>> getCategory(@Path("tag_id") String id);
}
