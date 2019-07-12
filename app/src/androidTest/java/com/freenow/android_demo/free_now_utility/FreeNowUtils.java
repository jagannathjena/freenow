package com.freenow.android_demo.free_now_utility;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import com.freenow.android_demo.R;
import com.freenow.android_demo.condition_watcher.WaitTillObjectVisible;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftAlignedWith;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class FreeNowUtils {

    public  ViewInteraction userName= onView(withId(R.id.edt_username));
    public  ViewInteraction passWord= onView(withId(R.id.edt_password));
    public ViewInteraction loginButton= onView(withId(R.id.btn_login));
    public ViewInteraction searchBox= onView(withId(R.id.textSearch));
    public ViewInteraction callDriverButton = onView(withId(R.id.fab));
    public ViewInteraction menuButton= onView(withId(R.id.drawer_layout));

    public  void verify_login(String username, String password) {
        userName.perform(ViewActions.typeText(username));
        passWord.perform(ViewActions.typeText(password));
    }

    public void verify_login_button_click_with_valid_login_creds(){
        loginButton.check(matches(isDisplayed()));
        loginButton.check(matches(isEnabled()));
        loginButton.perform(ViewActions.click());
    }

    public void verify_login_button_click_with_invalid_login_creds() throws Exception {
        loginButton.check(matches(isDisplayed()));
//        loginButton.check(matches(not(isEnabled())));
//        loginButton.check(matches(not(isClickable())));
        loginButton.perform(ViewActions.click());
//        Thread.sleep(1000);
        WaitTillObjectVisible.waitForElementIsDisplayed(onView(withText("Login failed")), 0);
    }

    public void verify_left_alignment_of_username_password(){
//        onView(withId(R.id.edt_username)).check(isLeftAlignedWith(withId(R.id.edt_password)));
        userName.check(isLeftAlignedWith(withId(R.id.edt_password)));
        passWord.check(isLeftAlignedWith(withId(R.id.btn_login)));
    }

    public void verify_search_box_existance(){
        searchBox.check(matches(isDisplayed()));
    }

    public void verify_search_and_select_name(String nameToBeSearched, String nameToBeSelcted) throws InterruptedException {
        searchBox.perform(ViewActions.typeText(nameToBeSearched));
        Thread.sleep(4000);
//        onView(withText(nameToBeSelcted)).inRoot(withDecorView(not(mActivityTestRule.getA)));
    }

    public void verify_login() throws InterruptedException {
        verify_login(DataUtil.VALID_USER_NAME, DataUtil.VALID_PASSWORD);
        closeSoftKeyboard();
        verify_login_button_click_with_valid_login_creds();
        Thread.sleep(2000);
        verify_search_box_existance();
    }
    public void verify_logout(){

        ViewInteraction menuBtn = onView(
                allOf(withContentDescription("Open navigation drawer")));

        menuBtn.check(matches(isDisplayed()));
        menuBtn.perform(click());

        onView(ViewMatchers.withId(R.id.design_menu_item_text)).perform(ViewActions.click());

    }
}
