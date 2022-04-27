package com.yeferic.nubankshorter.presentation.linksshortener.screens;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.not;

import com.yeferic.nubankshorter.R;
import com.yeferic.nubankshorter.presentation.linkshortener.screens.LinkShortenerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LinkShortenerViewTest {
    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule<>(LinkShortenerView.class);

    @Test
    public void testProgressBarIsNotVisibleWhenClickShortenerButtonAndInputIsEmpty(){
        onView(withId(R.id.btnShorten)).perform(click());
        onView(withId(R.id.pbSearch)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testProgressBarIsVisibleWhenClickShortenerButtonAndInputHasText(){
        onView(withId(R.id.txtURLShorten)).perform(typeText("www.nu.com.co"));
        onView(withId(R.id.btnShorten)).perform(click());
        onView(withId(R.id.pbSearch)).check(matches(isDisplayed()));
    }

}
