package com.example.recipesapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipesapp.databinding.FragmentSearchBinding
import com.example.recipesapp.presentation.adapter.MealsAdapter
import com.example.recipesapp.presentation.viemodel.SearchFragmentVM

class SearchFragment : Fragment() {
    private lateinit var searchAdapter: MealsAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")

    private lateinit var viewModel: SearchFragmentVM
    private var mealId = ""
    private var mealStr = ""
    private var mealThub = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
        searchAdapter = MealsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[SearchFragmentVM::class.java]
        onSearchClick()
        observeSearchLiveData()
    }

    private fun onMealCardClick() {
        binding.searchedMealCard.setOnClickListener {

        }
    }

    private fun onSearchClick() {
        binding.icSearch.setOnClickListener {
            viewModel.getMealByName(binding.edSearch.text.toString())
        }
    }

    private fun observeSearchLiveData() {
        viewModel.observeSearchLiveData().observe(viewLifecycleOwner) { t ->
                if (t == null) {
                    Toast.makeText(context, "No such a meal", Toast.LENGTH_SHORT).show()
                } else {
                    binding.apply {

                        mealId = t.idMeal
                        mealStr = t.strMeal
                        mealThub = t.strMealThumb

                        Glide.with(this@SearchFragment)
                            .load(t.strMealThumb)
                            .into(imgSearchedMeal)

                        tvSearchedMeal.text = t.strMeal
                        searchedMealCard.visibility = View.VISIBLE
                    }
                }
            }
    }
}