package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.domain.repository.ApiRepository
import javax.inject.Inject

class GetMealByIdUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend fun getMealById(id:String): RandomMealResponse? {
        val request  = repository.getMealById(id)

        if(request.failed){
            return null
        }
        if(!request.isSuccessFull){
            return null
        }
        return request.body
    }
}