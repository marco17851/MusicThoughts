package com.marcobarragan.thoughtmusic.main;

import android.support.v4.app.FragmentManager;

import com.marcobarragan.thoughtmusic.ThoughtMusicPagerAdapter;
import com.marcobarragan.thoughtmusic.dagger.AppComponent;
import com.marcobarragan.thoughtmusic.dagger.AppModule;
import com.marcobarragan.thoughtmusic.dagger.PerActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
