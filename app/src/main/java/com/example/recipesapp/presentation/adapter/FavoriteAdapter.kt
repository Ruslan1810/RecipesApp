package com.example.recipesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesapp.data.mapper.AppMapper
import com.example.recipesapp.data.network.model.Category
import com.example.recipesapp.data.network.model.Meal
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.databinding.ItemFavoriteBinding

class FavoriteAdapter: ListAdapter<MealDB, FavoriteAdapter.FavoriteHolder>(FavoriteDiffCallback) {
    lateinit var onItemClick: OnItemClick

    fun getMelaByPosition(position: Int):MealDB{
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        return FavoriteHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.binding.apply {
            tvFavMealName.text = getItem(position).mealName
            Glide.with(holder.itemView)
                .load(getItem(position).mealThumb)
                .into(imgFavMeal)
        }

        holder.itemView.setOnClickListener {
            AppMapper.mapperMealDBToMeal(getItem(position))
            onItemClick.onItemClick(AppMapper.mapperMealDBToMeal(getItem(position)))
        }

    }

    class FavoriteHolder(val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClick {
        fun onItemClick(meal: Meal)
    }
}