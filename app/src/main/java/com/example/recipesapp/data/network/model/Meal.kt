package com.example.recipesapp.data.network.model

import androidx.room.Entity

@Entity(tableName = "favorites")
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)