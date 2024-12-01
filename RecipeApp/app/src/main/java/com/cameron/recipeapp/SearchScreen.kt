package com.cameron.recipeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

//Displays the search screen.
@Composable
fun SearchScreen(
    onRecipeClick: (Recipe) -> Unit,
    onFavoriteToggle: (Recipe) -> Unit
) {
    //Manages the search query state.
    var query by remember { mutableStateOf(TextFieldValue("")) }

    //Filters recipes.
    val filteredRecipes = RecipeData.recipes.filter { recipe ->
        recipe.name.contains(query.text, ignoreCase = true)
    }

    //Lays out the search bar and the filtered recipe list.
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        //Displays a message if no recipes match the query.
        if (filteredRecipes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Sorry. No recipes found under that name.")
            }
        } else {
            //Displays the filtered recipes in a scrollable list.
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp,
                    bottom = 80.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                //Creates a card for each filtered recipe.
                items(filteredRecipes) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onRecipeClick = { onRecipeClick(recipe) },
                        onFavoriteToggle = { onFavoriteToggle(recipe) }
                    )
                }
            }
        }
    }
}