package com.bindlish.trendinghub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bindlish.trendinghub.R
import com.bindlish.trendinghub.viewmodel.TrendingViewModel
import kotlinx.android.synthetic.main.fragment_trending.*

class TrendingFragment : Fragment() {

    companion object {
        fun newInstance() = TrendingFragment()
    }

    private lateinit var viewModel: TrendingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        // TODO: Use the ViewModel
    }

    // starting shimmer effect in onResume
    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    // stoping shimmer effect in onPause
    override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }

}
