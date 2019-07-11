package com.freenow.android_demo.condition_watcher;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class WaitTillObjectVisible {
    public static ViewInteraction waitForElementIsDisplayed(final ViewInteraction interaction,
                                                            final int timeout) throws Exception {

        ConditionWatcher.setTimeoutLimit(timeout);
        ConditionWatcher.waitForCondition(new Instruction() {
            @Override
            public String getDescription() {
                return "waitForElementIsDisplayed";
            }

            @Override
            public boolean checkCondition() {
                try {
                    interaction.check(matches(isDisplayed()));
                    return true;
                } catch (NoMatchingViewException ex) {
                    return false;
                }


            }
        });
        return interaction;
    }
}
