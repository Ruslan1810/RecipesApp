package com.example.recipesapp.presentation.viemodel

import android.app.Application
import androidx.lifecycle.*
import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.data.repository.ApiRepositoryImpl
import com.example.recipesapp.data.repository.DbRepositoryImpl
import com.example.recipesapp.domain.usecases.api.GetCategoriesUseCase
import com.example.recipesapp.domain.usecases.api.GetPopularMealsUseCase
import com.example.recipesapp.domain.usecases.api.GetRandomMealUseCase
import com.example.recipesapp.domain.usecases.db.GetAllSavedMealsUseCase
import kotlinx.coroutines.launch

class HomeFragmentVM(application: Application): AndroidViewModel(application){
    private val dbRepository = DbRepositoryImpl(application)

    private val getPopularUseCase = GetPopularMealsUseCase(ApiRepositoryImpl)
    private val getRandomMealUseCase = GetRandomMealUseCase(ApiRepositoryImpl)
    private val getCategoriesUseCase = GetCategoriesUseCase(ApiRepositoryImpl)
    private val getAllSavedMealsUseCase = GetAllSavedMealsUseCase(dbRepository)

    private var popularMealsLiveData = MutableLiveData<MealsResponse>()
    private var randomMealLiveData = MutableLiveData<RandomMealResponse>()
    private var categoriesLiveData = MutableLiveData<CategoryResponse>()
    private var favoritesMealsLiveData: LiveData<List<MealDB>>? = null

    fun getPopularMeals() {
        viewModelScope.launch {
            popularMealsLiveData.value = getPopularUseCase.getPopularMeals("Seafood")
        }
    }

    private fun getRandomMeal() {
        viewModelScope.launch {
            randomMealLiveData.value = getRandomMealUseCase.getRandomMeal()
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoriesLiveData.value = getCategoriesUseCase.getCategories()
        }
    }

    private fun getFavoritesMeals() {
            favoritesMealsLiveData = getAllSavedMealsUseCase.getAllSavedMeals()
    }


    fun getPopularMealsLiveData(): LiveData<MealsResponse> {
        return popularMealsLiveData
    }

    fun getRandomMealLiveData(): LiveData<RandomMealResponse> {
        return randomMealLiveData
    }

    fun getCategoriesLiveData(): LiveData<CategoryResponse> {
        return categoriesLiveData
    }
    fun getFavoritesMealsLiveData(): LiveData<List<MealDB>>? {
        return favoritesMealsLiveData
    }


    init{
        getPopularMeals()
        getRandomMeal()
        getCategories()
        getFavoritesMeals()
    }

}