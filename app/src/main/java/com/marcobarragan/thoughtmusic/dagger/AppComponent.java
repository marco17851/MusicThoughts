package com.marcobarragan.thoughtmusic.dagger;

import com.marcobarragan.thoughtmusic.app.ThoughtMusicApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(ThoughtMusicApplication application);
}
