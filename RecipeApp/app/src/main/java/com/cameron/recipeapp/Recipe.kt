package com.cameron.recipeapp

data class Recipe(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val ingredients: List<String>,
    val instructions: String,
    val isFavorite: Boolean = false
)