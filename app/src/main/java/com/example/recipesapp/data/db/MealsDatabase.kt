package com.example.recipesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipesapp.data.network.model.MealDB

@Database(entities = [MealDB::class], version = 6)
abstract class MealsDatabase : RoomDatabase() {
    abstract fun dao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealsDatabase? = null
        private const val DB_NAME = "meal_database"

        @Synchronized
        fun getInstance(context: Context): MealsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

