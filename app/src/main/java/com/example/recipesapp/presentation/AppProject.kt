package com.example.recipesapp.presentation

import android.app.Application
import com.example.recipesapp.di.DaggerApplicationComponent

class AppProject: Application() {

    val component by lazy{
        DaggerApplicationComponent.factory().create(this)
    }
}