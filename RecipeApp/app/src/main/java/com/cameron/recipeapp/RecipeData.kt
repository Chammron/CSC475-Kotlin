package com.cameron.recipeapp

import com.cameron.recipeapp.R

object RecipeData {
    val recipes = mutableListOf(
        Recipe(
            id = 1,
            name = "Spaghetti Carbonara",
            imageResId = R.drawable.ic_launcher_background, // Replace with actual image
            ingredients = listOf(
                "200g Spaghetti",
                "100g Pancetta",
                "2 large eggs",
                "50g Parmesan cheese",
                "Salt and pepper"
            ),
            instructions = "1. Boil the spaghetti.\n2. Cook pancetta.\n3. Mix eggs and Parmesan.\n4. Combine everything and serve."
        ),
        Recipe(
            id = 2,
            name = "Pancakes",
            imageResId = R.drawable.ic_launcher_background, // Replace with actual image
            ingredients = listOf(
                "1 cup all-purpose flour",
                "2 tbsp sugar",
                "1 tsp baking powder",
                "1 egg",
                "1 cup milk",
                "2 tbsp melted butter"
            ),
            instructions = "1. Mix dry ingredients.\n2. Add wet ingredients and mix until smooth.\n3. Cook on a hot griddle until golden brown."
        )
    )
}