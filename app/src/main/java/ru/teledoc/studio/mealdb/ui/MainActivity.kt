package ru.teledoc.studio.mealdb.ui

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.teledoc.studio.mealdb.App
import ru.teledoc.studio.mealdb.MealCategoryAdapter
import ru.teledoc.studio.mealdb.R
import ru.teledoc.studio.mealdb.models.DataResult
import ru.teledoc.studio.mealdb.retrofit.models.Category
import ru.teledoc.studio.mealdb.retrofit.models.CategoryResponse
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : BaseActivity<List<Category>>() {

    companion object {
        fun create(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    override val layout: Int = R.layout.activity_main
    private lateinit var adapter: MealCategoryAdapter

    override fun initView() {
        adapter = MealCategoryAdapter(object : MealCategoryAdapter.MealCategoryListener {
            override fun onClick(id: String) {
                startCategoryActivity(id)
            }
        })

        category_list.layoutManager = getLayoutManager()
        category_list.adapter = adapter
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(this, 2)
    }

    override suspend fun loadData(): DataResult = suspendCoroutine { continuation ->
        try {
            val api = App.getRetrofitService()

            api.getCategories().enqueue(object : Callback<CategoryResponse> {
                override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                    continuation.resume(DataResult.Failure(t))
                }

                override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                    response.takeIf { it.isSuccessful }?.let {
                        it.body()?.let { categoryResponse ->
                            continuation.resume(DataResult.Success(categoryResponse.categories))
                        } ?: continuation.resume(DataResult.Failure(Throwable("непредвиденная ошибка")))
                    } ?: continuation.resume(DataResult.Failure(Throwable("непредвиденная ошибка")))
                }
            })
        } catch (t: Throwable) {
            continuation.resume(DataResult.Failure(t))
        }
    }

    private fun startCategoryActivity(id: String) {
        CategoryActivity.create(this, id).let { intent ->
            this.startActivity(intent)
        }
    }

    override fun showData(data: List<Category>) {
        adapter.categories = data
    }
}
