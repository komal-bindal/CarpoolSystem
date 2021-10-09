package com.example.carpoolsystem.screens

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.carpoolsystem.R
import org.junit.Before
import org.junit.Test


class SignupScreen2PhoneNumberTest {
    private lateinit var scenario: ActivityScenario<SignupScreen2PhoneNumber>
    var activityRule = ActivityTestRule(SignupScreen2PhoneNumber::class.java)


    @Before
    fun setUp() {
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testGetOtpButtonClickedScenario() {
        val phoneNumber = "1234567890"
        Espresso.onView(withId(R.id.editTextPhoneNumber))
            .perform(ViewActions.typeText(phoneNumber))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.buttonGetOtp)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.mobile)).check(
            matches(
                withText(
                    "+91 $phoneNumber"
                )
            )
        )
    }
}