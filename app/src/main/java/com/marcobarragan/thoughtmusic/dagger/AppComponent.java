package com.marcobarragan.thoughtmusic.dagger;

import com.marcobarragan.thoughtmusic.app.ThoughtMusicApplication;
import com.marcobarragan.thoughtmusic.main.MainActivity;
import com.marcobarragan.thoughtmusic.main.MainComponent;
import com.marcobarragan.thoughtmusic.main.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(ThoughtMusicApplication application);

    MainComponent newMainComponent(MainModule mainModule);
}
