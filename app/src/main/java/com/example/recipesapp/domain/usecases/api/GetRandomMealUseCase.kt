package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.domain.repository.ApiRepository
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend fun getRandomMeal(): RandomMealResponse? {
        val request  = repository.getRandomMeal()

        if(request.failed){
            return null
        }
        if(!request.isSuccessFull){
            return null
        }
        return request.body
    }
}