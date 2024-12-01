package com.cameron.recipeapp

import org.junit.Assert.assertEquals
import org.junit.Test

class SearchScreenUnitTest {

    private val mockRecipes = listOf(
        Recipe(id = 1, name = "Pancakes", imageResId = 0, ingredients = listOf(), instructions = ""),
        Recipe(id = 2, name = "Scrambled Eggs", imageResId = 0, ingredients = listOf(), instructions = ""),
        Recipe(id = 3, name = "Chocolate Cake", imageResId = 0, ingredients = listOf(), instructions = "")
    )

    @Test
    fun `search with query filters recipes correctly`() {
        val query = "Pancakes"
        val filteredRecipes = mockRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
        //Check only pancakes is returned.
        assertEquals(1, filteredRecipes.size)
        assertEquals("Pancakes", filteredRecipes[0].name)
    }

    @Test
    fun `search with empty query returns all recipes`() {
        val query = ""
        val filteredRecipes = mockRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
        //Checks all recipes are returned.
        assertEquals(mockRecipes.size, filteredRecipes.size)
    }

    @Test
    fun `search with no matching query returns empty list`() {
        val query = "Pizza"
        val filteredRecipes = mockRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
        //Check no recipes are returned.
        assertEquals(0, filteredRecipes.size)
    }
}