package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.domain.repository.ApiRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(private val repository: ApiRepository){

    suspend fun getMealsByCategory(categoryName:String): MealsResponse? {
        val request  = repository.getMealsByCategory(categoryName)

        if(request.failed){
            return null
        }
        if(!request.isSuccessFull){
            return null
        }
        return request.body
    }
}