package com.example.github.features.search.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitRepositoryResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    val items: List<ItemRepositoryResponse>,
)

@JsonClass(generateAdapter = true)
data class ItemRepositoryResponse(
    val name: String,
    val description: String,
    @Json(name = "forks_count")
    val forksCount: Long,
    @Json(name = "contents_url")
    val contentsUrl: String
)