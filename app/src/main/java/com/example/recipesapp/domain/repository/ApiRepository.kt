package com.example.recipesapp.domain.repository

import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.data.network.retrofit.NetworkResponse

interface ApiRepository {

    suspend fun getCategories(): NetworkResponse<CategoryResponse>

    suspend fun getMealsByCategory(category:String): NetworkResponse<MealsResponse>

    suspend fun getPopularMeals(categoryName:String): NetworkResponse<MealsResponse>

    suspend fun getRandomMeal(): NetworkResponse<RandomMealResponse>

    suspend fun getMealById(id:String): NetworkResponse<RandomMealResponse>

    suspend fun getMealByName(s:String): NetworkResponse<RandomMealResponse>

}