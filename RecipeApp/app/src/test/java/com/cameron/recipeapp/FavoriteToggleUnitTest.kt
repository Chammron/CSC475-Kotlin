package com.cameron.recipeapp

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

class FavoriteToggleUnitTest {

    @Test
    fun `test favorite toggle functionality`() {
        val mockPreferencesManager = mock(PreferencesManager::class.java)

        val testRecipes = mutableListOf(
            Recipe(1, "Pancakes", 0, listOf("Flour", "Milk"), "Mix and cook.", false),
            Recipe(2, "Brownies", 0, listOf("Chocolate", "Sugar"), "Bake.", false)
        )

        RecipeData.recipes.clear()
        RecipeData.recipes.addAll(testRecipes)

        assertEquals(false, RecipeData.recipes[0].isFavorite)
        assertEquals(false, RecipeData.recipes[1].isFavorite)

        //Toggle favorite for the first recipe.
        val toggledRecipe1 = testRecipes[0].copy(isFavorite = !testRecipes[0].isFavorite)
        val index1 = RecipeData.recipes.indexOf(testRecipes[0])
        if (index1 != -1) {
            RecipeData.recipes[index1] = toggledRecipe1
        }

        //Verify after toggling.
        assertEquals(true, RecipeData.recipes[0].isFavorite)

        //Toggle for second.
        val toggledRecipe2 = testRecipes[1].copy(isFavorite = !testRecipes[1].isFavorite)
        val index2 = RecipeData.recipes.indexOf(testRecipes[1])
        if (index2 != -1) {
            RecipeData.recipes[index2] = toggledRecipe2
        }

        //Verify.
        assertEquals(true, RecipeData.recipes[1].isFavorite)

        //Try to toggle a fake recipe.
        val nonExistentRecipe = Recipe(999, "Non-existent", 0, listOf(), "", false)
        val indexNonExistent = RecipeData.recipes.indexOf(nonExistentRecipe)
        //Make sure it doesn't exist.
        assertEquals(-1, indexNonExistent)

        //Verify saved favorites.
        val favoriteIds = RecipeData.recipes.filter { it.isFavorite }.map { it.id }
        mockPreferencesManager.saveFavorites(favoriteIds)
        assertEquals(listOf(1, 2), favoriteIds)

        verify(mockPreferencesManager).saveFavorites(favoriteIds)
    }
}