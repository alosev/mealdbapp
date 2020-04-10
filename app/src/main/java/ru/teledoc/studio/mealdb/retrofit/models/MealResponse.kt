package ru.teledoc.studio.mealdb.retrofit.models

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)