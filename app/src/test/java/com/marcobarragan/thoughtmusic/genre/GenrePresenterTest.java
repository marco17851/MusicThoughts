package com.marcobarragan.thoughtmusic.genre;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.fakeData.FakeDataRepository;
import com.marcobarragan.thoughtmusic.models.Genre;

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
public class GenrePresenterTest {

    FakeDataRepository mockRepository;
    GenreContract.View mockView;
    GenrePresenter presenter;

    @Before
    public void setup(){
        mockRepository = mock(FakeDataRepository.class);
        mockView = mock(GenreFragment.class);

        presenter = new GenrePresenter(mockView);
    }

    @Test
    public void shouldCallFragmentViewWhenSettingUpListener(){
        presenter.setupListeners();

        verify(mockView).setPresenter(presenter);
    }

    @Test
    public void shouldCallFakeDataRepositoryOnStartAndPassToView(){
        presenter.start();
        verify(mockView).setGenres(ArgumentMatchers.<Genre>anyList());
    }

}