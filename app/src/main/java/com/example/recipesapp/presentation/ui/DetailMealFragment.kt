package com.example.recipesapp.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipesapp.R
import com.example.recipesapp.data.mapper.AppMapper
import com.example.recipesapp.data.network.model.MealDB
import com.example.recipesapp.databinding.FragmentDetailInfoBinding
import com.example.recipesapp.presentation.viemodel.DetailMealFragmentVM
import com.example.recipesapp.utils.MEAL_ID

class DetailMealFragment : Fragment() {

    private lateinit var youtubeLink: String
    private lateinit var mealId: String
    private var mealToSave: MealDB? = null
    private var _binding: FragmentDetailInfoBinding? = null
    private val binding: FragmentDetailInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailInfoBinding is null")

    private lateinit var viewModel: DetailMealFragmentVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(MEAL_ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailMealFragmentVM::class.java]

        observerMealDetail()

        onYoutubeImageClick()
        onFavoriteClick()
    }

    private fun observerMealDetail() {
        viewModel.getMealDetail(mealId)
        viewModel.getMealDetailLiveData().observe(viewLifecycleOwner) {
            it?.let {
                "Category: ${it.meals[0].strCategory}".also { binding.tvCategoryInfo.text = it }
                "Area: ${it.meals[0].strArea}".also { binding.tvAreaInfo.text = it }
                binding.tvInstructions.text = it.meals[0].strInstructions

                Glide.with(this@DetailMealFragment)
                    .load(it.meals[0].strMealThumb)
                    .into(binding.imgMealDetail)

                mealToSave = AppMapper.mapperMealDetailToMealDB(it.meals[0])
                youtubeLink = it.meals[0].strYoutube
                binding.collapsingToolbar.title = it.meals[0].strMeal
                binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
                binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

            }

        }
    }

    private fun onFavoriteClick() {
        binding.btnSave.setOnClickListener {
            mealToSave?.let {
                viewModel.saveMealFavorite(it)
                Toast.makeText(activity, "Saved", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }
}