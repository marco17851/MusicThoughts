package com.marcobarragan.musicthoughts.dagger;

import com.marcobarragan.musicthoughts.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
}
