package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.domain.repository.ApiRepository

class GetMealsByCategoryUseCase (private val repository: ApiRepository){

    suspend fun getMealsByCategory(categoryName:String): MealsResponse {
        return repository.getMealsByCategory(categoryName)
    }

}