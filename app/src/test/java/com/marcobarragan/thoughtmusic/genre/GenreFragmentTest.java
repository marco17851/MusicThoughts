package com.marcobarragan.thoughtmusic.genre;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeGenreData;
import com.marcobarragan.thoughtmusic.main.DaggerMainComponent;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.main.MainModule;
import com.marcobarragan.thoughtmusic.models.Genre;
import com.marcobarragan.thoughtmusic.network.NetModule;
import com.marcobarragan.thoughtmusic.songs.SongsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;
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
                .netModule(new NetModule("http://10.0.2.2:3000/"))
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

        List<Genre> genres = FakeGenreData.getSampleGenres();

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

    @Test
    public void shouldStartGenreCategoryActivityWithSongIdsInBundle(){
        List<Genre> genres = FakeGenreData.getSingleGenre();

        fragment.setGenres(genres);

        List<Integer> songIds = genres.get(0).getSongIds();
        fragment.onClick(songIds);

        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(SongsActivity.class.toString(), shadowIntent.getIntentClass().toString());
        assertEquals(startedIntent.getExtras().get("song_ids"), songIds);
    }
}