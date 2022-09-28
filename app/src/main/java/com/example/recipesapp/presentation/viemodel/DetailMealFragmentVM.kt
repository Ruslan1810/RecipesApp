package com.example.recipesapp.presentation.viemodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.domain.usecases.api.GetMealByIdUseCase
import com.example.recipesapp.domain.usecases.db.InsertFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailMealFragmentVM @Inject constructor(
    private val insertFavoriteUseCase:InsertFavoriteUseCase,
    private val getMealByIdUseCase: GetMealByIdUseCase
): ViewModel() {

    private var detailMeal = MutableLiveData<RandomMealResponse>()

    fun getMealDetail(id: String) {
        viewModelScope.launch {
            detailMeal.value = getMealByIdUseCase.getMealById(id)
            Log.d("Test", detailMeal.value!!.meals[0].strMeal)
        }
    }
    fun saveMealFavorite(mealDB: MealDB) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteUseCase.insertFavorite(mealDB)
        }
    }

    fun getMealDetailLiveData(): LiveData<RandomMealResponse> {
        return detailMeal
    }

}