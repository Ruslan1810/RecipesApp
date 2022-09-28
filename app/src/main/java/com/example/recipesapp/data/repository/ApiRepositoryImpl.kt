package com.example.recipesapp.data.repository

import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.data.network.retrofit.ApiService
import com.example.recipesapp.data.network.retrofit.NetworkResponse
import com.example.recipesapp.domain.repository.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiservice: ApiService
) : ApiRepository {

    override suspend fun getCategories(): NetworkResponse<CategoryResponse> {
        return safeApiCall { apiservice.getCategories() }
    }

    override suspend fun getMealsByCategory(category: String): NetworkResponse<MealsResponse> {
        return safeApiCall { apiservice.getMealsByCategory(category) }
    }

    override suspend fun getPopularMeals(categoryName: String): NetworkResponse<MealsResponse> {
        return safeApiCall { apiservice.getPopularItems(categoryName) }
    }

    override suspend fun getRandomMeal(): NetworkResponse<RandomMealResponse> {
        return safeApiCall { apiservice.getRandomMeal() }
    }

    override suspend fun getMealById(id: String): NetworkResponse<RandomMealResponse> {
        return safeApiCall { apiservice.getMealById(id) }
    }

    override suspend fun getMealByName(s: String): NetworkResponse<RandomMealResponse> {
        return safeApiCall { apiservice.getMealByName(s) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): NetworkResponse<T> {
        return try {
            NetworkResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            NetworkResponse.failure<T>(e)
        }
    }

}