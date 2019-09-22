package com.bindlish.trendinghub.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bindlish.trendinghub.data.GitRepoData

/**
 * Created by Aman Bindlish on 21,September,2019
 */
@Database(entities = [GitRepoData::class], version = 1)
abstract class RepositoriesDatabase : RoomDatabase() {

    abstract fun repositoriesDao(): RepositoriesDao
}