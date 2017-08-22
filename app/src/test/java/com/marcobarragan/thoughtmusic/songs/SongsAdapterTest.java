package com.marcobarragan.thoughtmusic.songs;

import android.content.Context;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeSongData;
import com.marcobarragan.thoughtmusic.network.ImageDownloader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SongsAdapterTest {

    SongsAdapter adapter;
    Context mockContext;
    ImageDownloader mockImageDownloader;

    @Before
    public void setup(){
        mockContext = mock(Context.class);
        mockImageDownloader = mock(ImageDownloader.class);
        adapter = new SongsAdapter(mockContext, mockImageDownloader);
    }

    @Test
    public void shouldReturnTrueIfGivenListOfSongsWasNotNullOrEmptyAndItWasProcessed(){
        assertTrue(adapter.setSongs(FakeSongData.getSampleSongs()));
    }

    @Test
    public void shouldReturnFalseIfGivenListOfSongsWasNullOrEmpty(){
        assertFalse(adapter.setSongs(FakeSongData.getEmptySongs()));
    }

    @Test
    public void shouldReturnCorrectSizeOfSongList(){
        int correctSize = 2;
        adapter.setSongs(FakeSongData.getSampleSongs());
        assertEquals(correctSize, adapter.getItemCount());
    }

}