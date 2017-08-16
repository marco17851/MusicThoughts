package com.marcobarragan.thoughtmusic.main;

import com.marcobarragan.thoughtmusic.dagger.PerActivity;
import com.marcobarragan.thoughtmusic.genre.GenreModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {GenreModule.class, MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
