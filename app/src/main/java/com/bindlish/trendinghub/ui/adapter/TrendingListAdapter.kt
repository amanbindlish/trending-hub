package com.bindlish.trendinghub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.databinding.RepoListItemBinding
import com.bumptech.glide.Glide

/**
 * Created by Aman Bindlish on 20,September,2019
 */
class TrendingListAdapter : RecyclerView.Adapter<TrendingListAdapter.TrendingViewHolder>() {

    private val repositories: MutableList<GitRepoData> = mutableListOf()

    fun setRepositories(repos: List<GitRepoData>) {
        repositories.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            RepoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bindData(repositories[position])
    }

    class TrendingViewHolder(private val binding: RepoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(repo: GitRepoData) {
            binding.authorName.text = repo.author
            binding.repoName.text = repo.name
            Glide.with(binding.root.context).load(repo.avatar).into(binding.repoAvatar)
        }
    }
}