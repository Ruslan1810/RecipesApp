package com.example.recipesapp.domain.usecases.db

import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.domain.repository.DbRepository

class InsertFavoriteUseCase(private val repository: DbRepository) {
    suspend fun insertFavorite(meal: MealDB) {
        return repository.insertFavorite(meal)
    }
}