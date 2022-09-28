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
import com.example.recipesapp.databinding.FragmentAllCategoriesBinding
import com.example.recipesapp.presentation.AppProject
import com.example.recipesapp.presentation.adapter.CategoryAdapter
import com.example.recipesapp.presentation.viemodel.AllCategoriesFragmentVM
import com.example.recipesapp.presentation.viemodel.ViewModelFactory
import com.example.recipesapp.utils.CATEGORY_NAME
import javax.inject.Inject

class AllCategoriesFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AllCategoriesFragmentVM
    private var _binding: FragmentAllCategoriesBinding? = null
    private val binding: FragmentAllCategoriesBinding
        get() = _binding ?: throw RuntimeException("FragmentAllCategoriesBinding is null")

    private lateinit var categoryAdapter: CategoryAdapter
    private val component by lazy{
        (requireActivity().application as AppProject).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareCategoryRecyclerView()

        viewModel = ViewModelProvider(this,viewModelFactory)[AllCategoriesFragmentVM::class.java]
        observerCategory()
        onCategoryClick()
    }

    private fun prepareCategoryRecyclerView() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(
                activity, 2, GridLayoutManager.VERTICAL, false
            )
            adapter = categoryAdapter
        }
    }

    private fun observerCategory() {
        viewModel.getCategoriesLiveData().observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it.categories)
        }
    }
    private fun onCategoryClick() {
        categoryAdapter.onItemClick = {
            val bundle = bundleOf(CATEGORY_NAME to it.strCategory)
            view?.let {
                findNavController().navigate(
                    R.id.action_allCategoriesFragment_to_mealsByCategoryFragment,
                    bundle
                )
            }
        }
    }

}