package com.example.recipesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesapp.data.network.model.Meal
import com.example.recipesapp.databinding.ItemPopularBinding

class PopularAdapter: ListAdapter<Meal, PopularAdapter.PopularHolder>(MealsDiffCallback) {
    lateinit var onItemClick: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        return PopularHolder(ItemPopularBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(getItem(position).strMealThumb)
                .into(imgPopularMeal)

        }
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(getItem(position))
        }
    }

    class PopularHolder(val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClick {
        fun onItemClick(meal: Meal)
    }
}
