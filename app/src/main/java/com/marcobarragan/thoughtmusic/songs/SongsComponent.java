package com.marcobarragan.thoughtmusic.songs;

import com.marcobarragan.thoughtmusic.dagger.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = {SongsModule.class})
public interface SongsComponent {
    void inject(SongsActivity songsActivity);
}
