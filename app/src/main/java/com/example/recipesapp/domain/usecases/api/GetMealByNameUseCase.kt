package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.domain.repository.ApiRepository
import javax.inject.Inject

class GetMealByNameUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend fun getMealByName(s:String): RandomMealResponse {
        return repository.getMealByName(s)
    }
}