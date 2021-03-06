package com.marcobarragan.thoughtmusic.dagger;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }

}
