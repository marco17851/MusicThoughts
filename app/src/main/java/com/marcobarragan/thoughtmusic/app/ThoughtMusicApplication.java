package com.marcobarragan.thoughtmusic.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.marcobarragan.thoughtmusic.dagger.AppComponent;
import com.marcobarragan.thoughtmusic.dagger.AppModule;
import com.marcobarragan.thoughtmusic.dagger.DaggerAppComponent;
import com.marcobarragan.thoughtmusic.network.ImageDownloaderModule;
import io.fabric.sdk.android.Fabric;

public class ThoughtMusicApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        mAppComponent = initDagger(this);
        mAppComponent.inject(this);
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    protected AppComponent initDagger(ThoughtMusicApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .imageDownloaderModule(new ImageDownloaderModule(this))
                .build();
    }

}
