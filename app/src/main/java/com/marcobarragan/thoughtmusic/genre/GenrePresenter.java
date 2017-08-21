package com.marcobarragan.thoughtmusic.genre;

import com.marcobarragan.thoughtmusic.retrofit.NetModule;
import com.marcobarragan.thoughtmusic.retrofit.ThoughtMusicApiClient;
import com.marcobarragan.thoughtmusic.models.Genre;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenrePresenter implements GenreContract.Presenter {

    @Inject
    Retrofit retrofit;

    private GenreContract.View mView;

    @Inject
    GenrePresenter(GenreContract.View view) {
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        ThoughtMusicApiClient client = retrofit.create(ThoughtMusicApiClient.class);
        Call<List<Genre>> call = client.getCategory("2");

        call.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                List<Genre> genres = response.body();
                System.out.println(genres.size());
                System.out.println(response.body());
                mView.setGenres(genres);
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                System.out.println("Error connecting to API");
                System.out.println(call.request().toString());
            }
        });

    }
}
