package com.marcobarragan.thoughtmusic.songs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.models.Song;
import com.marcobarragan.thoughtmusic.retrofit.NetModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

public class SongsActivity extends AppCompatActivity implements SongsContract.View {

    private RecyclerView mRecyclerView;

    private TextView mErrorView;

    @Inject
    SongsAdapter mSongsAdapter;

    @Inject
    SongsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSongComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        Bundle extras = getIntent().getExtras();
        ArrayList<Integer> song_ids = (ArrayList<Integer>) extras.get("song_ids");

        mPresenter.setSongIds(song_ids);

        mErrorView = (TextView) findViewById(R.id.error_message);

        mRecyclerView = (RecyclerView) findViewById(R.id.songs_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mSongsAdapter);
    }

    private SongsComponent getSongComponent() {
        return DaggerSongsComponent.builder()
                .songsModule(new SongsModule(this))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(SongsContract.Presenter presenter) {
        mPresenter = (SongsPresenter) checkNotNull(presenter);
    }

    @Override
    public void setSongs(List<Song> songs) {
        if (mSongsAdapter.setSongs(songs)){
            hideErrors();
        } else {
            showErrorMessage();
        }
    }

    private void hideErrors() {
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        mErrorView.setVisibility(View.VISIBLE);
    }
}
