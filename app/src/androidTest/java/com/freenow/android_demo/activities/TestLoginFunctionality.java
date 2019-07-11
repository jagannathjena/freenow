package com.freenow.android_demo.activities;

import android.Manifest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.freenow.android_demo.condition_watcher.WaitTillObjectVisible;
import com.freenow.android_demo.free_now_utility.DataUtil;
import com.freenow.android_demo.free_now_utility.FreeNowUtils;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class TestLoginFunctionality {


    FreeNowUtils util= new FreeNowUtils();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule=
            new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule=
            GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    @Test
    public void test_left_alignment_of_username_password_login_fields(){
        util.verify_left_alignment_of_username_password();
    }

    @Test
    public void verify_valid_username_invalid_password_then_login(){
        util.verify_login(DataUtil.VALID_USER_NAME,DataUtil.INVALID_PASSWORD);
        closeSoftKeyboard();
        try {
            util.verify_login_button_click_with_invalid_login_creds();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void verify_invalid_username_valid_password_then_login(){
        util.verify_login(DataUtil.INVALID_USER_NAME,DataUtil.VALID_PASSWORD);
        closeSoftKeyboard();
        try {
            util.verify_login_button_click_with_invalid_login_creds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void verify_invalid_username_invalid_password_then_login(){
        util.verify_login(DataUtil.INVALID_USER_NAME,DataUtil.INVALID_PASSWORD);
        closeSoftKeyboard();
        try {
            util.verify_login_button_click_with_invalid_login_creds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verify_empty_username_empty_password_then_login()  {
        util.verify_login(DataUtil.EMPTY_USER_NAME, DataUtil.EMPTY_PASSWORD);
        closeSoftKeyboard();
        try {
            util.verify_login_button_click_with_invalid_login_creds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verify_valid_username_valid_password_then_login() throws InterruptedException {
        util.verify_login(DataUtil.VALID_USER_NAME, DataUtil.VALID_PASSWORD);
        closeSoftKeyboard();
        util.verify_login_button_click_with_valid_login_creds();
        Thread.sleep(2000);
        util.verify_search_box_existance();
        util.verify_logout();

    }
}
