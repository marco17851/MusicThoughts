package com.marcobarragan.thoughtmusic.artists;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.models.Genre;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GenreFragmentTest {

    GenreFragment fragment;

    @Before
    public void setup(){
        fragment = GenreFragment.newInstance();
        startFragment(fragment, MainActivity.class);
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(fragment);
    }

    @Test
    public void shouldShowListOfThreeGenresOnRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.genres_recycler_view);

        assertNotNull(recyclerView);

        List<Genre> genres = getSampleGenres();
        fragment.setGenres(genres);

        // https://stackoverflow.com/a/27069766
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);

        TextView pop = (TextView) recyclerView.getChildAt(0).findViewById(R.id.genre_list_title);
        TextView rap = (TextView) recyclerView.getChildAt(1).findViewById(R.id.genre_list_title);
        TextView disco = (TextView) recyclerView.getChildAt(2).findViewById(R.id.genre_list_title);

        assertEquals("Pop", pop.getText().toString());
        assertEquals("Rap", rap.getText().toString());
        assertEquals("Disco", disco.getText().toString());
    }

    private List<Genre> getSampleGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Pop", "0"));
        genres.add(new Genre("Rap", "1"));
        genres.add(new Genre("Disco", "2"));
        return genres;
    }


}