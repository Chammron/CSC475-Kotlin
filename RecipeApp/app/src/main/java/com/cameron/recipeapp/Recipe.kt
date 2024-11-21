package com.cameron.recipeapp

data class Recipe(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val ingredients: List<String>,
    val instructions: String,
    var isFavorite: Boolean = false // Added isFavorite property
)