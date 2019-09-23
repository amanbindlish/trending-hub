package com.bindlish.trendinghub.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Aman Bindlish on 20,September,2019
 */
@Entity(tableName = "repositories")
data class GitRepoData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @SerializedName("author")
    var author: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("language")
    var language: String?,
    @SerializedName("languageColor")
    var languageColor: String?,
    @SerializedName("stars")
    var stars: String,
    @SerializedName("forks")
    var forks: String,
    var isExpanded: Boolean,
    var createdAt: Long
)