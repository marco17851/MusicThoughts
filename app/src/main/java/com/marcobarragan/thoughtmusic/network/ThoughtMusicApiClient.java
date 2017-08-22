package com.marcobarragan.thoughtmusic.network;

import com.marcobarragan.thoughtmusic.models.Album;
import com.marcobarragan.thoughtmusic.models.Artist;
import com.marcobarragan.thoughtmusic.models.Genre;
import com.marcobarragan.thoughtmusic.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ThoughtMusicApiClient {
    @GET("/api/1/category/tag/0")
    Call<List<Album>> getAlbums();

    @GET("/api/1/category/tag/1")
    Call<List<Artist>> getArtists();

    @GET("/api/1/category/tag/2")
    Call<List<Genre>> getGenres();

    @GET("/api/1/songs/multi")
    Call<List<Song>> getSongs(@Query("id") List<Integer> song_ids);

}
