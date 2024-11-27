package com.cameron.recipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson

//Manages all screen transitions.
@Composable
fun NavigationGraph(
    navController: NavHostController,
    preferencesManager: PreferencesManager
) {
    //Navigation host starts at home screen.
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        //Defines home screen route.
        composable(Screen.Home.route) {
            HomeScreen(
                onRecipeClick = { recipe ->
                    //Converts recipe to JSON and navigate to the RecipeDetail.
                    val recipeJson = Gson().toJson(recipe)
                    navController.navigate("recipeDetail/$recipeJson")
                },
                onFavoriteToggle = { recipe ->
                    //Toggle recipe favorite status.
                    val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
                    val index = RecipeData.recipes.indexOf(recipe)
                    if (index != -1) {
                        RecipeData.recipes[index] = updatedRecipe
                    }
                    //Save updated favorites to SharedPreferences.
                    preferencesManager.saveFavorites(
                        RecipeData.recipes.filter { recipe: Recipe -> recipe.isFavorite }
                            .map { recipe: Recipe -> recipe.id }
                    )
                }
            )
        }
        //Defines search screen route.
        composable(Screen.Search.route) {
            SearchScreen(
                onRecipeClick = { recipe ->
                    val recipeJson = Gson().toJson(recipe)
                    navController.navigate("recipeDetail/$recipeJson")
                },
                onFavoriteToggle = { recipe ->
                    val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
                    val index = RecipeData.recipes.indexOf(recipe)
                    if (index != -1) {
                        RecipeData.recipes[index] = updatedRecipe
                    }
                    preferencesManager.saveFavorites(
                        RecipeData.recipes.filter { recipe: Recipe -> recipe.isFavorite }
                            .map { recipe: Recipe -> recipe.id }
                    )
                }
            )
        }
        //Define favorites screen route.
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onRecipeClick = { recipe ->
                    val recipeJson = Gson().toJson(recipe)
                    navController.navigate("recipeDetail/$recipeJson")
                },
                onFavoriteToggle = { recipe ->
                    val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
                    val index = RecipeData.recipes.indexOf(recipe)
                    if (index != -1) {
                        RecipeData.recipes[index] = updatedRecipe
                    }
                    preferencesManager.saveFavorites(
                        RecipeData.recipes.filter { recipe: Recipe -> recipe.isFavorite }
                            .map { recipe: Recipe -> recipe.id }
                    )
                }
            )
        }
        //Define RecipeDetail route with a JSON argument for the recipe.
        composable(
            route = "recipeDetail/{recipeJson}",
            arguments = listOf(navArgument("recipeJson") { nullable = true })
        ) { backStackEntry ->
            val recipeJson = backStackEntry.arguments?.getString("recipeJson")
            val recipe = Gson().fromJson(recipeJson, Recipe::class.java)

            //Null-check to prevent crashes.
            if (recipe != null) {
                RecipeDetailScreen(recipe, navController)
            } else {
                navController.popBackStack()
            }
        }
    }
}