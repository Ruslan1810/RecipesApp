package com.example.recipesapp.domain.usecases.db

import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.domain.repository.DbRepository
import javax.inject.Inject

class DeleteMealUseCase@Inject constructor(private val repository: DbRepository) {
    suspend fun deleteMeal(meal: MealDB){
        return repository.deleteMeal(meal)
    }
}