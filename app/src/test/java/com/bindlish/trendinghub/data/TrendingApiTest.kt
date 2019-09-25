package com.bindlish.trendinghub.data

import com.bindlish.trendinghub.MockUtils
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

/**
 * Created by Aman Bindlish on 24,September,2019
 */
@RunWith(JUnit4::class)
class TrendingApiTest : BaseApiTest<DataApi>() {

    private lateinit var dataApi: DataApi

    @Before
    fun init() {
        dataApi = createService(DataApi::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun getRepository() {
        enqueueResponse("response.json")
        val repoSource = MockUtils.getValue(dataApi.fetchRepositories()).data
        // Dummy request
        mockWebServer.takeRequest()
        // Check repos source
        Assert.assertThat(repoSource, CoreMatchers.notNullValue())
        // Check item 1
        val repo1 = repoSource?.get(0)
        Assert.assertThat(repo1, CoreMatchers.notNullValue())
        if (repo1 != null) {
            Assert.assertEquals("denisidoro", repo1.author)
            Assert.assertEquals("navi", repo1.name)
            Assert.assertEquals("https://github.com/denisidoro.png", repo1.avatar)
            Assert.assertEquals("Shell", repo1.language)
        }
    }
}