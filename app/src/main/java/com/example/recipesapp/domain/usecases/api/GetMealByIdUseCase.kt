package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.domain.repository.ApiRepository
import javax.inject.Inject

class GetMealByIdUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend fun getMealById(id:String): RandomMealResponse {
        return repository.getMealById(id)
    }
}