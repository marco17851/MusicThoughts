package com.marcobarragan.thoughtmusic.dagger;

import com.marcobarragan.thoughtmusic.app.ThoughtMusicApplication;
import com.marcobarragan.thoughtmusic.network.ImageDownloaderModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ImageDownloaderModule.class, AppModule.class})
public interface AppComponent {
    void inject(ThoughtMusicApplication application);
}
