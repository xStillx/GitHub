package com.example.github.features.search.data.model.mapper

import com.example.github.features.search.data.model.GitUserResponse
import com.example.github.features.search.domain.model.Git
import javax.inject.Inject

class GitUserMapper @Inject constructor(
    private val itemMapper: ItemUserMapper
) {

    fun map(gitResponse: GitUserResponse) = Git(
        items = gitResponse.items.map {
            itemMapper.map(it)
        }
    )
}