package com.marcobarragan.thoughtmusic.artists;

import com.marcobarragan.thoughtmusic.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArtistsFragmentTest {

    @Test
    public void shouldNotBeNull() throws Exception
    {
        ArtistsFragment fragment = new ArtistsFragment();
        startFragment( fragment );
        assertNotNull( fragment );
    }


}