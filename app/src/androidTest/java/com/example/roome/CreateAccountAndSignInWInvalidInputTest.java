package com.example.roome;


import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAccountAndSignInWInvalidInputTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createAccountAndSignInWInvalidInputTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Pair<String, String>> testInputs = new ArrayList<Pair<String, String>>();
        testInputs.add(new Pair<>("", ""));
        testInputs.add(new Pair<>(" ", ""));
        testInputs.add(new Pair<>("", " "));
        testInputs.add(new Pair<>(" ", " "));
        testInputs.add(new Pair<>("123", "456"));
        testInputs.add(new Pair<>("123", "abc"));
        testInputs.add(new Pair<>("abc", "456"));
        testInputs.add(new Pair<>("abc", "$7_"));


        for (int i = 0; i < testInputs.size(); i++)
        {
            String first = testInputs.get(i).first;
            String last  = testInputs.get(i).second;

            ViewInteraction appCompatEditText = onView(
                    allOf(withId(R.id.et_first_name_without_google),
                            childAtPosition(
                                    allOf(withId(R.id.cl_sign_in),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    2),
                            isDisplayed()));
            appCompatEditText.perform(click());


            ViewInteraction appCompatEditText2 = onView(
                    allOf(withId(R.id.et_first_name_without_google),
                            childAtPosition(
                                    allOf(withId(R.id.cl_sign_in),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    2),
                            isDisplayed()));
            appCompatEditText2.perform(replaceText(first), closeSoftKeyboard());

            ViewInteraction appCompatEditText3 = onView(
                    allOf(withId(R.id.et_last_name_without_google),
                            childAtPosition(
                                    allOf(withId(R.id.cl_sign_in),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    3),
                            isDisplayed()));
            appCompatEditText3.perform(replaceText(last), closeSoftKeyboard());

            ViewInteraction appCompatImageView = onView(
                    allOf(withId(R.id.btn_sign_without_google),
                            childAtPosition(
                                    allOf(withId(R.id.cl_sign_in),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    6),
                            isDisplayed()));
            appCompatImageView.perform(click());

//        ViewInteraction frameLayout = onView(
//                allOf(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class), isDisplayed()));
//        frameLayout.check(matches(isDisplayed()));
//        ViewInteraction frameLayout = onView(
//                allOf(childAtPosition(
//                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
//                        0),
//                        withEffectiveVisibility(VISIBLE),
//                        isDisplayed()));
//        frameLayout.check(matches(isDisplayed()));

            // if all goes well, on each input (which is invalid) a popup dialog with a msg will
            // appear, on which we will press the "ok" button
            ViewInteraction appCompatButton = onView(
                    allOf(withId(android.R.id.button1), withText("ok"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.widget.ScrollView")),
                                            0),
                                    3)));
            appCompatButton.perform(scrollTo(), click());
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
