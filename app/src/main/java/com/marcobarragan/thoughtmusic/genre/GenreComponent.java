package com.marcobarragan.thoughtmusic.genre;

import dagger.Subcomponent;

@Subcomponent(modules = {GenreModule.class})
public interface GenreComponent {
    void inject(GenreFragment fragment);
}
