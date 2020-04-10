package ru.teledoc.studio.mealdb.retrofit.models

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal")
    val id: Int,

    @SerializedName("strMeal")
    val title: String,

    @SerializedName("strMealThumb")
    val thumbs: String
)