package com.example.recipesapp.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.data.repository.ApiRepositoryImpl
import com.example.recipesapp.domain.usecases.api.GetCategoriesUseCase
import kotlinx.coroutines.launch

class AllCategoriesFragmentVM: ViewModel() {

    private val getCategoriesUseCase = GetCategoriesUseCase(ApiRepositoryImpl)
    private var categoriesLiveData = MutableLiveData<CategoryResponse>()

    private fun getCategories() {
        viewModelScope.launch {
            categoriesLiveData.value = getCategoriesUseCase.getCategories()
        }
    }

    fun getCategoriesLiveData(): LiveData<CategoryResponse> {
        return categoriesLiveData
    }

    init{
        getCategories()
    }
}