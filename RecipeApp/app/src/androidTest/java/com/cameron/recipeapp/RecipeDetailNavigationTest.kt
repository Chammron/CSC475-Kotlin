package com.cameron.recipeapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeDetailNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testRecipeDetailNavigation() {
        //Check that card is displayed on the Home screen.
        composeTestRule.onNodeWithText("Pancakes").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pancakes").performClick()

        //Checks screen has correct information for recipe.
        composeTestRule.onNodeWithText("Pancakes").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ingredients:").assertIsDisplayed()
        composeTestRule.onNodeWithText("- 1 egg").assertIsDisplayed()
        composeTestRule.onNodeWithText("Instructions:").assertIsDisplayed()
    }
}