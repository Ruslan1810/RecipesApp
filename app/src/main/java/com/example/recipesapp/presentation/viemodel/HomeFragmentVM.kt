package com.example.recipesapp.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.domain.usecases.api.GetCategoriesUseCase
import com.example.recipesapp.domain.usecases.api.GetPopularMealsUseCase
import com.example.recipesapp.domain.usecases.api.GetRandomMealUseCase
import com.example.recipesapp.domain.usecases.db.GetAllSavedMealsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragmentVM @Inject constructor(
    private val getAllSavedMealsUseCase: GetAllSavedMealsUseCase,
    private val getPopularUseCase: GetPopularMealsUseCase,
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

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


    init {
        getPopularMeals()
        getRandomMeal()
        getCategories()
        getFavoritesMeals()
    }

}