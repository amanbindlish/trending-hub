package com.bindlish.trendinghub.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Aman Bindlish on 20,September,2019
 */
data class GitRepoData(
    @SerializedName("author")
    var author: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("languageColor")
    var languageColor: String,
    @SerializedName("stars")
    var stars: String,
    @SerializedName("forks")
    var forks: String
)