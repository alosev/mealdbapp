package ru.teledoc.studio.mealdb.retrofit.models

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
    @SerializedName("categories")
    val categories: List<Category>
)