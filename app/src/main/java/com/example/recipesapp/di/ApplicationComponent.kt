package com.example.recipesapp.di

import android.app.Activity
import android.app.Application
import com.example.recipesapp.presentation.ui.*
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: Activity)
    fun inject(fragment: AllCategoriesFragment)
    fun inject(fragment: DetailMealFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: MealsByCategoryFragment)
    fun inject(fragment: SearchFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}