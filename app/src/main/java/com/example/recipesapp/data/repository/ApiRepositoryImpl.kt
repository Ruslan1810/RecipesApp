package com.example.recipesapp.data.repository

import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.data.network.retrofit.ApiFactory
import com.example.recipesapp.domain.repository.ApiRepository

object ApiRepositoryImpl: ApiRepository {

    private val apiservice = ApiFactory.apiService

    override suspend fun getCategories(): CategoryResponse {
       return apiservice.getCategories()
    }

    override suspend fun getMealsByCategory(category: String): MealsResponse {
        return apiservice.getMealsByCategory(category)
    }

    override suspend fun getPopularMeals(categoryName: String): MealsResponse {
        return apiservice.getPopularItems(categoryName)
    }

    override suspend fun getRandomMeal(): RandomMealResponse {
        return apiservice.getRandomMeal()
    }

    override suspend fun getMealById(id: String): RandomMealResponse {
        return apiservice.getMealById(id)
    }

    override suspend fun getMealByName(s: String): RandomMealResponse {
        return apiservice.getMealByName(s)
    }

}