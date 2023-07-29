package com.example.github.features.search.data

import com.example.github.features.search.data.api.GitApi
import com.example.github.features.search.data.model.mapper.GitRepositoryMapper
import com.example.github.features.search.data.model.mapper.GitUserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitRepo @Inject constructor(
    private val gitApi: GitApi,
    private val gitUserMapper: GitUserMapper,
    private val gitRepositoryMapper: GitRepositoryMapper
) {

    suspend fun getUser(name: String) = withContext(Dispatchers.IO){
        gitApi.getUser(name).let {
            gitUserMapper.map(it)
        }
    }

    suspend fun getRepository(name: String) = withContext(Dispatchers.IO){
        gitApi.getRepository(name).let {
            gitRepositoryMapper.map(it)
        }
    }
}