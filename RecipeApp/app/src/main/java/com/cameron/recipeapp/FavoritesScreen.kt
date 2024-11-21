package com.cameron.recipeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen(
    onRecipeClick: (Recipe) -> Unit,
    onFavoriteToggle: (Recipe) -> Unit
) {
    val favoriteRecipes = RecipeData.recipes.filter { it.isFavorite }

    if (favoriteRecipes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No recipes to show. Please add some recipes to your favorites.")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(favoriteRecipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onRecipeClick = { onRecipeClick(recipe) },
                    onFavoriteToggle = { onFavoriteToggle(recipe) }
                )
            }
        }
    }
}