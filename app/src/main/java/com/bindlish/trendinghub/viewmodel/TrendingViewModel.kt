package com.bindlish.trendinghub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bindlish.trendinghub.data.DataApi
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.data.repository.TrendingRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendingViewModel @Inject constructor(dataApi: DataApi) : ViewModel() {

    private val dataRepository = TrendingRepository(dataApi)

    private val repositories = mutableListOf<GitRepoData>()
    private val repositoryListLiveData = MutableLiveData<List<GitRepoData>>()
    private val loadingError = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    fun getRepositories(): List<GitRepoData> = repositories

    fun fetchRepositories() {
        disposable.add(
            dataRepository.getRepositories().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<GitRepoData>>() {
                    override fun onNext(repoList: List<GitRepoData>?) {
                        repoList?.let {
                            repositories.clear()
                            repositories.addAll(it)
                            repositoryListLiveData.postValue(repositories)
                            loadingError.postValue(false)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        loadingError.postValue(true)
                    }

                    override fun onComplete() {}
                })
        )
    }

    fun getRepositoryLiveData() = repositoryListLiveData

    fun getLoadingErrorStatus() = loadingError

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
