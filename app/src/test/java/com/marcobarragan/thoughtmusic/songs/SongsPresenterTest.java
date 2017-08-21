package com.marcobarragan.thoughtmusic.songs;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.fakeData.FakeDataRepository;
import com.marcobarragan.thoughtmusic.models.Song;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SongsPresenterTest {

    FakeDataRepository mockRepository;
    SongsContract.View mockView;
    SongsPresenter presenter;

    @Before
    public void setup(){
        mockRepository = mock(FakeDataRepository.class);
        mockView = mock(SongsActivity.class);

        presenter = new SongsPresenter(mockView);
    }

    @Test
    public void shouldCallFragmentViewWhenSettingUpListener(){
        presenter.setupListeners();

        verify(mockView).setPresenter(presenter);
    }

}