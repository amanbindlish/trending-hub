package com.bindlish.trendinghub.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bindlish.trendinghub.data.GitRepoData
import com.bindlish.trendinghub.data.network.Resource
import com.bindlish.trendinghub.data.network.Status
import com.bindlish.trendinghub.data.repository.TrendingRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Aman Bindlish on 24,September,2019
 */
@RunWith(JUnit4::class)
class TrendingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var dataRepository: TrendingRepository

    lateinit var viewModel: TrendingViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = TrendingViewModel(this.dataRepository)
    }

    @Test
    fun fetchRepositories_success() {
        // mock response
        Mockito.`when`(dataRepository.getRepositories()).thenAnswer {
            return@thenAnswer MutableLiveData<Resource<List<GitRepoData>>>()
        }
        // Attach fake observer
        val observer = Mockito.mock(Observer::class.java) as Observer<Resource<List<GitRepoData>?>>
        viewModel.getRepositoryLiveData().observeForever(observer)

        viewModel.fetchUpdatedData(ArgumentMatchers.anyBoolean())

        // Verify and assertion
        Assert.assertNotNull(viewModel.getRepositoryLiveData().value)
        Assert.assertEquals(Status.SUCCESS, viewModel.getRepositoryLiveData().value?.status)

    }

}