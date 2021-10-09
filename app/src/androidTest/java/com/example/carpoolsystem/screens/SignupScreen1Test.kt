package com.example.carpoolsystem.screens

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.carpoolsystem.R
import org.junit.Before
import org.junit.Test

class SignupScreen1Test {
    private lateinit var scenario: ActivityScenario<SignupScreen1>

    @Before
    fun setup() {
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testSignupFieldsInputScenario() {
        val name = "Komal Bindal"
        val emailId = "komal.bindal_cs19@gla.ac.in"
        val password = "Komal12@"
        Espresso.onView(withId(R.id.editTextName)).perform(ViewActions.typeText(name.toString()))
        Espresso.onView(withId(R.id.editTextEmail))
            .perform(ViewActions.typeText(emailId.toString()))
        Espresso.onView(withId(R.id.editTextPassword))
            .perform(ViewActions.typeText(password.toString()))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.buttonNext)).perform(click())
    }

    @Test
    fun testOtpSignupButtonClickedScenario() {
        val phoneNumber = "1234567890"
        Espresso.onView(withId(R.id.buttonNext2)).perform(click())
        Espresso.onView(withId(R.id.editTextPhoneNumber))
            .perform(ViewActions.typeText(phoneNumber))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.buttonGetOtp)).perform(click())
        Espresso.onView(withId(R.id.mobile)).check(matches(withText("+91 $phoneNumber")))
    }
}