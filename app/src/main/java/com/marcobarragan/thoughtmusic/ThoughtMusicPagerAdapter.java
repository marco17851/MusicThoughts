package com.marcobarragan.thoughtmusic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ThoughtMusicPagerAdapter extends FragmentPagerAdapter{

    private int NUM_CATEGORIES = 3;
    private Context mContext;

    public ThoughtMusicPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return new Fragment();
    }

    @Override
    public int getCount() {
        return NUM_CATEGORIES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Artists";
            case 1:
                return "Albums";
            case 2:
                return "Genres";
            default:
                return "";
        }
    }
}
