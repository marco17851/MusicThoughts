package com.marcobarragan.thoughtmusic.main;

import com.marcobarragan.thoughtmusic.dagger.AppComponent;
import com.marcobarragan.thoughtmusic.dagger.PerActivity;
import com.marcobarragan.thoughtmusic.genre.GenreContract;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;
import com.marcobarragan.thoughtmusic.genre.GenrePresenter;
import com.marcobarragan.thoughtmusic.retrofit.NetModule;

import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Component(modules = {GenreModule.class, MainModule.class, NetModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(GenreFragment fragmentView);
}
