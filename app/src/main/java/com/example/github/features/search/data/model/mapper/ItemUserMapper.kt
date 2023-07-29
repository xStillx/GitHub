package com.example.github.features.search.data.model.mapper

import com.example.github.features.search.data.model.ItemUserResponse
import com.example.github.features.search.domain.model.Item
import javax.inject.Inject

class ItemUserMapper @Inject constructor() {

    fun map(itemResponse: ItemUserResponse) = Item(
        name = null,
        login = itemResponse.login,
        avatarUrl = itemResponse.avatarUrl,
        score = itemResponse.score,
        userType = true,
        htmlUrl = itemResponse.htmlUrl,
        description = null,
        forksCount = null,
        contentsUrl = null
    )
}