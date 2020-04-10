package ru.teledoc.studio.mealdb.ui

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import ru.teledoc.studio.mealdb.App
import ru.teledoc.studio.mealdb.MealAdapter
import ru.teledoc.studio.mealdb.R
import ru.teledoc.studio.mealdb.models.DataResult
import ru.teledoc.studio.mealdb.retrofit.models.Meal

class CategoryActivity : BaseActivity<List<Meal>>() {

    companion object {
        const val EXTRA_ID = "extra_id"

        fun create(context: Context, id: String) = Intent(context, CategoryActivity::class.java).apply {
            putExtra(EXTRA_ID, id)
        }
    }

    override val layout: Int = R.layout.activity_category
    private lateinit var id: String
    private lateinit var adapter: MealAdapter

    override fun initView() {
        intent.getStringExtra(EXTRA_ID)?.let {
            id = it
        } ?: finish()

        adapter = MealAdapter()
        meal_list.layoutManager = getLayoutManager()
        meal_list.adapter = adapter
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(this, 2)
    }

    override suspend fun loadData(): DataResult {
        return loadAsync().await()
    }

    private suspend fun loadAsync() = async(context = Job() + Dispatchers.Default) {
        return@async try {
            val api = App.getRetrofitService()
            api.getMeal(id).execute().takeIf { it.isSuccessful }?.let { response ->
                response.body()?.let { data ->
                    DataResult.Success(data.meals)
                } ?: DataResult.Failure(Throwable("Непредвиденная ошибка"))
            } ?: DataResult.Failure(Throwable("Непредвиденная ошибка"))

        } catch (error: Throwable) {
            DataResult.Failure(error)
        }
    }

    override fun showData(data: List<Meal>) {
        adapter.meals = data
    }
}
