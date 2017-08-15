package com.marcobarragan.thoughtmusic.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.marcobarragan.thoughtmusic.ThoughtMusicPagerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainActivity mMainActivity;

    public MainModule(MainActivity mainActivity){
        mMainActivity = mainActivity;
    }

    @Provides
    public MainActivity getMainActivity(){
        return mMainActivity;
    }

    @Provides
    public FragmentManager getFragmentManager(){
        return mMainActivity.getSupportFragmentManager();
    }

    @Provides
    public Context getActivityContext(){
        return mMainActivity;
    }
}
