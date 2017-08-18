package com.marcobarragan.thoughtmusic.songs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marcobarragan.thoughtmusic.R;

public class SongsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SongsAdapter mSongsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        mRecyclerView = (RecyclerView) findViewById(R.id.songs_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSongsAdapter = new SongsAdapter(this);
        mRecyclerView.setAdapter(mSongsAdapter);
    }
}
