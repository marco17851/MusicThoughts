package com.marcobarragan.thoughtmusic.app;

import android.app.Application;

import com.marcobarragan.thoughtmusic.dagger.AppComponent;
import com.marcobarragan.thoughtmusic.dagger.AppModule;
import com.marcobarragan.thoughtmusic.dagger.DaggerAppComponent;

public class ThoughtMusicApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        mAppComponent = initDagger(this);
        mAppComponent.inject(this);
        super.onCreate();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    protected AppComponent initDagger(ThoughtMusicApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
