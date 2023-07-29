package com.example.github.features.search.data.model.mapper

import com.example.github.features.search.data.model.ItemRepositoryResponse
import com.example.github.features.search.domain.model.Item
import javax.inject.Inject

class ItemRepositoryMapper @Inject constructor() {

    fun map(itemRepositoryResponse: ItemRepositoryResponse) = Item(
        name = itemRepositoryResponse.name,
        description = itemRepositoryResponse.description,
        forksCount = itemRepositoryResponse.forksCount,
        contentsUrl = itemRepositoryResponse.contentsUrl,
        login = null,
        avatarUrl = null,
        score = null,
        htmlUrl = null,
        userType = false

    )
}