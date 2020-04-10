package ru.teledoc.studio.mealdb

import android.app.Application
import ru.teledoc.studio.mealdb.retrofit.MealDbApi

class App : Application() {

    companion object {
        private lateinit var mealDbApi: MealDbApi

        fun getRetrofitService() = mealDbApi
    }

    override fun onCreate() {
        super.onCreate()

        mealDbApi = MealDbApi.getInstance()
    }
}