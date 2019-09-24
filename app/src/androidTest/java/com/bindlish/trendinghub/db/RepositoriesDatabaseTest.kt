package com.bindlish.trendinghub.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bindlish.trendinghub.data.GitRepoData
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by Aman Bindlish on 24,September,2019
 */
@RunWith(AndroidJUnit4::class)
open class RepositoriesDatabaseTest {

    private lateinit var repoDb: RepositoriesDatabase
    private lateinit var repoDao: RepositoriesDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        repoDb = Room.inMemoryDatabaseBuilder(
            context,
            RepositoriesDatabase::class.java
        ).build()
        repoDao = repoDb.repositoriesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        repoDb.close()
    }

    @Test
    fun should_insert_repository() {
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
        val rows = repoDao.insertRepositories(listOf(repo))
        val repoTest = getValue(repoDao.getRepositories())

        Assert.assertEquals(1, rows.size)
        Assert.assertEquals(1, repoTest.size)
        assert(repoTest.isNotEmpty())
    }

    @Test
    fun compare_inserted_repository() {
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
        repoDao.insertRepositories(listOf(repo))
        val repoTest = getValue(repoDao.getRepositories())

        Assert.assertEquals(1, repoTest.size)
        assert(repoTest.isNotEmpty())

        val repo1 = repoTest[0]
        Assert.assertEquals(repo.author, repo1.author)
        Assert.assertEquals(repo.name, repo1.name)
        Assert.assertEquals(repo.avatar, repo1.avatar)
        Assert.assertEquals(repo.url, repo1.url)
    }

    @Test
    fun should_clear_Repositories() {
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
        repoDao.insertRepositories(listOf(repo))
        repoDao.deleteAll()
        assert(getValue(repoDao.getRepositories()).isEmpty())
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