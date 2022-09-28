package com.example.recipesapp.domain.usecases.api

import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.domain.repository.ApiRepository
import javax.inject.Inject

class GetPopularMealsUseCase @Inject constructor(private val repository: ApiRepository){

    suspend fun getPopularMeals(categoryName:String): MealsResponse {
       return repository.getPopularMeals(categoryName)
   }
}