package com.example.carpoolsystem.screens


import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.carpoolsystem.R
import org.junit.Rule
import org.junit.Test


class UsersScreenTest {
    @Rule
    @JvmField
    var intentsRule = IntentsTestRule(UsersScreen::class.java)
    var activityRule = ActivityTestRule(UsersScreen::class.java)


    @Test
    fun testDriverButtonClickInputScenario() {
        Espresso.onView(withId(R.id.buttonDriver)).perform(click())
        val intent = Intent()
        activityRule.launchActivity(intent)
    }

    @Test
    fun testPassengerButtonClickInputScenario() {
        Espresso.onView(withId(R.id.buttonPassenger)).perform(click())
        val intent = Intent()
        activityRule.launchActivity(intent)
    }
}