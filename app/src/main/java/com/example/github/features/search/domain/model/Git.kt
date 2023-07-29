package com.example.github.features.search.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Git(
    val items: List<Item>,
): Parcelable

@Parcelize
data class Item(
    val name: String?,
    val login: String?,
    val avatarUrl: String?,
    val score: Float?,
    val htmlUrl: String?,
    val userType: Boolean,
    val description: String?,
    val forksCount: Long?,
    val contentsUrl: String?
): Parcelable
