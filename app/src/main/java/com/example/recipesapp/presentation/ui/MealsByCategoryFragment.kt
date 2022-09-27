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
import com.example.recipesapp.R
import com.example.recipesapp.data.network.model.Meal
import com.example.recipesapp.databinding.FragmentMealsByCategoryBinding
import com.example.recipesapp.presentation.adapter.MealsAdapter
import com.example.recipesapp.presentation.viemodel.MealsByCategoryFragmentVM
import com.example.recipesapp.utils.CATEGORY_NAME
import com.example.recipesapp.utils.MEAL_ID
import com.example.recipesapp.utils.MEAL_NAME
import com.example.recipesapp.utils.MEAL_THUMB


class MealsByCategoryFragment: Fragment() {

    private lateinit var viewModel: MealsByCategoryFragmentVM
    private var _binding: FragmentMealsByCategoryBinding? = null
    private val binding: FragmentMealsByCategoryBinding
        get() = _binding ?: throw RuntimeException("FragmentMealsByCategoryBinding is null")

    private lateinit var mealsAdapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       mealsAdapter = MealsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealsByCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareMealsByCategoryRecyclerView()

        viewModel = ViewModelProvider(this)[MealsByCategoryFragmentVM::class.java]
        observerMealsByCategory()
        onMealsByCategoryClick()
    }

    private fun prepareMealsByCategoryRecyclerView() {
        binding.rvMealByCategory.apply {
            layoutManager = GridLayoutManager(
                activity, 2, GridLayoutManager.VERTICAL, false
            )
            adapter = mealsAdapter
        }
    }

    private fun observerMealsByCategory() {
        val categoryName = arguments?.getString(CATEGORY_NAME)
        categoryName?.let {
            viewModel.getMealsByCategory(it)
            "Dishes from ${it.lowercase()}".also { binding.tvCategoryCount.text = it }
        }

        viewModel.getMealsByCategoryLiveData().observe(viewLifecycleOwner) {
            mealsAdapter.submitList(it.meals)
        }
    }
    private fun onMealsByCategoryClick(){
        mealsAdapter.onItemClick = object: MealsAdapter.OnItemClick {
            override fun onItemClick(meal: Meal) {
                val bundle = bundleOf(
                    MEAL_ID to meal.idMeal,
                    MEAL_NAME to meal.strMeal,
                    MEAL_THUMB to meal.strMealThumb
                )
                view?.let {
                    findNavController().navigate(
                        R.id.action_mealsByCategoryFragment_to_detailMealFragment,
                        bundle
                    )
                }
            }
        }
    }
}