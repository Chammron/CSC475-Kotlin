package com.cameron.poundstostonesconverter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

//All tests Enter a value into field, click button, and then checks output.
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    //Standard number test.
    @Test
    fun testConversionOutput() {
        onView(withId(R.id.etPounds)).perform(typeText("120"), closeSoftKeyboard())
        onView(withId(R.id.btnConvert)).perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("8.57 stones")))
    }

    //Empty input test.
    @Test
    fun testEmptyInputError() {
        onView(withId(R.id.btnConvert)).perform(click())
        onView(withId(R.id.tvError)).check(matches(withText("Please enter a valid number.")))
    }

    //Making sure negatives work.
    @Test
    fun testNegativeInputConversion() {
        onView(withId(R.id.etPounds)).perform(typeText("-120"), closeSoftKeyboard())
        onView(withId(R.id.btnConvert)).perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("-8.57 stones")))
    }

    //Checking to see how large numbers work.
    @Test
    fun testLargeNumberConversion() {
        onView(withId(R.id.etPounds)).perform(typeText("1000000"), closeSoftKeyboard())
        onView(withId(R.id.btnConvert)).perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("71428.57 stones")))
    }

    //Checking decimal inputs.
    @Test
    fun testDecimalInputConversion() {
        onView(withId(R.id.etPounds)).perform(typeText("123.45"), closeSoftKeyboard())
        onView(withId(R.id.btnConvert)).perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("8.82 stones")))
    }

    //Seeing how letters are handled.
    @Test
    fun testNonNumericInputError() {
        onView(withId(R.id.etPounds)).perform(typeText("abc"), closeSoftKeyboard())
        onView(withId(R.id.btnConvert)).perform(click())
        onView(withId(R.id.tvError)).check(matches(withText("Please enter a valid number.")))
    }
}