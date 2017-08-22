package com.marcobarragan.thoughtmusic.network;

import com.marcobarragan.thoughtmusic.models.Genre;
import com.marcobarragan.thoughtmusic.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ThoughtMusicApiClient {

    @GET("/api/1/category/tag/{tag_id}")
    Call<List<Genre>> getCategory(@Path("tag_id") String id);

    @GET("/api/1/songs/multi")
    Call<List<Song>> getSongs(@Query("id") List<Integer> song_ids);
}
