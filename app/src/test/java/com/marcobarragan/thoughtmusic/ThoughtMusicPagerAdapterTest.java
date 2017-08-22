package com.marcobarragan.thoughtmusic;

import android.support.v4.app.FragmentManager;

import com.marcobarragan.thoughtmusic.artist.ArtistFragment;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ThoughtMusicPagerAdapterTest {

    FragmentManager mockManager;
    ArtistFragment mockArtistFragment;
    GenreFragment mockGenreFragment;
    ThoughtMusicPagerAdapter adapter;

    @Before
    public void setup(){
        mockManager = mock(FragmentManager.class);
        mockArtistFragment = mock(ArtistFragment.class);
        mockGenreFragment = mock(GenreFragment.class);
        adapter = new ThoughtMusicPagerAdapter(mockManager, mockArtistFragment, mockGenreFragment);
    }

    @Test
    public void shouldReturnCorrectNumberOfFragments(){
        int correctNumber = 3;

        assertEquals(correctNumber, adapter.getCount());
    }

    @Test
    public void shouldReturnCorrectGenreFragmentOnPositionTwo(){
        String genreTitle = "Genres";

        assertEquals(mockGenreFragment, adapter.getItem(2));
        assertEquals(genreTitle, adapter.getPageTitle(2));
    }



}