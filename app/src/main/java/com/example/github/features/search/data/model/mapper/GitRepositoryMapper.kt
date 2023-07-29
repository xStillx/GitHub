package com.example.github.features.search.data.model.mapper

import com.example.github.features.search.data.model.GitRepositoryResponse
import com.example.github.features.search.domain.model.Git
import javax.inject.Inject

class GitRepositoryMapper @Inject constructor(
    private val itemRepositoryMapper: ItemRepositoryMapper
) {

    fun map(gitRepositoryResponse: GitRepositoryResponse) = Git(
        items = gitRepositoryResponse.items.map {
            itemRepositoryMapper.map(it)
        }
    )
}