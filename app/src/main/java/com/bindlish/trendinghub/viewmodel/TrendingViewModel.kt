package com.bindlish.trendinghub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.data.network.Resource
import com.bindlish.trendinghub.data.repository.TrendingRepository
import javax.inject.Inject

class TrendingViewModel @Inject constructor(dataRepository: TrendingRepository) : ViewModel() {

    private val repositoryListLiveData: LiveData<Resource<List<GitRepoData>?>> =
        dataRepository.getRepositories()

    private val loadingError = MutableLiveData<Boolean>()

    fun getRepositoryLiveData() = repositoryListLiveData

    fun getLoadingErrorStatus() = loadingError
}
