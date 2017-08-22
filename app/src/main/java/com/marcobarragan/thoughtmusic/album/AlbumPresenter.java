package com.marcobarragan.thoughtmusic.album;

import com.marcobarragan.thoughtmusic.models.Album;
import com.marcobarragan.thoughtmusic.network.ThoughtMusicApiClient;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AlbumPresenter implements AlbumContract.Presenter {

    @Inject
    Retrofit retrofit;

    private AlbumContract.View mView;

    @Inject
    AlbumPresenter(AlbumContract.View view) {
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }
    @Override
    public void start() {
        ThoughtMusicApiClient client = retrofit.create(ThoughtMusicApiClient.class);
        Call<List<Album>> call = client.getAlbums();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> genres = response.body();
                mView.setAlbums(genres);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                System.out.println("Error connecting to API");
                System.out.println(call.request().toString());
                mView.showErrorMessage();
            }
        });
    }
}
