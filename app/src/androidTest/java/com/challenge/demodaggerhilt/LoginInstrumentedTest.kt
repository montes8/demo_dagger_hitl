package com.challenge.demodaggerhilt

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.challenge.demodaggerhilt.ui.splash.LoginActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun listGoesOverTheFold() {
        onView(withId(R.id.editEmail)).perform(typeText("gabbi0812@gmail.com"))
        onView(withId(R.id.editPassword)).perform(typeText("gabbi@1"))
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText("gabbi0812@gmail.com")).check(matches(isDisplayed()))
        onView(withText("gabbi@1")).check(matches(isDisplayed()))
    }
}