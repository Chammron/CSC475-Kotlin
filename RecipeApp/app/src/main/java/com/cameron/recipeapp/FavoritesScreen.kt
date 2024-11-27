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
    //Filter.
    val favoriteRecipes: List<Recipe> = RecipeData.recipes.filter { it.isFavorite }

    if (favoriteRecipes.isEmpty()) {
        //Message if no recipes are favorites.
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No recipes to show. Please add some recipes to your favorites.")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            //Card spacing.
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 80.dp
            ),
            //Space between cards.
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //Display each favorite recipe as a RecipeCard.
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