package com.marcobarragan.thoughtmusic.musicPlayer;

import com.marcobarragan.thoughtmusic.dagger.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = {MusicPlayerModule.class})
public interface MusicPlayerComponent {
    void inject(MusicPlayerActivity musicPlayerActivity);
}