package com.marcobarragan.thoughtmusic.album;

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
public class AlbumPresenterTest {

    AlbumContract.View mockView;
    AlbumPresenter presenter;

    @Before
    public void setup(){
        mockView = mock(AlbumFragment.class);

        presenter = new AlbumPresenter(mockView);
    }

    @Test
    public void shouldCallFragmentViewWhenSettingUpListener(){
        presenter.setupListeners();

        verify(mockView).setPresenter(presenter);
    }

}