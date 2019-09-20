package com.bindlish.trendinghub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bindlish.trendinghub.data.DataApi
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.data.repository.TrendingRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendingViewModel @Inject constructor(dataApi: DataApi) : ViewModel() {

    private val dataRepository = TrendingRepository(dataApi)

    private val repositories = mutableListOf<GitRepoData>()
    private val repositoryListLiveData = MutableLiveData<List<GitRepoData>>()
    private val disposable = CompositeDisposable()

    fun getRepositories(): List<GitRepoData> = repositories

    fun fetchRepositories() {
        disposable.add(dataRepository.getRepositories().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { repoList ->
                repoList?.let {
                    repositories.clear()
                    repositories.addAll(it)
                    repositoryListLiveData.postValue(repositories)
                }
            })
    }

    fun getRepositoryLiveData() = repositoryListLiveData

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
