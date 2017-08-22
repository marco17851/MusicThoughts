package com.marcobarragan.thoughtmusic.artist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.models.Artist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArtistFragmentTest {

    ArtistFragment fragment;
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    @Before
    public void setup(){
        fragment = ArtistFragment.newInstance();
        startFragment(fragment, mainActivity.getClass());
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(fragment);
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