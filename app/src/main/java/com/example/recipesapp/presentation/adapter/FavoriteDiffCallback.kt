package com.example.recipesapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recipesapp.data.network.model.MealDB

object FavoriteDiffCallback: DiffUtil.ItemCallback<MealDB>() {
    override fun areItemsTheSame(oldItem: MealDB, newItem: MealDB): Boolean {
        return oldItem.mealId == newItem.mealId
    }

    override fun areContentsTheSame(oldItem: MealDB, newItem: MealDB): Boolean {
        return oldItem == newItem
    }
}