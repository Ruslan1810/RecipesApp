package com.example.recipesapp.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.MealsResponse
import com.example.recipesapp.domain.usecases.api.GetMealsByCategoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealsByCategoryFragmentVM @Inject constructor(
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel()  {

    private var mealsByCategory = MutableLiveData<MealsResponse>()

    fun getMealsByCategory(categoryName:String) {
        viewModelScope.launch {
            mealsByCategory.value = getMealsByCategoryUseCase.getMealsByCategory(categoryName)
        }
    }

    fun getMealsByCategoryLiveData(): LiveData<MealsResponse> {
        return mealsByCategory
    }
}