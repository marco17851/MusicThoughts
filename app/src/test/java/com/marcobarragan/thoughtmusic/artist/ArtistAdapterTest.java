package com.marcobarragan.thoughtmusic.artist;

import static org.junit.Assert.*;
import android.content.Context;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeArtistData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArtistAdapterTest {

    ArtistAdapter adapter;
    Context mockContext;
    ArtistAdapter.ArtistAdapterOnClickHandler mockClickHandler;

    @Before
    public void setup(){
        mockContext = mock(Context.class);
        mockClickHandler = mock(ArtistAdapter.ArtistAdapterOnClickHandler.class);
        adapter = new ArtistAdapter(mockContext, mockClickHandler);
    }

    @Test
    public void shouldReturnTrueIfGivenListOfArtistsWasNotNullOrEmptyAndItWasProcessed(){
        assertTrue(adapter.setArtists(FakeArtistData.getSampleArtists()));
    }

    @Test
    public void shouldReturnFalseIfGivenListOfArtistsWasNullAndNotProcessed(){
        assertFalse(adapter.setArtists(null));
    }

    @Test
    public void shouldReturnFalseIfGivenListOfArtistsWasEmptyAndNotProcessed(){
        assertFalse(adapter.setArtists(FakeArtistData.getEmptyArtists()));
    }

    @Test
    public void shouldReturnCorrectSizeOfArtistsList(){
        int correctSize = 3;
        adapter.setArtists(FakeArtistData.getSampleArtists());
        assertEquals(correctSize, adapter.getItemCount());
    }
}