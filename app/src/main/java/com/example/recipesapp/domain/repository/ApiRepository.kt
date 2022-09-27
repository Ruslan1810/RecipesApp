package com.example.recipesapp.domain.repository

import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse

interface ApiRepository {

    suspend fun getCategories(): CategoryResponse

    suspend fun getMealsByCategory(category:String): MealsResponse

    suspend fun getPopularMeals(categoryName:String): MealsResponse

    suspend fun getRandomMeal(): RandomMealResponse

    suspend fun getMealById(id:String): RandomMealResponse

    suspend fun getMealByName(s:String): RandomMealResponse

}