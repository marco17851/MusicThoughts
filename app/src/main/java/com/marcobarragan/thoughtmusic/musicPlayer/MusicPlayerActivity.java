package com.marcobarragan.thoughtmusic.musicPlayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.network.ImageDownloader;

import javax.inject.Inject;

public class MusicPlayerActivity extends AppCompatActivity {

    @Inject
    ImageDownloader mImageDownloader;

    private TextView mTitle;
    private TextView mDescription;
    private TextView mType;
    private ImageView mCover;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DaggerMusicPlayerComponent.builder()
                .musicPlayerModule(new MusicPlayerModule(this))
                .build()
                .inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        postponeEnterTransition();
        // https://stackoverflow.com/a/26748694
        Transition fade = new Fade();
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        getWindow().setExitTransition(fade);
        getWindow().setEnterTransition(fade);

        Bundle bundle = getIntent().getExtras();

        mTitle = (TextView) findViewById(R.id.song_title);
        mType = (TextView) findViewById(R.id.song_type);
        mDescription = (TextView) findViewById(R.id.song_description);
        mCover = (ImageView) findViewById(R.id.song_cover);

        mTitle.setText(bundle.getString("name"));
        mType.setText(bundle.getString("type"));
        mDescription.setText(bundle.getString("description"));
        mImageDownloader.loadImageFromUrl(bundle.getString("cover"), mCover);

        startPostponedEnterTransition();
    }
}
