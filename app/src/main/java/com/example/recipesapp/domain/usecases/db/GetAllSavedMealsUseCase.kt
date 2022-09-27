package com.example.recipesapp.domain.usecases.db

import androidx.lifecycle.LiveData
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.domain.repository.DbRepository

class GetAllSavedMealsUseCase(private val repository: DbRepository) {
    fun getAllSavedMeals(): LiveData<List<MealDB>> {
        return repository.getAllSavedMeals()
    }
}