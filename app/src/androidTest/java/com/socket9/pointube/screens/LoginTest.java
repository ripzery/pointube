package com.socket9.pointube.screens;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.socket9.pointube.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginTest() {
        ViewInteraction imageView = onView(
allOf(withId(R.id.civLogo), isDisplayed()));
        imageView.check(matches(isDisplayed()));
        
        ViewInteraction imageView2 = onView(
allOf(withId(R.id.civLogo), isDisplayed()));
        imageView2.check(matches(isDisplayed()));
        
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
        
        ViewInteraction textView = onView(
allOf(withId(R.id.tvPoint), withText("Point"), isDisplayed()));
        textView.check(matches(withText("Point")));
        
        }

    }
