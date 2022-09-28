package com.example.recipesapp.di

import android.app.Application
import com.example.recipesapp.data.db.MealDao
import com.example.recipesapp.data.db.MealsDatabase
import com.example.recipesapp.data.network.retrofit.ApiFactory
import com.example.recipesapp.data.network.retrofit.ApiService
import com.example.recipesapp.data.repository.ApiRepositoryImpl
import com.example.recipesapp.data.repository.DbRepositoryImpl
import com.example.recipesapp.domain.repository.ApiRepository
import com.example.recipesapp.domain.repository.DbRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    fun bindApiRepository(impl: ApiRepositoryImpl): ApiRepository

    @Binds
    fun bindDbRepository(impl: DbRepositoryImpl): DbRepository

    companion object {
        @Singleton
        @Provides
        fun provideMealDao(application: Application): MealDao {
            return MealsDatabase.getInstance(application).dao()
        }
        @Singleton
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}