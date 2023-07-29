package com.example.github.features.search.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitUserResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    val items: List<ItemUserResponse>
)

@JsonClass(generateAdapter = true)
data class ItemUserResponse(
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val score: Float,
    @Json(name = "html_url")
    val htmlUrl: String
)