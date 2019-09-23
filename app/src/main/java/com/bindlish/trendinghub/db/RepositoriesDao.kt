package com.bindlish.trendinghub.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bindlish.trendinghub.data.GitRepoData

/**
 * Created by Aman Bindlish on 21,September,2019
 */
@Dao
interface RepositoriesDao {

    @Query("SELECT * FROM repositories")
    fun getRepositories(): LiveData<List<GitRepoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repos: List<GitRepoData>): List<Long>

    @Query("DELETE FROM repositories")
    fun deleteAll()

    @Transaction
    fun deleteAndInsertRepos(repos: List<GitRepoData>) {
        deleteAll()
        insertRepositories(repos)
    }

    @Transaction
    fun deleteAndInsertWithTimeStamp(repos: List<GitRepoData>) {
        deleteAndInsertRepos(repos.apply {
            for (data in this) {
                data.createdAt = System.currentTimeMillis()
            }
        })
    }
}