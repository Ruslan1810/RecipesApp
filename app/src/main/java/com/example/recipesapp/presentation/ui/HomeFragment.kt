package com.example.recipesapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipesapp.R
import com.example.recipesapp.data.network.model.Meal
import com.example.recipesapp.data.network.model.RandomMealResponse
import com.example.recipesapp.databinding.FragmentHomeBinding
import com.example.recipesapp.presentation.adapter.CategoryAdapter
import com.example.recipesapp.presentation.adapter.PopularAdapter
import com.example.recipesapp.presentation.viemodel.HomeFragmentVM
import com.example.recipesapp.utils.CATEGORY_NAME
import com.example.recipesapp.utils.MEAL_ID
import com.example.recipesapp.utils.MEAL_NAME
import com.example.recipesapp.utils.MEAL_THUMB

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    private lateinit var viewModel: HomeFragmentVM
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var randomMealResponse: RandomMealResponse
    private var randomMealId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularAdapter = PopularAdapter()
        categoryAdapter = CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularRecyclerView()
        prepareCategoryRecyclerView()

        viewModel = ViewModelProvider(this)[HomeFragmentVM::class.java]
        observerPopularMeals()
        observerRandomMeals()
        observerCategory()

        onCategoryClick()
        onRandomMealClick()
        onPopularMelClick()
        onSearchClick()

    }

    private fun preparePopularRecyclerView() {
        binding.rvMealsPopular.apply {
            layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = popularAdapter
        }
    }

    private fun prepareCategoryRecyclerView() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(
                activity, 2, GridLayoutManager.VERTICAL, false
            )
            adapter = categoryAdapter
        }
    }

    private fun observerPopularMeals() {
        viewModel.getPopularMeals()
        viewModel.getPopularMealsLiveData().observe(viewLifecycleOwner) {
            popularAdapter.submitList(it.meals)
        }
    }

    private fun observerRandomMeals() {
        viewModel.getRandomMealLiveData().observe(viewLifecycleOwner) {
            val strMealThumb = it.meals[0].strMealThumb
            Glide.with(this@HomeFragment)
                .load(strMealThumb)
                .into(binding.imgRandomMeal)
            randomMealResponse = it
            randomMealId = it.meals[0].idMeal
        }
    }

    private fun observerCategory() {
        viewModel.getCategoriesLiveData().observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it.categories.subList(0, 6))
        }
    }

    private fun onCategoryClick() {
        categoryAdapter.onItemClick = {
            val bundle = bundleOf(CATEGORY_NAME to it.strCategory)
            view?.let {
                findNavController().navigate(
                    R.id.action_homeFragment_to_mealsByCategoryFragment,
                    bundle
                )
            }
        }
    }

    private fun onRandomMealClick() {
        binding.randomMeal.setOnClickListener {
            val meal = randomMealResponse.meals[0]
            val bundle = bundleOf(
                MEAL_ID to meal.idMeal,
                MEAL_NAME to meal.strMeal,
                MEAL_THUMB to meal.strMealThumb
            )
            view?.let {
                findNavController().navigate(
                    R.id.action_homeFragment_to_detailMealFragment,
                    bundle
                )
            }
        }
    }

    private fun onPopularMelClick() {
        popularAdapter.onItemClick = object: PopularAdapter.OnItemClick {
            override fun onItemClick(meal: Meal) {
                val bundle = bundleOf(
                    MEAL_ID to meal.idMeal,
                    MEAL_NAME to meal.strMeal,
                    MEAL_THUMB to meal.strMealThumb
                )
                view?.let {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailMealFragment,
                        bundle
                    )
                }
            }
        }
    }
    private fun onSearchClick() {

        binding.imgSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }
}