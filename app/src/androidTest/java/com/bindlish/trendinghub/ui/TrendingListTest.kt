package com.bindlish.trendinghub.ui

import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bindlish.trendinghub.R
import com.bindlish.trendinghub.ui.adapter.TrendingListAdapter
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Aman Bindlish on 24,September,2019
 */
@RunWith(AndroidJUnit4::class)
class TrendingListTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(TrendingActivity::class.java)

    @Before
    fun init() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun title_should_be_trending() {
        onView(withId(R.id.toolbar_title)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_title)).check(matches(withText("Trending")))
    }

    @Test
    fun check_fragment() {
        val fragment =
            activityTestRule.activity.supportFragmentManager.findFragmentByTag(TrendingFragment.TAG)
        Assert.assertTrue(fragment is TrendingFragment)
    }

    @Test
    fun click_third_item_in_list() {
        onView(withId(R.id.repos_recycler)).check { view, noViewFoundException ->
            val recyclerView = view as RecyclerView
            recyclerView.adapter?.itemCount?.compareTo(25)
        }
        onView(withId(R.id.repos_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<TrendingListAdapter.TrendingViewHolder>(
                2,
                ViewActions.click()
            )
        )
        // check if it is expanded
        onView(withId(R.id.repos_recycler)).check(matches(checkExpandedViewOf(2)))
        // check any other item should be collapsed
        onView(withId(R.id.repos_recycler)).check(matches(CoreMatchers.not(checkExpandedViewOf(3))))
    }

    private fun checkExpandedViewOf(pos: Int): Matcher<View> {
        return object :
            BoundedMatcher<View, View>(
                View::class.java
            ) {
            override fun describeTo(description: Description) {
                description.appendText("No item found")
            }

            override fun matchesSafely(view: View): Boolean {
                Assert.assertTrue("View is RecyclerView", view is RecyclerView)
                val recyclerView = view as RecyclerView
                val holder =
                    recyclerView.findViewHolderForAdapterPosition(pos) as TrendingListAdapter.TrendingViewHolder
                val collapsedLayout =
                    holder.itemView.findViewById<RelativeLayout>(R.id.collapsed_layout)
                return collapsedLayout != null && collapsedLayout.visibility == View.VISIBLE
            }

        }
    }
}