package com.cameron.recipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson

@Composable
fun NavigationGraph(
    navController: NavHostController,
    preferencesManager: PreferencesManager
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onRecipeClick = { recipe ->
                    val recipeJson = Gson().toJson(recipe)
                    navController.navigate("recipeDetail/$recipeJson")
                },
                onFavoriteToggle = { recipe ->
                    // Update the favorite status by copying the object
                    val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
                    val index = RecipeData.recipes.indexOf(recipe)
                    if (index != -1) {
                        RecipeData.recipes[index] = updatedRecipe
                    }
                    preferencesManager.saveFavorites(
                        RecipeData.recipes.filter { it.isFavorite }.map { it.id }
                    )
                }
            )
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onRecipeClick = { recipe ->
                    val recipeJson = Gson().toJson(recipe)
                    navController.navigate("recipeDetail/$recipeJson")
                },
                onFavoriteToggle = { recipe ->
                    // Update the favorite status by copying the object
                    val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
                    val index = RecipeData.recipes.indexOf(recipe)
                    if (index != -1) {
                        RecipeData.recipes[index] = updatedRecipe
                    }
                    preferencesManager.saveFavorites(
                        RecipeData.recipes.filter { it.isFavorite }.map { it.id }
                    )
                }
            )
        }
        composable(
            route = "recipeDetail/{recipeJson}",
            arguments = listOf(navArgument("recipeJson") { nullable = true })
        ) { backStackEntry ->
            val recipeJson = backStackEntry.arguments?.getString("recipeJson")
            val recipe = Gson().fromJson(recipeJson, Recipe::class.java)
            RecipeDetailScreen(recipe, navController)
        }
    }
}