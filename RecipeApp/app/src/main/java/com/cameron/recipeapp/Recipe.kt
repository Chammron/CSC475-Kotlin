package com.cameron.recipeapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class Recipe(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val ingredients: List<String>,
    val instructions: String,
    var isFavorite: Boolean = false
) {
    //Real-time state for favorite toggle; not serialized.
    @delegate:Transient
    var isFavoriteState by mutableStateOf(isFavorite)
}