package com.bindlish.trendinghub

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bindlish.trendinghub.data.GitRepoData
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by Aman Bindlish on 24,September,2019
 */
object MockUtils {

    fun mockRepositories(): List<GitRepoData> {
        val repos = ArrayList<GitRepoData>()
        val repo = GitRepoData(
            author = "denisidoro",
            name = "navi",
            avatar = "https://github.com/denisidoro.png",
            url = "https://github.com/denisidoro/navi",
            language = "Shell",
            languageColor = "#89e051",
            stars = "1492",
            forks = "67",
            createdAt = System.currentTimeMillis()
        )
        repos.add(repo)
        return repos
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return data[0] as T
    }
}