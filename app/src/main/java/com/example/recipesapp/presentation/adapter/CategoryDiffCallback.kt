package com.example.recipesapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recipesapp.data.network.model.Category

object CategoryDiffCallback: DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}