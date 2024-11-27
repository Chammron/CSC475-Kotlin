package com.cameron.recipeapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//Saves and retrieves user preferences, i.e. favorite recipes.
class PreferencesManager(context: Context) {
    //Inits SharedPreferences for persistent data storage.
    private val sharedPreferences = context.getSharedPreferences("RecipeAppPrefs", Context.MODE_PRIVATE)
    //Inits Gson for JSON serialization and deserialization.
    private val gson = Gson()

    //Saves the list of favorite recipe as a JSON string.
    fun saveFavorites(favorites: List<Int>) {
        //Converts list to JSON.
        val json = gson.toJson(favorites)
        sharedPreferences.edit().putString("favorites", json).apply()
    }

    //Retrieves list of favorite recipes.
    fun getFavorites(): List<Int> {
        val json = sharedPreferences.getString("favorites", null)
        return if (json != null) {
            //Deserialize the JSON string back into a list of integers.
            val type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(json, type)
        } else {
            //Return empty list if no favorites found.
            emptyList()
        }
    }
}