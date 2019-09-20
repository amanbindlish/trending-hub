package com.bindlish.trendinghub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bindlish.trendinghub.R
import com.bindlish.trendinghub.databinding.TrendingFragmentBinding
import com.bindlish.trendinghub.viewmodel.TrendingViewModel
import com.bindlish.trendinghub.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_trending.*
import javax.inject.Inject

class TrendingFragment : Fragment() {

    companion object {
        fun newInstance() = TrendingFragment()
    }

    @Inject
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: TrendingFragmentBinding
    private lateinit var viewModel: TrendingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_trending,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrendingViewModel::class.java)
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
