package com.marcobarragan.thoughtmusic.artist;

import com.marcobarragan.thoughtmusic.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArtistPresenterTest {

    ArtistContract.View mockView;
    ArtistPresenter presenter;

    @Before
    public void setup(){
        mockView = mock(ArtistFragment.class);

        presenter = new ArtistPresenter(mockView);
    }

    @Test
    public void shouldCallFragmentViewWhenSettingUpListener(){
        presenter.setupListeners();

        verify(mockView).setPresenter(presenter);
    }

}