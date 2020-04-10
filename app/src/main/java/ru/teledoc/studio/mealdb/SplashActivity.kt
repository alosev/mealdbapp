package ru.teledoc.studio.mealdb

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startMainActivity()
        }, 1000)
    }

    private fun startMainActivity() {
        MainActivity.create(this).let {
            this.startActivity(it)
            finish()
        }
    }
}
