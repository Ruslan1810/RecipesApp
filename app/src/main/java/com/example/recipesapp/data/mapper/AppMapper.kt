package com.example.recipesapp.data.mapper

import com.example.recipesapp.data.network.model.Meal
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.data.network.model.MealDetail

object AppMapper {
    fun mapperMealDetailToMealDB(mealDetail: MealDetail): MealDB {
        return MealDB(
            mealId = mealDetail.idMeal.toInt(),
            mealName = mealDetail.strMeal,
            mealCountry = mealDetail.strArea,
            mealCategory = mealDetail.strCategory,
            mealInstruction = mealDetail.strInstructions,
            mealThumb = mealDetail.strMealThumb,
            mealYoutubeLink = mealDetail.strYoutube
        )
    }


    fun mapperMealDBToMeal(mealDB: MealDB): Meal {
        return Meal(
            idMeal = mealDB.mealId.toString(),
            strMeal = mealDB.mealName,
            strMealThumb = mealDB.mealThumb
        )
    }
}