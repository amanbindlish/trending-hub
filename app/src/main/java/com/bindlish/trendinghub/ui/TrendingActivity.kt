package com.bindlish.trendinghub.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.bindlish.trendinghub.R
import kotlinx.android.synthetic.main.toolbar.*

class TrendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        window.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

        // casting custom toolbar and set it as action bar
        setSupportActionBar(toolbar)
        // configuring behaviour of toolbar
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrendingFragment.newInstance(), TrendingFragment.TAG)
                .commitNow()
        }
        // handle click for three dots on top right of toolbar
        more_button.setOnClickListener {
            showSortingPopup()
        }

    }

    // method to create and show popup to show sorting items
    private fun showSortingPopup() {
        val popupMenu = PopupMenu(this, more_button)
        // inflating menu items
        popupMenu.inflate(R.menu.sorting_menu)
        // handling click of items
        popupMenu.setOnMenuItemClickListener {
            val fragment =
                supportFragmentManager.findFragmentByTag(TrendingFragment.TAG) as TrendingFragment
            when (it.itemId) {
                R.id.action_names -> fragment?.sortByNames()
                R.id.action_stars -> fragment?.sortByStars()
            }
            true
        }
        // show popup
        popupMenu.show()
    }

}
