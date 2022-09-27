package com.example.recipesapp.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.data.repository.ApiRepositoryImpl
import com.example.recipesapp.domain.usecases.api.GetMealsByCategoryUseCase
import kotlinx.coroutines.launch

class MealsByCategoryFragmentVM : ViewModel()  {

    private var mealsByCategory = MutableLiveData<MealsResponse>()

    private val getMealsByCategoryUseCase = GetMealsByCategoryUseCase(ApiRepositoryImpl)

    fun getMealsByCategory(categoryName:String) {
        viewModelScope.launch {
            mealsByCategory.value = getMealsByCategoryUseCase.getMealsByCategory(categoryName)
        }
    }

    fun getMealsByCategoryLiveData(): LiveData<MealsResponse> {
        return mealsByCategory
    }
}