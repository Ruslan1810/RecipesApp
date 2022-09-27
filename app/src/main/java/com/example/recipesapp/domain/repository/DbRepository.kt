package com.example.recipesapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.recipesapp.data.network.model.MealDB

interface DbRepository {

    suspend fun insertFavorite(meal: MealDB)

    suspend fun updateFavorite(meal: MealDB)

    fun getAllSavedMeals(): LiveData<List<MealDB>>

    suspend fun getMealById(id:String): MealDB

    suspend fun deleteMealById(id:String)

    suspend fun deleteMeal(meal: MealDB)
}