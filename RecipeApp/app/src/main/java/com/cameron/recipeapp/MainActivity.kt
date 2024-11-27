package com.cameron.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

//Inits app components and preferences.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Handling saved preferences.
        val preferencesManager = PreferencesManager(this)
        //Load list of favorite recipes.
        val favoriteIds = preferencesManager.getFavorites()

        //Update favorites based on saved IDs.
        RecipeData.recipes.replaceAll { recipe: Recipe ->
            recipe.copy(isFavorite = favoriteIds.contains(recipe.id))
        }
        setContent {
            RecipeApp(preferencesManager = preferencesManager)
        }
    }
}

//App layout and nav.
@Composable
fun RecipeApp(preferencesManager: PreferencesManager) {
    val navController = rememberNavController()

    //Use scaffold to organize the app's UI, with a bottom navigation bar.
    Scaffold(
        //Set the bottom navigation bar.
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        //Managing transitions.
        NavigationGraph(
            navController = navController,
            preferencesManager = preferencesManager
        )
    }
}