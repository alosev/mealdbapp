package ru.teledoc.studio.mealdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.teledoc.studio.mealdb.models.DataResult
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T> : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext by lazy {
        Job() + Dispatchers.Main
    }

    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        initView()
        initData()
    }

    protected fun initData() {
        launch {
            loadData().let { result ->
                when (result) {
                    is DataResult.Success<*> -> showData(result.data as T)
                    is DataResult.Failure -> showError(result.error)
                }
            }
        }
    }

    protected abstract fun initView()
    protected abstract suspend fun loadData(): DataResult
    protected abstract fun showData(data: T)

    protected fun showError(t: Throwable) {
        t.message?.let { errorMessage ->
            ErrorDialog.create(errorMessage).show(supportFragmentManager, ErrorDialog.TAG)
        }
    }
}