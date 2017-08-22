package com.marcobarragan.thoughtmusic.artist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeArtistData;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;
import com.marcobarragan.thoughtmusic.main.DaggerMainComponent;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.main.MainModule;
import com.marcobarragan.thoughtmusic.models.Artist;
import com.marcobarragan.thoughtmusic.network.NetModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArtistFragmentTest {

    ArtistFragment fragment;
    GenreFragment mockGenreFragment;
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    @Before
    public void setup(){
        fragment = ArtistFragment.newInstance();
        mockGenreFragment = mock(GenreFragment.class);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(mainActivity))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .artistModule(new ArtistModule(fragment))
                .genreModule(new GenreModule(mockGenreFragment))
                .build().inject(fragment);
        startFragment(fragment, mainActivity.getClass());
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(fragment);
    }

    @Test
    public void shouldShowListOfThreeGenresOnRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.artists_recycler_view);

        assertNotNull(recyclerView);

        List<Artist> artists = FakeArtistData.getSampleArtists();

        fragment.setArtists(artists);

        // https://stackoverflow.com/a/27069766
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);

        TextView artist1 = (TextView) recyclerView.getChildAt(0).findViewById(R.id.artist_list_title);
        TextView artist2 = (TextView) recyclerView.getChildAt(1).findViewById(R.id.artist_list_title);
        TextView artist3 = (TextView) recyclerView.getChildAt(2).findViewById(R.id.artist_list_title);

        assertEquals("Artist1", artist1.getText().toString());
        assertEquals("Artist2", artist2.getText().toString());
        assertEquals("Artist3", artist3.getText().toString());
    }

    @Test
    public void shouldShowErrorMessageIfThereIsGenreDataCannotBeUpdated(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.artists_recycler_view);
        TextView errorMessage = (TextView) fragment.getView().findViewById(R.id.artists_error_message);

        fragment.showErrorMessage();

        assertEquals(View.VISIBLE, errorMessage.getVisibility());
        assertEquals("Unable to get updated Artist data", errorMessage.getText().toString());
    }
}