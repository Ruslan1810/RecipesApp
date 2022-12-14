package com.example.recipesapp.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.domain.usecases.db.DeleteMealUseCase
import com.example.recipesapp.domain.usecases.db.GetAllSavedMealsUseCase
import com.example.recipesapp.domain.usecases.db.InsertFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragmentVM @Inject constructor(
    private val getAllSavedMealsUseCase: GetAllSavedMealsUseCase,
    private val deleteMealUseCase: DeleteMealUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : ViewModel() {

    private var favoritesMealsLiveData: LiveData<List<MealDB>>? = null

    private fun getFavoritesMeals() {
        favoritesMealsLiveData = getAllSavedMealsUseCase.getAllSavedMeals()
    }

    fun deleteMeal(meal: MealDB) {
        viewModelScope.launch(Dispatchers.IO) {
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


    init {
        getFavoritesMeals()
    }
}