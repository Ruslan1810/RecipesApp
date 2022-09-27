package com.example.recipesapp.presentation.viemodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.data.repository.ApiRepositoryImpl
import com.example.recipesapp.data.repository.DbRepositoryImpl
import com.example.recipesapp.domain.usecases.api.GetMealByIdUseCase
import com.example.recipesapp.domain.usecases.db.InsertFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMealFragmentVM(application: Application): AndroidViewModel(application) {

    private val dbRepository = DbRepositoryImpl(application)
    private val getMealByIdUseCase = GetMealByIdUseCase(ApiRepositoryImpl)
    private val insertFavoriteUseCase = InsertFavoriteUseCase(dbRepository)
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