package com.marcobarragan.thoughtmusic.songs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marcobarragan.thoughtmusic.R;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

public class SongsActivity extends AppCompatActivity implements SongsContract.View {

    private RecyclerView mRecyclerView;

    @Inject
    SongsAdapter mSongsAdapter;

    @Inject
    SongsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        DaggerSongsComponent.builder().songsModule(new SongsModule(this)).build().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        mRecyclerView = (RecyclerView) findViewById(R.id.songs_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mSongsAdapter);
    }

    @Override
    public void setPresenter(SongsContract.Presenter presenter) {
        mPresenter = (SongsPresenter) checkNotNull(presenter);
    }
}
