package com.marcobarragan.thoughtmusic.songs;

import android.content.Intent;
import android.os.Bundle;
import android.support.transition.Visibility;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeSongData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SongsActivityTest {

    Intent intent;
    Bundle bundle;
    SongsActivity songsCategoryActivity;


    @Before
    public void setup(){
        bundle = new Bundle();
        intent = new Intent(Intent.ACTION_VIEW);
    }

    @Test
    public void shouldShowSongTitleTypeAndDescriptionOnRecyclerView(){
        songsCategoryActivity = Robolectric.buildActivity(SongsActivity.class, intent).create().get();

        RecyclerView recyclerView = (RecyclerView) songsCategoryActivity.findViewById(R.id.songs_recycler_view);

        songsCategoryActivity.setSongs(FakeSongData.getSampleSongs());

        assertNotNull(recyclerView);

        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);

        TextView songTitle = (TextView) recyclerView.getChildAt(0).findViewById(R.id.song_title);
        TextView songType = (TextView) recyclerView.getChildAt(0).findViewById(R.id.song_type);
        TextView songDescription = (TextView) recyclerView.getChildAt(0).findViewById(R.id.song_description);

        assertEquals("Mambo No. 5", songTitle.getText().toString());
        assertEquals("Basic", songType.getText().toString());
        assertEquals("Hit song from 1999!", songDescription.getText().toString());

        songTitle = (TextView) recyclerView.getChildAt(1).findViewById(R.id.song_title);
        songType = (TextView) recyclerView.getChildAt(1).findViewById(R.id.song_type);
        songDescription = (TextView) recyclerView.getChildAt(1).findViewById(R.id.song_description);

        assertEquals("Who Knew", songTitle.getText().toString());
        assertEquals("Stream", songType.getText().toString());
        assertEquals("One of the best songs by Pink!", songDescription.getText().toString());
    }

    @Test
    public void shouldShowErrorMessageIfObtainingEmptySongData(){
        songsCategoryActivity = Robolectric.buildActivity(SongsActivity.class, intent).create().get();

        songsCategoryActivity.setSongs(FakeSongData.getEmptySongs());

        TextView errorView = (TextView) songsCategoryActivity.findViewById(R.id.error_message);

        assertNotNull(errorView);

        assertEquals(View.VISIBLE, errorView.getVisibility());
        assertEquals("Unable to get Song data", errorView.getText().toString());

    }

}