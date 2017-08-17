package com.marcobarragan.thoughtmusic.main;

import com.marcobarragan.thoughtmusic.dagger.PerActivity;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;

import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Component(modules = {GenreModule.class, MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(GenreFragment fragment);
}
