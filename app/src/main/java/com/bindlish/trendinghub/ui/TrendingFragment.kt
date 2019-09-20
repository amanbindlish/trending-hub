package com.bindlish.trendinghub.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bindlish.trendinghub.R
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.databinding.TrendingFragmentBinding
import com.bindlish.trendinghub.ui.adapter.TrendingListAdapter
import com.bindlish.trendinghub.viewmodel.TrendingViewModel
import com.bindlish.trendinghub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_trending.*
import javax.inject.Inject

class TrendingFragment : Fragment() {

    companion object {
        fun newInstance() = TrendingFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: TrendingFragmentBinding
    private lateinit var viewModel: TrendingViewModel
    private lateinit var listAdapter: TrendingListAdapter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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
        initialiseViews()
    }

    private fun initialiseViews() {
        binding.reposRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            listAdapter = TrendingListAdapter()
            adapter = listAdapter
        }
        // hit api if view model doesn't have data, else show directly (for configuration changes)
        if (viewModel.getRepositories().isEmpty()) {
            viewModel.fetchRepositories()
        } else {
            displayReposData(viewModel.getRepositories())
        }
        // observe live data from repository
        viewModel.getRepositoryLiveData().observe(this, Observer { repositories ->
            displayReposData(repositories)
        })
    }

    private fun displayReposData(repositories: List<GitRepoData>) {
        listAdapter.setRepositories(repositories)
        hideShimmer()
        binding.reposRecycler.scheduleLayoutAnimation()
    }

    // starting shimmer effect in onResume
    override fun onResume() {
        super.onResume()
        showShimmer()
    }

    // stop shimmer effect in onPause
    override fun onPause() {
        hideShimmer()
        super.onPause()
    }

    private fun showShimmer() {
        shimmer_view_container.visibility = View.VISIBLE
        shimmer_view_container.startShimmer()
    }

    private fun hideShimmer() {
        shimmer_view_container.stopShimmer()
        shimmer_view_container.visibility = View.GONE
    }

}
