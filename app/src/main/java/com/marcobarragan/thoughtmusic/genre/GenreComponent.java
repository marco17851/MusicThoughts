package com.marcobarragan.thoughtmusic.genre;

import com.marcobarragan.thoughtmusic.main.MainComponent;
import com.marcobarragan.thoughtmusic.main.MainModule;

import dagger.Component;
import dagger.Subcomponent;

@Subcomponent(modules = {GenreModule.class})
public interface GenreComponent {
    void inject(GenreFragment fragment);
}
