package com.marcobarragan.musicthoughts.app;

import android.app.Application;

import com.marcobarragan.musicthoughts.dagger.AppComponent;
import com.marcobarragan.musicthoughts.dagger.AppModule;
import com.marcobarragan.musicthoughts.dagger.DaggerAppComponent;

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
