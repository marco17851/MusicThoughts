package com.marcobarragan.thoughtmusic.artist;

import com.marcobarragan.thoughtmusic.models.Artist;
import com.marcobarragan.thoughtmusic.network.ThoughtMusicApiClient;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ArtistPresenter implements ArtistContract.Presenter {

    @Inject
    Retrofit retrofit;

    private ArtistContract.View mView;

    @Inject
    ArtistPresenter(ArtistContract.View view) {
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }
    @Override
    public void start() {
        ThoughtMusicApiClient client = retrofit.create(ThoughtMusicApiClient.class);
        Call<List<Artist>> call = client.getArtists();

        call.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                List<Artist> genres = response.body();
                mView.setArtists(genres);
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                System.out.println("Error connecting to API");
                System.out.println(call.request().toString());
                mView.showErrorMessage();
            }
        });
    }
}
