package com.bindlish.trendinghub.ui.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bindlish.trendinghub.R
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.databinding.RepoListItemBinding
import com.bumptech.glide.Glide

/**
 * Created by Aman Bindlish on 20,September,2019
 */
class TrendingListAdapter : RecyclerView.Adapter<TrendingListAdapter.TrendingViewHolder>() {

    private val repositories: MutableList<GitRepoData> = mutableListOf()
    private var expandedPos = -1

    fun setRepositories(repos: List<GitRepoData>?) {
        repositories.clear()
        repos?.let {
            repositories.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = RepoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrendingViewHolder(binding)
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bindData(repositories[position])
    }

    fun getItem(pos: Int) = repositories[pos]

    inner class TrendingViewHolder(private val binding: RepoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(repo: GitRepoData) {
            binding.authorName.text = repo.author
            binding.repoName.text = repo.name
            binding.repoUrl.text = repo.url
            binding.repoStarCount.text = repo.stars
            binding.repoForkCount.text = repo.forks
            // check for repo lang and make it visible
            repo.language?.let {
                binding.repoLangName.visibility = View.VISIBLE
                binding.repoLangColor.visibility = View.VISIBLE
                binding.repoLangName.text = repo.language
            } ?: run {
                binding.repoLangColor.visibility = View.GONE
                binding.repoLangName.visibility = View.GONE
            }
            // check for repo lang color and change color
            repo.languageColor?.let {
                val drawable =
                    binding.root.context.resources.getDrawable(R.drawable.ic_shape_circle) as GradientDrawable
                drawable.setColor(Color.parseColor(repo.languageColor))
                binding.repoLangColor.setImageDrawable(drawable)
            }
            // show avatar from glide
            Glide.with(binding.root.context).load(repo.avatar).into(binding.repoAvatar)
            // check for expand/collapsed state
            if (repo.isExpanded) {
                binding.collapsedLayout.visibility = View.VISIBLE
            } else {
                binding.collapsedLayout.visibility = View.GONE
            }
            binding.root.setOnClickListener {
                // make it expand / collapse on basis of boolean
                repo.isExpanded = !repo.isExpanded
                notifyItemChanged(layoutPosition)
                // update/notify the expanded item to collapse
                if (expandedPos != -1 && expandedPos != layoutPosition) {
                    getItem(expandedPos).isExpanded = false
                    notifyItemChanged(expandedPos)
                }
                // update the expanded position
                if (repo.isExpanded) {
                    expandedPos = layoutPosition
                }
            }
        }
    }
}