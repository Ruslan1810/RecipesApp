package com.example.recipesapp.di

import androidx.lifecycle.ViewModel
import com.example.recipesapp.presentation.viemodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllCategoriesFragmentVM::class)
    fun bindAllCategoriesFragmentVM(viewModel: AllCategoriesFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMealFragmentVM::class)
    fun bindDetailMealFragmentVM(viewModel: DetailMealFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteFragmentVM::class)
    fun bindFavoriteFragmentVM(viewModel: FavoriteFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentVM::class)
    fun bindHomeFragmentVM(viewModel: HomeFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MealsByCategoryFragmentVM::class)
    fun bindMealsByCategoryFragmentVM(viewModel: MealsByCategoryFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentVM::class)
    fun bindSearchFragmentVM(viewModel: SearchFragmentVM): ViewModel
}