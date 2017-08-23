package com.marcobarragan.thoughtmusic.album;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.artist.ArtistFragment;
import com.marcobarragan.thoughtmusic.artist.ArtistModule;
import com.marcobarragan.thoughtmusic.fakeTestData.FakeAlbumData;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;
import com.marcobarragan.thoughtmusic.main.DaggerMainComponent;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.main.MainModule;
import com.marcobarragan.thoughtmusic.models.Album;
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
public class AlbumFragmentTest {

    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
    ArtistFragment mockArtistFragment;
    GenreFragment mockGenreFragment;
    AlbumFragment fragment;

    @Before
    public void setup(){
        fragment = AlbumFragment.newInstance();
        mockArtistFragment = mock(ArtistFragment.class);
        mockGenreFragment = mock(GenreFragment.class);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(mainActivity))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .artistModule(new ArtistModule(mockArtistFragment))
                .genreModule(new GenreModule(mockGenreFragment))
                .albumModule(new AlbumModule(fragment)).build().inject(fragment);
        startFragment(fragment, mainActivity.getClass());
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(fragment);
    }

    @Test
    public void shouldShowListOfThreeAlbumsOnRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.albums_recycler_view);

        assertNotNull(recyclerView);

        List<Album> albums = FakeAlbumData.getSampleAlbums();

        fragment.setAlbums(albums);

        // https://stackoverflow.com/a/27069766
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);

        TextView album1 = (TextView) recyclerView.getChildAt(0).findViewById(R.id.item_list_title);
        TextView album2 = (TextView) recyclerView.getChildAt(1).findViewById(R.id.item_list_title);
        TextView album3 = (TextView) recyclerView.getChildAt(2).findViewById(R.id.item_list_title);

        assertEquals("Album1", album1.getText().toString());
        assertEquals("Album2", album2.getText().toString());
        assertEquals("Album3", album3.getText().toString());
    }

    @Test
    public void shouldShowErrorMessageIfThereIsAlbumDataCannotBeUpdated(){
        TextView errorMessage = (TextView) fragment.getView().findViewById(R.id.albums_error_message);

        fragment.showErrorMessage();


        assertEquals(View.VISIBLE, errorMessage.getVisibility());
        assertEquals("Unable to get updated Album data", errorMessage.getText().toString());
    }

    @Test
    public void shouldStartAlbumCategoryActivityWithSongIdsInBundle(){
        List<Album> albums = FakeAlbumData.getSingleAlbum();

        fragment.setAlbums(albums);

        List<Integer> songIds = albums.get(0).getSongIds();
        fragment.onClick(songIds);

        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(SongsActivity.class.toString(), shadowIntent.getIntentClass().toString());
        assertEquals(startedIntent.getExtras().get("song_ids"), songIds);
    }
}