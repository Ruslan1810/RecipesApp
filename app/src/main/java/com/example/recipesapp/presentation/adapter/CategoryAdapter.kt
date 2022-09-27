package com.example.recipesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesapp.data.network.model.Category
import com.example.recipesapp.databinding.ItemCategoryBinding

class CategoryAdapter: ListAdapter<Category, CategoryAdapter.CategoryHolder>(CategoryDiffCallback) {
    lateinit var onItemClick: ((Category) -> Unit)

    class CategoryHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.binding.apply {
            tvCategoryName.text = getItem(position).strCategory

            Glide.with(holder.itemView)
                .load(getItem(position).strCategoryThumb)
                .into(imgCategory)
        }
        holder.itemView.setOnClickListener {
            onItemClick.invoke(getItem(position))
        }
    }
}