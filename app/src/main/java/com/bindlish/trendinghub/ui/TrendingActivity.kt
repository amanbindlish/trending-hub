package com.bindlish.trendinghub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bindlish.trendinghub.R
import com.bindlish.trendinghub.ui.TrendingFragment

class TrendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrendingFragment.newInstance())
                .commitNow()
        }
    }

}
