package com.marcobarragan.thoughtmusic.songs;

import com.marcobarragan.thoughtmusic.fakeData.FakeDataRepository;
import com.marcobarragan.thoughtmusic.models.Song;
import com.marcobarragan.thoughtmusic.retrofit.ThoughtMusicApiClient;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SongsPresenter implements SongsContract.Presenter  {

    @Inject
    Retrofit retrofit;
    
    private SongsContract.View mView;
    private List<Integer> songIds;

    @Inject
    SongsPresenter(SongsContract.View view){
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        ThoughtMusicApiClient client = retrofit.create(ThoughtMusicApiClient.class);
        Call<List<Song>> call = client.getSongs(songIds);

        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> Songs = response.body();
                mView.setSongs(Songs);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                System.out.println("Error connecting to API");
                System.out.println(call.request().toString());
                t.printStackTrace();
                mView.showErrorMessage();
            }
        });
    }

    public void setSongIds(ArrayList<Integer> song_ids) {
        this.songIds = song_ids;
    }
}
