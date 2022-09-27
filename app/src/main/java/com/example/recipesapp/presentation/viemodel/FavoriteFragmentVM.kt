package com.example.recipesapp.presentation.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.data.repository.DbRepositoryImpl
import com.example.recipesapp.domain.usecases.db.DeleteMealUseCase
import com.example.recipesapp.domain.usecases.db.GetAllSavedMealsUseCase
import com.example.recipesapp.domain.usecases.db.InsertFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteFragmentVM(application: Application): AndroidViewModel(application){

    private val dbRepository = DbRepositoryImpl(application)
    private val getAllSavedMealsUseCase = GetAllSavedMealsUseCase(dbRepository)
    private val deleteMealUseCase = DeleteMealUseCase(dbRepository)
    private val insertFavoriteUseCase = InsertFavoriteUseCase(dbRepository)
    private var favoritesMealsLiveData: LiveData<List<MealDB>>? = null

    private fun getFavoritesMeals() {
        favoritesMealsLiveData = getAllSavedMealsUseCase.getAllSavedMeals()
    }
    fun deleteMeal(meal: MealDB) {
        viewModelScope.launch(Dispatchers.IO){
            deleteMealUseCase.deleteMeal(meal)
        }

    }
    fun saveMealFavorite(mealDB: MealDB) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteUseCase.insertFavorite(mealDB)
        }
    }

    fun getFavoritesMealsLiveData(): LiveData<List<MealDB>>? {
        return favoritesMealsLiveData
    }


    init{
        getFavoritesMeals()
    }
}