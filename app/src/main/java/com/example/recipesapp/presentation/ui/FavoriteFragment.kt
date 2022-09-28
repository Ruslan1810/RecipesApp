package com.example.recipesapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.data.network.model.Meal
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.databinding.FragmentFavoriteBinding
import com.example.recipesapp.presentation.AppProject
import com.example.recipesapp.presentation.adapter.FavoriteAdapter
import com.example.recipesapp.presentation.viemodel.FavoriteFragmentVM
import com.example.recipesapp.presentation.viemodel.ViewModelFactory
import com.example.recipesapp.utils.MEAL_ID
import com.example.recipesapp.utils.MEAL_NAME
import com.example.recipesapp.utils.MEAL_THUMB
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class FavoriteFragment : Fragment() {
    private lateinit var favoriteAdapter: FavoriteAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: FavoriteFragmentVM
    private val component by lazy{
        (requireActivity().application as AppProject).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        favoriteAdapter = FavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularRecyclerView()
        viewModel = ViewModelProvider(this,viewModelFactory)[FavoriteFragmentVM::class.java]

        observerFavoritesMealsLiveData()
        onFavoriteMelClick()
        itemTouchHandler()

    }

    private fun itemTouchHandler() {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val favoriteMeal = favoriteAdapter.getMelaByPosition(position)
                viewModel.deleteMeal(favoriteMeal)

                Snackbar.make(requireView(), "Meal was deleted", Snackbar.LENGTH_LONG).apply {
                    setAction("undo") {
                        viewModel.saveMealFavorite(favoriteMeal)
                    }.show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavorite)
    }


    private fun observerFavoritesMealsLiveData() {
        viewModel.getFavoritesMealsLiveData()?.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
            it.forEach {
                Log.d("Test", it.mealThumb)
            }
        }
    }

    private fun preparePopularRecyclerView() {
        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(
                context, 2, GridLayoutManager.VERTICAL, false
            )
            adapter = favoriteAdapter
        }
    }
    private fun onFavoriteMelClick() {
        favoriteAdapter.onItemClick = object: FavoriteAdapter.OnItemClick {
            override fun onItemClick(meal: Meal) {
                val bundle = bundleOf(
                    MEAL_ID to meal.idMeal,
                    MEAL_NAME to meal.strMeal,
                    MEAL_THUMB to meal.strMealThumb
                )
                view?.let {
                    findNavController().navigate(
                        R.id.action_favoriteFragment_to_detailMealFragment,
                        bundle
                    )
                }
            }
        }
    }
}