package com.marcobarragan.thoughtmusic.musicPlayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mDescription;
    private TextView mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Bundle bundle = getIntent().getExtras();

        mTitle = (TextView) findViewById(R.id.song_title);
        mType = (TextView) findViewById(R.id.song_type);
        mDescription = (TextView) findViewById(R.id.song_description);

        mTitle.setText(bundle.getString("name"));
        mType.setText(bundle.getString("type"));
        mDescription.setText(bundle.getString("description"));
    }
}
