package com.example.recipesapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recipesapp.data.network.model.Meal

object MealsDiffCallback: DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal== newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}