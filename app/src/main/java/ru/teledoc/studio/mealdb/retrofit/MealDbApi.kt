package ru.teledoc.studio.mealdb.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.teledoc.studio.mealdb.retrofit.models.CategoryResponse
import ru.teledoc.studio.mealdb.retrofit.models.MealResponse

interface MealDbApi {

    companion object {
        fun getInstance(): MealDbApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            return retrofit.create(MealDbApi::class.java)
        }
    }

    @GET("categories.php")
    fun getCategories(): Call<CategoryResponse>

    @GET("filter.php")
    fun getMeal(@Query("c") id: String): Call<MealResponse>
}