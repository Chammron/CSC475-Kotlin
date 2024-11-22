package com.cameron.poundstostonesconverter

import org.junit.Assert.assertEquals
import org.junit.Test

class ConversionUnitTest {

    //Just a simple normal value test.
    @Test
    fun testConversionNormalValues() {
        val pounds = 140.0
        val expectedStones = 10.0
        assertEquals(expectedStones, ConversionUtils.convertPoundsToStones(pounds), 0.01)
    }

    //Seeing how it handles decimal values.
    @Test
    fun testConversionDecimalValues() {
        val pounds = 155.5
        val expectedStones = 11.11
        assertEquals(expectedStones, ConversionUtils.convertPoundsToStones(pounds), 0.01)
    }

    //Making sure 0 comes back 0.
    @Test
    fun testConversionZero() {
        val pounds = 0.0
        val expectedStones = 0.0
        assertEquals(expectedStones, ConversionUtils.convertPoundsToStones(pounds), 0.01)
    }

    //Seeing how it handles negative numbers.(I did make it impossible for the user to enter negative numbers though in my app.)
    @Test
    fun testConversionNegativeValues() {
        val pounds = -140.0
        val expectedStones = -10.0
        assertEquals(expectedStones, ConversionUtils.convertPoundsToStones(pounds), 0.01)
    }

    //Checking that large numbers don't throw it off.
    @Test
    fun testConversionLargeValues() {
        val pounds = 14000.0
        val expectedStones = 1000.0
        assertEquals(expectedStones, ConversionUtils.convertPoundsToStones(pounds), 0.01)
    }
}