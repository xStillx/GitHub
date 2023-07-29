package com.example.github.features.search.data.api

import com.example.github.features.search.data.model.GitRepositoryResponse
import com.example.github.features.search.data.model.GitUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    @GET("search/users")
    suspend fun getUser(@Query("q") name: String): GitUserResponse

    @GET("search/repositories")
    suspend fun getRepository(@Query("q") name: String): GitRepositoryResponse

}