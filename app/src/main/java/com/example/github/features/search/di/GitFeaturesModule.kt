package com.example.github.features.search.di

import com.example.github.features.search.data.api.GitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class GitFeaturesModule {

    @Provides
    fun provideGitApi(retrofit: Retrofit) = retrofit.create(
        GitApi::class.java
    )
}