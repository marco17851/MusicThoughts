package com.marcobarragan.thoughtmusic.main;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.MainActivity;
import com.marcobarragan.thoughtmusic.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    @Test
    public void shouldNotBeNull(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        assertNotNull(mainActivity);
    }

    @Test
    public void shouldContainViewPagerAndPagerTitleStripWithHighLevelCategories(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.categories_view_pager);
        PagerTitleStrip titleStrip = (PagerTitleStrip) mainActivity.findViewById(R.id.categories_title_strip);

        assertNotNull(viewPager);
        assertNotNull(titleStrip);

        assertEquals("Artists", viewPager.getAdapter().getPageTitle(0));
        assertEquals("Albums", viewPager.getAdapter().getPageTitle(1));
        assertEquals("Genres", viewPager.getAdapter().getPageTitle(2));
    }

}