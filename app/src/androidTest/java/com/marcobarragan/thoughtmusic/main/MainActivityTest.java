package com.marcobarragan.thoughtmusic.main;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.view.View;

import com.marcobarragan.thoughtmusic.R;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tools.fastlane.screengrab.locale.LocaleTestRule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final long TIMEOUT = 3000;
    private static final String PACKAGE_NAME = "com.marcobarragan.thoughtmusic";
    UiDevice device;
    
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @ClassRule
    public static final LocaleTestRule localeTestRule = new LocaleTestRule();
    
    @Before
    public void setup(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void shouldAllowUsersToNavigateThrough(){
        Matcher<View> categoryStrip = ViewMatchers.withId(R.id.categories_title_strip);
        onView(categoryStrip).perform(swipeLeft());
        onView(categoryStrip).perform(swipeLeft());
        onView(categoryStrip).perform(swipeRight());
        onView(categoryStrip).perform(swipeRight());

        onView(withId(R.id.artists_error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.artists_recycler_view)).check(matches(isDisplayed()));

        Matcher<View> artistView = allOf(withId(R.id.item_list_title), isDisplayed(), withText("Blackpink"));
        onView(artistView).perform(click());

        Matcher<View> songView = allOf(withId(R.id.song_title), isDisplayed());
        onView(songView).perform(click());

        Matcher<View> controlButton = withId(R.id.play_pause_button);
        onView(controlButton).check(matches(isDisplayed()));

        device.openNotification();
        device.wait(Until.hasObject(By.text("Boombayah")), TIMEOUT);
        UiObject2 title = device.findObject(By.text("Boombayah"));
        assertEquals("Boombayah", title.getText());

        device.pressBack();
        pressBack();
        pressBack();

        onView(categoryStrip).perform(swipeLeft());
        onView(categoryStrip).perform(swipeLeft());

        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.genres_recycler_view)).check(matches(isDisplayed()));

        Matcher<View> genreView = allOf(withId(R.id.genre_list_title), isDisplayed(), withText("Pop"));
        onView(genreView).perform(click());

        Matcher<View> songsView = withId(R.id.songs_recycler_view);
        onView(songsView).perform(RecyclerViewActions.scrollToPosition(4));

        songView = allOf(withId(R.id.song_title), withText("Uptown Funk"));
        onView(songView).perform(click());

        onView(controlButton).check(matches(isDisplayed()));

        device.openNotification();
        device.wait(Until.hasObject(By.text("Uptown Funk")), TIMEOUT);
        title = device.findObject(By.text("Uptown Funk"));
        assertEquals("Uptown Funk", title.getText());
    }

}