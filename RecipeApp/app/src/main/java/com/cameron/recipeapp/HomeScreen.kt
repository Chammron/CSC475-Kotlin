package com.cameron.recipeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onRecipeClick: (Recipe) -> Unit,
    onFavoriteToggle: (Recipe) -> Unit
) {
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
        //Populate column with recipes.
        items(items = RecipeData.recipes) { recipe: Recipe ->
            //Display each recipe using a RecipeCard.
            RecipeCard(
                recipe = recipe,
                onRecipeClick = { onRecipeClick(recipe) },
                onFavoriteToggle = { onFavoriteToggle(recipe) }
            )
        }
    }
}