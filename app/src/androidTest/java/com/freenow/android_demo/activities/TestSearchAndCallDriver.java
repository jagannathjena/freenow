package com.freenow.android_demo.activities;

import android.Manifest;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.freenow.android_demo.R;
import com.freenow.android_demo.free_now_utility.DataUtil;
import com.freenow.android_demo.free_now_utility.FreeNowUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class TestSearchAndCallDriver {

    FreeNowUtils util= new FreeNowUtils();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule=
            new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule=
            GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);
    @Before
    public void verify_search_field(){
        /* Login to application */
        try {
            util.verify_login();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /* Check if the search field exists */
      try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           util.verify_search_box_existance();
    }

    @Test
    public void verify_search_name_and_call() throws InterruptedException {
        try {
            Thread.sleep(3000);
            util.searchBox.perform(ViewActions.click());
            /* Enter the name to search for */
            util.searchBox.perform(ViewActions.typeText(DataUtil.NAME_TO_BE_SEARCHED));
            closeSoftKeyboard();
            /* Select the desired from the result */
            ViewInteraction nameToBeSelected = onView(withText(DataUtil.NAME_TO_BE_SELECTED));
            nameToBeSelected.inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(ViewActions.click());
            ViewInteraction driverName = onView(withId(R.id.textViewDriverName));
            /* check if the landing-page has the searched name */
            driverName.check(matches(isDisplayed()));
            driverName.check(matches(withText(DataUtil.NAME_TO_BE_SELECTED)));
            /* check for the call-button */
            util.callDriverButton.check(matches(isDisplayed()));
            util.callDriverButton.check(matches(isClickable()));
            /* Press the call button to make a call */
            util.callDriverButton.perform(ViewActions.click());
        }
        catch (NoMatchingViewException exception){
            System.out.println("Exception found: "+exception.toString());
            Assert.fail();
        }
        catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }


    }

//    @After
//    public void logout_app(){
//
//        util.verify_logout();
//    }

}
