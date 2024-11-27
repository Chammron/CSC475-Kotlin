package com.cameron.recipeapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search

//Defines the structure of a screen in the navigation system.
sealed class Screen(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Search : Screen("search", "Search", Icons.Filled.Search)
    object Favorites : Screen("favorites", "Favorites", Icons.Filled.Favorite)
}