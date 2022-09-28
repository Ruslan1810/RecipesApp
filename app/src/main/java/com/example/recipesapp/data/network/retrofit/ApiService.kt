package com.example.recipesapp.data.network.retrofit

import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("filter.php?")
    suspend fun getMealsByCategory(@Query("i") category:String): Response<MealsResponse>

    @GET("filter.php?")
    suspend fun getPopularItems(@Query("c") categoryName:String): Response<MealsResponse>

    @GET ("random.php")
    suspend fun getRandomMeal(): Response<RandomMealResponse>

    @GET("lookup.php?")
    suspend fun getMealById(@Query("i") id:String): Response<RandomMealResponse>

    @GET("search.php?")
    suspend fun getMealByName(@Query("s") s:String): Response<RandomMealResponse>
}