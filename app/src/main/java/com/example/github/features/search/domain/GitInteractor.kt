package com.example.github.features.search.domain

import com.example.github.features.search.data.GitRepo
import com.example.github.utils.safeRequest
import javax.inject.Inject

class GitInteractor @Inject constructor(
    private val gitRepo: GitRepo
) {

    suspend fun getUser(name: String) = safeRequest {
        gitRepo.getUser(name)
    }

    suspend fun getRepository(name: String) = safeRequest {
        gitRepo.getRepository(name)
    }
}