package com.bindlish.trendinghub.data

import com.bindlish.trendinghub.data.network.Resource
import com.bindlish.trendinghub.data.network.Status
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Aman Bindlish on 24,September,2019
 */
@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val apiResponse = Resource.error<Any>("test error")
        Assert.assertEquals("test error", apiResponse.errorMessage)
        Assert.assertEquals(Status.ERROR, apiResponse.status)
    }

    @Test
    fun success() {
        val resource = Resource.success("test success")
        Assert.assertEquals("test success", resource.data)
        Assert.assertEquals(Status.SUCCESS, resource.status)
    }
}