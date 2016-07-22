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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterFormTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void registerFormTest2() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.tvSignUp), withText("Sign Up"),
                        withParent(withId(R.id.layoutNewUser)),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction materialEditText = onView(
                allOf(withId(R.id.metEmail),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText.perform(scrollTo(), click());

        ViewInteraction materialEditText2 = onView(
                allOf(withId(R.id.metEmail),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText2.perform(scrollTo(), replaceText("r@a.a"));

        ViewInteraction materialEditText3 = onView(
                allOf(withId(R.id.metPassword),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText3.perform(scrollTo(), replaceText("a"));

        ViewInteraction materialEditText4 = onView(
                allOf(withId(R.id.metPassword), withText("a"),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText4.perform(pressImeActionButton());

        ViewInteraction materialEditText5 = onView(
                allOf(withId(R.id.metPassword), withText("a"),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText5.perform(scrollTo(), replaceText("aaaa"));

        ViewInteraction materialEditText6 = onView(
                allOf(withId(R.id.metPassword), withText("aaaa"),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText6.perform(pressImeActionButton());

        ViewInteraction materialEditText7 = onView(
                allOf(withId(R.id.metRepeatPassword),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText7.perform(scrollTo(), replaceText("aaaa"));

        ViewInteraction materialEditText8 = onView(
                allOf(withId(R.id.metRepeatPassword), withText("aaaa"),
                        withParent(withId(R.id.layoutAccount))));
        materialEditText8.perform(pressImeActionButton());

        ViewInteraction materialEditText9 = onView(
                allOf(withId(R.id.metFirstName),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText9.perform(scrollTo(), replaceText("a"));

        ViewInteraction materialEditText10 = onView(
                allOf(withId(R.id.metFirstName), withText("a"),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText10.perform(pressImeActionButton());

        ViewInteraction materialEditText11 = onView(
                allOf(withId(R.id.metLastName),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText11.perform(scrollTo(), replaceText("a"));

        ViewInteraction materialEditText12 = onView(
                allOf(withId(R.id.metLastName), withText("a"),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText12.perform(pressImeActionButton());

        ViewInteraction materialEditText13 = onView(
                allOf(withId(R.id.metFirstNameTH),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText13.perform(scrollTo(), replaceText("a"));

        ViewInteraction materialEditText14 = onView(
                allOf(withId(R.id.metFirstNameTH), withText("a"),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText14.perform(pressImeActionButton());

        ViewInteraction materialEditText15 = onView(
                allOf(withId(R.id.metLastNameTH),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText15.perform(scrollTo(), replaceText("a"));

        ViewInteraction materialEditText16 = onView(
                allOf(withId(R.id.metLastNameTH), withText("a"),
                        withParent(withId(R.id.layoutProfile))));
        materialEditText16.perform(pressImeActionButton());

        ViewInteraction materialEditText17 = onView(
                allOf(withId(R.id.metCitizenID),
                        withParent(allOf(withId(R.id.layoutCitizenID),
                                withParent(withId(R.id.layoutVerifyCitizen)))),
                        isDisplayed()));
        materialEditText17.perform(replaceText("1234567890123"));

        sleepOne();

        ViewInteraction materialEditText18 = onView(
                allOf(withId(R.id.metCitizenID), withText("1234567890123"),
                        withParent(allOf(withId(R.id.layoutCitizenID),
                                withParent(withId(R.id.layoutVerifyCitizen)))),
                        isDisplayed()));
        materialEditText18.perform(pressImeActionButton());

        sleepOne();

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.tvDob),
                        withParent(withId(R.id.layoutProfile))));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        withParent(withId(R.id.done_background)),
                        isDisplayed()));
        appCompatButton.perform(click());

        sleepOne();

//        ViewInteraction frameLayout = onView(
//                allOf(withId(R.id.layoutRight), isDisplayed()));
//        frameLayout.perform(click());
//
//        ViewInteraction frameLayout2 = onView(
//                allOf(withId(R.id.layoutLeft), isDisplayed()));
//        frameLayout2.perform(click());

        ViewInteraction materialEditText20 = onView(
                allOf(withId(R.id.metCitizenID), withText("1234567890123"),
                        withParent(allOf(withId(R.id.layoutCitizenID),
                                withParent(withId(R.id.layoutVerifyCitizen)))),
                        isDisplayed()));
        materialEditText20.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnNext), withText("Next")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction editText = onView(
                allOf(withClassName(is("android.widget.EditText")),
                        withParent(withId(R.id.telInput)),
                        isDisplayed()));
        editText.perform(replaceText("0871231581"));

        ViewInteraction nextButton = onView(
                allOf(withId(R.id.btnNext), withText("Next"), isDisplayed()));

        nextButton.check(matches(withText("Next")));
    }

    private void sleepOne() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
