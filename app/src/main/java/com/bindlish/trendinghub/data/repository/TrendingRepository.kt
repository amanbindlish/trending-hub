package com.bindlish.trendinghub.data.repository

import com.bindlish.trendinghub.data.DataApi
import javax.inject.Singleton

/**
 * Created by Aman Bindlish on 20,September,2019
 */
@Singleton
class TrendingRepository(private val dataApi: DataApi) {

    fun getRepositories() = dataApi.fetchRepositories()
}