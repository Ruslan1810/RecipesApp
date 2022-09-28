package com.example.recipesapp.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.CategoryResponse
import com.example.recipesapp.domain.usecases.api.GetCategoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllCategoriesFragmentVM @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var categoriesLiveData = MutableLiveData<CategoryResponse>()

    private fun getCategories() {
        viewModelScope.launch {
            categoriesLiveData.value = getCategoriesUseCase.getCategories()
        }
    }

    fun getCategoriesLiveData(): LiveData<CategoryResponse> {
        return categoriesLiveData
    }

    init {
        getCategories()
    }
}