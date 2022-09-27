package com.example.recipesapp.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.MealDetail
import com.example.recipesapp.data.repository.ApiRepositoryImpl
import com.example.recipesapp.domain.usecases.api.GetMealByNameUseCase
import kotlinx.coroutines.launch

class SearchFragmentVM : ViewModel() {
    private var searchedMealLiveData = MutableLiveData<MealDetail>()
    private val getMealByNameUseCase = GetMealByNameUseCase(ApiRepositoryImpl)

    fun getMealByName(s: String) {
        viewModelScope.launch {
            searchedMealLiveData.value = getMealByNameUseCase.getMealByName(s).meals[0]
        }
    }

    fun observeSearchLiveData(): LiveData<MealDetail> {
        return searchedMealLiveData
    }
}