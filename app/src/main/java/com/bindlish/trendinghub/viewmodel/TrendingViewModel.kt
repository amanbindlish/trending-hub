package com.bindlish.trendinghub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.data.network.Resource
import com.bindlish.trendinghub.data.repository.TrendingRepository
import javax.inject.Inject

class TrendingViewModel @Inject constructor(private val dataRepository: TrendingRepository) :
    ViewModel() {

    private val repoData = MutableLiveData<Boolean>()

    private val repositoryListLiveData: LiveData<Resource<List<GitRepoData>?>> =
        Transformations.switchMap(
            repoData,
            { input -> dataRepository.getRepositories(input) }
        )

    private val loadingError = MutableLiveData<Boolean>()

    fun getRepositoryLiveData() = repositoryListLiveData

    fun getLoadingErrorStatus() = loadingError

    fun fetchUpdatedData(forceRefresh: Boolean) {
        repoData.value = forceRefresh
    }
}
