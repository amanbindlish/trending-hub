package com.bindlish.trendinghub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bindlish.trendinghub.R
import com.bindlish.trendinghub.ui.TrendingFragment
import kotlinx.android.synthetic.main.toolbar.*

class TrendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // casting custom toolbar and set it as action bar
        setSupportActionBar(toolbar)
        // configuring behaviour of toolbar
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrendingFragment.newInstance())
                .commitNow()
        }
    }

}
