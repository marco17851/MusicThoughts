package com.marcobarragan.thoughtmusic.genre;

import android.content.Context;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.genre.fakeData.FakeGenreData;
import com.marcobarragan.thoughtmusic.models.Genre;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GenreAdapterTest {

    GenreAdapter adapter;
    Context mockContext;
    GenreAdapter.GenreAdapterOnClickHandler mockClickHandler;

    @Before
    public void setup(){
        mockContext = mock(Context.class);
        mockClickHandler = mock(GenreAdapter.GenreAdapterOnClickHandler.class);
        adapter = new GenreAdapter(mockContext, mockClickHandler);
    }

    @Test
    public void shouldReturnTrueIfGivenListOfGenresWasNotNullOrEmptyAndItWasProcessed(){
        assertTrue(adapter.setGenres(FakeGenreData.getSampleGenres()));
    }

    @Test
    public void shouldReturnFalseIfGivenListOfGenresWasNullAndNotProcessed(){
        assertFalse(adapter.setGenres(null));
    }

    @Test
    public void shouldReturnFalseIfGivenListOfGenresWasEmptyAndNotProcessed(){
        assertFalse(adapter.setGenres(FakeGenreData.getEmptyGenres()));
    }

    @Test
    public void shouldReturnCorrectSizeOfGenresList(){
        int correctSize = 3;
        adapter.setGenres(FakeGenreData.getSampleGenres());
        assertEquals(correctSize, adapter.getItemCount());
    }
}