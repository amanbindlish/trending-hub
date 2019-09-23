package com.bindlish.trendinghub.data.repository

import android.util.ArrayMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bindlish.trendinghub.data.DataApi
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.data.network.Resource
import com.bindlish.trendinghub.db.RepositoriesDao
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Aman Bindlish on 20,September,2019
 */
@Singleton
class TrendingRepository @Inject constructor(
    private val dataDao: RepositoriesDao,
    private val dataApi: DataApi,
    private val appRequestExecutors: AppRequestExecutors = AppRequestExecutors()
) {

    companion object {
        // cache timeout is 2 hours as per requirement
        val TIMEOUT = TimeUnit.HOURS.toMillis(2)
        var timeOutMap: ArrayMap<String, Long> = ArrayMap()
    }

    // fetch repositories data, param is boolean which is to fetch api data forcefully
    fun getRepositories(forceRefresh: Boolean = false): MutableLiveData<Resource<List<GitRepoData>?>> =
        object : NetworkBoundResource<List<GitRepoData>, List<GitRepoData>>(appRequestExecutors) {
            override fun saveCallResult(item: List<GitRepoData>) {
                dataDao.deleteAndInsertRepos(item)
            }

            override fun shouldFetch(data: List<GitRepoData>?): Boolean =
                data == null || data.isEmpty() || isCacheTimedOut("repositories") || forceRefresh

            override fun loadFromDb(): LiveData<List<GitRepoData>> = dataDao.getRepositories()

            override fun createCall(): LiveData<Resource<List<GitRepoData>>> =
                dataApi.fetchRepositories()

        }.asLiveData()

    // method to check cache timeout of a request
    private fun isCacheTimedOut(key: String): Boolean {
        val lastFetched = timeOutMap[key]
        val now = System.currentTimeMillis()
        if (lastFetched == null || (now - lastFetched) > TIMEOUT) {
            timeOutMap[key] = now
            return true
        }
        return false
    }
}