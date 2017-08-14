package com.marcobarragan.thoughtmusic.app;

import android.app.Application;

import com.marcobarragan.thoughtmusic.dagger.AppComponent;
import com.marcobarragan.thoughtmusic.dagger.AppModule;
import com.marcobarragan.thoughtmusic.dagger.DaggerAppComponent;

public class MusicThoughtsApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = initDagger(this);
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    protected AppComponent initDagger(MusicThoughtsApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
