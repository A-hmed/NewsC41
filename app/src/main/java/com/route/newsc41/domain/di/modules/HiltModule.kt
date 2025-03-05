package com.route.newsc41.domain.di.modules

import com.route.newsc41.data.repository.news_repository.NewsRepositoryImpl
import com.route.newsc41.data.repository.news_repository.data_sources.local_data_source.NewsLocalDataSource
import com.route.newsc41.data.repository.news_repository.data_sources.local_data_source.NewsLocalDataSourceImpl
import com.route.newsc41.data.repository.news_repository.data_sources.remote_data_source.NewsRemoteDataSource
import com.route.newsc41.data.repository.news_repository.data_sources.remote_data_source.NewsRemoteDataSourceImpl
import com.route.newsc41.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltModule {

    @Binds
    abstract fun getNewsRemoteDataSource(
        newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource

    @Binds
    abstract fun getNewsLocalDataSource(
        newsLocalDataSourceImpl: NewsLocalDataSourceImpl
    ): NewsLocalDataSource

    @Binds
    abstract fun getNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}