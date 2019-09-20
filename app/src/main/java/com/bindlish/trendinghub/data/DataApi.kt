package com.bindlish.trendinghub.data

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Aman Bindlish on 20,September,2019
 */
interface DataApi {

    @GET("repositories")
    fun fetchRepositories(): Observable<Response<List<GitRepoData>>>
}