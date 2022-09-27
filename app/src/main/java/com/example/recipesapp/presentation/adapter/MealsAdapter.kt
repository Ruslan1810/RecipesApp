package com.example.recipesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesapp.data.network.model.Meal
import com.example.recipesapp.databinding.ItemMealBinding

class MealsAdapter:
    ListAdapter<Meal, MealsAdapter.MealsHolder>(MealsDiffCallback) {
    lateinit var onItemClick: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsHolder {
        return MealsHolder(ItemMealBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MealsHolder, position: Int) {
        holder.binding.apply {
            tvMealName.text = getItem(position).strMeal
            Glide.with(holder.itemView)
                .load(getItem(position).strMealThumb)
                .into(imgMeal)
        }
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(getItem(position))
        }

    }

    class MealsHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClick {
        fun onItemClick(meal: Meal)
    }
}