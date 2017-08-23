package com.marcobarragan.thoughtmusic.artist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.album.AlbumFragment;
import com.marcobarragan.thoughtmusic.album.AlbumModule;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeArtistData;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;
import com.marcobarragan.thoughtmusic.main.DaggerMainComponent;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.main.MainModule;
import com.marcobarragan.thoughtmusic.models.Album;
import com.marcobarragan.thoughtmusic.models.Artist;
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
import static org.mockito.Mockito.mock;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArtistFragmentTest {

    ArtistFragment fragment;
    GenreFragment mockGenreFragment;
    AlbumFragment mockAlbumFragment;
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    @Before
    public void setup(){
        fragment = ArtistFragment.newInstance();
        mockGenreFragment = mock(GenreFragment.class);
        mockAlbumFragment = mock(AlbumFragment.class);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(mainActivity))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .artistModule(new ArtistModule(fragment))
                .genreModule(new GenreModule(mockGenreFragment))
                .albumModule(new AlbumModule(mockAlbumFragment))
                .build().inject(fragment);
        startFragment(fragment, mainActivity.getClass());
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(fragment);
    }

    @Test
    public void shouldShowListOfThreeArtistsOnRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.artists_recycler_view);

        assertNotNull(recyclerView);

        List<Artist> artists = FakeArtistData.getSampleArtists();

        fragment.setArtists(artists);

        // https://stackoverflow.com/a/27069766
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);

        TextView artist1 = (TextView) recyclerView.getChildAt(0).findViewById(R.id.item_list_title);
        TextView artist2 = (TextView) recyclerView.getChildAt(1).findViewById(R.id.item_list_title);
        TextView artist3 = (TextView) recyclerView.getChildAt(2).findViewById(R.id.item_list_title);

        assertEquals("Artist1", artist1.getText().toString());
        assertEquals("Artist2", artist2.getText().toString());
        assertEquals("Artist3", artist3.getText().toString());
    }

    @Test
    public void shouldShowErrorMessageIfThereIsArtistDataCannotBeUpdated(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.artists_recycler_view);
        TextView errorMessage = (TextView) fragment.getView().findViewById(R.id.artists_error_message);

        fragment.showErrorMessage();

        assertEquals(View.VISIBLE, errorMessage.getVisibility());
        assertEquals("Unable to get updated Artist data", errorMessage.getText().toString());
    }

    @Test
    public void shouldStartArtistCategoryActivityWithSongIdsInBundle(){
        List<Artist> artists = FakeArtistData.getSingleArtist();

        fragment.setArtists(artists);

        List<Integer> songIds = artists.get(0).getSongIds();
        fragment.onClick(songIds);

        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(SongsActivity.class.toString(), shadowIntent.getIntentClass().toString());
        assertEquals(startedIntent.getExtras().get("song_ids"), songIds);
    }
}