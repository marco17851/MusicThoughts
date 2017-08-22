package com.marcobarragan.thoughtmusic.songs;

import com.marcobarragan.thoughtmusic.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SongsPresenterTest {

    SongsContract.View mockView;
    SongsPresenter presenter;

    @Before
    public void setup(){
        mockView = mock(SongsActivity.class);

        presenter = new SongsPresenter(mockView);
    }

    @Test
    public void shouldCallFragmentViewWhenSettingUpListener(){
        presenter.setupListeners();

        verify(mockView).setPresenter(presenter);
    }

}