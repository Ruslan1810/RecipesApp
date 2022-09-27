package com.example.recipesapp.data.network.retrofit

import androidx.lifecycle.LiveData
import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php?")
    suspend fun getMealsByCategory(@Query("i") category:String): MealsResponse

    @GET("filter.php?")
    suspend fun getPopularItems(@Query("c") categoryName:String): MealsResponse

    @GET ("random.php")
    suspend fun getRandomMeal(): RandomMealResponse

    @GET("lookup.php?")
    suspend fun getMealById(@Query("i") id:String): RandomMealResponse

    @GET("search.php?")
    suspend fun getMealByName(@Query("s") s:String): RandomMealResponse
}