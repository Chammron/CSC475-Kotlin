package com.cameron.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SharedPreferences Manager
        val preferencesManager = PreferencesManager(this)

        // Load favorites from SharedPreferences
        val favoriteIds = preferencesManager.getFavorites()
        RecipeData.recipes.replaceAll { recipe ->
            recipe.copy(isFavorite = favoriteIds.contains(recipe.id))
        }

        setContent {
            RecipeApp(preferencesManager = preferencesManager)
        }
    }
}

@Composable
fun RecipeApp(preferencesManager: PreferencesManager) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationGraph(
            navController = navController,
            preferencesManager = preferencesManager
        )
    }
}