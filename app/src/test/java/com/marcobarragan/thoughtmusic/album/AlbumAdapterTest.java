package com.marcobarragan.thoughtmusic.album;

import android.content.Context;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeAlbumData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class AlbumAdapterTest {
    AlbumAdapter adapter;
    Context mockContext;
    AlbumAdapter.AlbumAdapterOnClickHandler mockClickHandler;

    @Before
    public void setup(){
        mockContext = mock(Context.class);
        mockClickHandler = mock(AlbumAdapter.AlbumAdapterOnClickHandler.class);
        adapter = new AlbumAdapter(mockContext, mockClickHandler);
    }

    @Test
    public void shouldReturnTrueIfGivenListOfAlbumsWasNotNullOrEmptyAndItWasProcessed(){
        assertTrue(adapter.setAlbums(FakeAlbumData.getSampleAlbums()));
    }

    @Test
    public void shouldReturnFalseIfGivenListOfAlbumsWasNullAndNotProcessed(){
        assertFalse(adapter.setAlbums(null));
    }

    @Test
    public void shouldReturnFalseIfGivenListOfAlbumsWasEmptyAndNotProcessed(){
        assertFalse(adapter.setAlbums(FakeAlbumData.getEmptyAlbums()));
    }

    @Test
    public void shouldReturnCorrectSizeOfAlbumsList(){
        int correctSize = 3;
        adapter.setAlbums(FakeAlbumData.getSampleAlbums());
        assertEquals(correctSize, adapter.getItemCount());
    }

}