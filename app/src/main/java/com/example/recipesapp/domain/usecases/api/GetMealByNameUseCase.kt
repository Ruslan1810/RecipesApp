package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.domain.repository.ApiRepository
import javax.inject.Inject

class GetMealByNameUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend fun getMealByName(s:String): RandomMealResponse? {
        val request  = repository.getMealByName(s)

        if(request.failed){
            return null
        }
        if(!request.isSuccessFull){
            return null
        }
        return request.body
    }
}