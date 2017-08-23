package com.marcobarragan.thoughtmusic.musicPlayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MusicPlayerActivityTest {

    Intent intent;
    Bundle bundle = getSongBundle();
    MusicPlayerActivity musicPlayerActivity;


    @Before
    public void setup(){
        intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtras(bundle);
    }

    @Test
    public void shouldShowSongTitleTypeAndDescription(){
        musicPlayerActivity = Robolectric.buildActivity(MusicPlayerActivity.class, intent).create().get();

        TextView songTitle = (TextView) musicPlayerActivity.findViewById(R.id.song_title);
        TextView songType = (TextView) musicPlayerActivity.findViewById(R.id.song_type);
        TextView songDescription = (TextView) musicPlayerActivity.findViewById(R.id.song_description);

        assertEquals("Mambo No. 5", songTitle.getText().toString());
        assertEquals("Basic", songType.getText().toString());
        assertEquals("Hit song from 1999!", songDescription.getText().toString());
    }

    private Bundle getSongBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("name", "Mambo No. 5");
        bundle.putString("description", "Hit song from 1999!");
        bundle.putString("type", "Basic");

        return bundle;
    }

}