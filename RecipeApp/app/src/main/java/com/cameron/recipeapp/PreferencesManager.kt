package com.cameron.recipeapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("RecipeAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveFavorites(favorites: List<Int>) {
        val json = gson.toJson(favorites)
        sharedPreferences.edit().putString("favorites", json).apply()
    }

    fun getFavorites(): List<Int> {
        val json = sharedPreferences.getString("favorites", null)
        return if (json != null) {
            val type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}