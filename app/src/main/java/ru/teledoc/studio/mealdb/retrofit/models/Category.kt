package ru.teledoc.studio.mealdb.retrofit.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    val id: Int,

    @SerializedName("strCategory")
    val title: String,

    @SerializedName("strCategoryDescription")
    val description: String,

    @SerializedName("strCategoryThumb")
    val thumb: String
)