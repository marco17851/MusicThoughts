package com.marcobarragan.thoughtmusic.songs;

import com.marcobarragan.thoughtmusic.dagger.AppComponent;
import com.marcobarragan.thoughtmusic.dagger.PerActivity;
import com.marcobarragan.thoughtmusic.network.NetModule;

import dagger.Component;

@PerActivity
@Component(modules = {SongsModule.class, NetModule.class})
public interface SongsComponent {
    void inject(SongsActivity songsActivity);
}
