package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.domain.repository.ApiRepository

class GetCategoriesUseCase(private val repository: ApiRepository) {
    suspend fun getCategories(): CategoryResponse {
        return repository.getCategories()
    }
}