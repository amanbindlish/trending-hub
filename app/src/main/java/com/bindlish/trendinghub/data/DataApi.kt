package com.bindlish.trendinghub.data

import androidx.lifecycle.LiveData
import com.bindlish.trendinghub.data.network.Resource
import retrofit2.http.GET

/**
 * Created by Aman Bindlish on 20,September,2019
 */
interface DataApi {

    @GET("repositories")
    fun fetchRepositories(): LiveData<Resource<List<GitRepoData>>>
}