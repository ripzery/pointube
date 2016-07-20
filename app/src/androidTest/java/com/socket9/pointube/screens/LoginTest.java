package com.socket9.pointube.screens;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.socket9.pointube.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginTest() {
        ViewInteraction recyclerView = onView(withId(R.id.recyclerView));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnLogin), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction materialEditText = onView(
                allOf(withId(R.id.metUsername), isDisplayed()));
        materialEditText.perform(replaceText("ripzery@gmail.com"));

        ViewInteraction materialEditText2 = onView(
                allOf(withId(R.id.metPassword), isDisplayed()));
        materialEditText2.perform(replaceText("123"));

        ViewInteraction materialEditText3 = onView(
                allOf(withId(R.id.metPassword), withText("123"), isDisplayed()));
        materialEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.btnLogin), withText("Login"), isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction materialEditText4 = onView(
                allOf(withId(R.id.metPassword), withText("123"), isDisplayed()));
        materialEditText4.perform(replaceText("1234"));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"), isDisplayed()));
        appCompatButton3.perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withId(R.id.tvPoint), withText("Point"), isDisplayed()));
        textView.check(matches(withText("Point")));

    }

}
