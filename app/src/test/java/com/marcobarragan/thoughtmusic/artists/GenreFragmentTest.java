package com.marcobarragan.thoughtmusic.artists;

import android.support.transition.Visibility;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.genre.GenreAdapter;
import com.marcobarragan.thoughtmusic.genre.GenreContract;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;
import com.marcobarragan.thoughtmusic.main.DaggerMainComponent;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.main.MainModule;
import com.marcobarragan.thoughtmusic.models.Genre;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import edu.emory.mathcs.backport.java.util.Arrays;

import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GenreFragmentTest {

    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
    GenreFragment fragment;

    @Before
    public void setup(){
        fragment = GenreFragment.newInstance();
        DaggerMainComponent.builder()
                .mainModule(new MainModule(mainActivity))
                .genreModule(new GenreModule(fragment)).build().inject(fragment);
        startFragment(fragment, mainActivity.getClass());
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

    @Test
    public void shouldShowErrorMessageIfThereIsGenreDataCannotBeUpdated(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.genres_recycler_view);
        TextView errorMessage = (TextView) fragment.getView().findViewById(R.id.error_message);

        fragment.showErrorMessage();


        assertEquals(View.VISIBLE, errorMessage.getVisibility());
        assertEquals("Unable to get updated Genre data", errorMessage.getText().toString());

    }

    private List<Genre> getSampleGenres() {
        List<Genre> genres = new ArrayList<>();

        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);
        songIds.add(2);
        songIds.add(3);

        genres.add(new Genre("Pop", "0", songIds));
        genres.add(new Genre("Rap", "1", songIds));
        genres.add(new Genre("Disco", "2", songIds));
        return genres;
    }


}