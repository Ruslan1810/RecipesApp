package com.example.recipesapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.recipesapp.data.db.MealsDatabase
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.domain.repository.DbRepository

class DbRepositoryImpl(val context: Context): DbRepository {
    private val mealDao = MealsDatabase.getInstance(context).dao()

    override suspend fun insertFavorite(meal: MealDB) {
        mealDao.insertFavorite(meal)
    }

    override suspend fun updateFavorite(meal: MealDB) {
        mealDao.updateFavorite(meal)
    }

    override fun getAllSavedMeals(): LiveData<List<MealDB>> {
        return mealDao.getAllSavedMeals()
    }

    override suspend fun getMealById(id: String): MealDB {
        return mealDao.getMealById(id)
    }

    override suspend fun deleteMealById(id: String) {
        mealDao.deleteMealById(id)
    }

    override suspend fun deleteMeal(meal: MealDB) {
        mealDao.deleteMeal(meal)
    }
}