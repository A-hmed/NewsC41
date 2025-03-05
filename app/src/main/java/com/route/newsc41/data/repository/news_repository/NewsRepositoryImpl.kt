package com.route.newsc41.data.repository.news_repository

import com.route.newsc41.data.mappers.SourcesMapper
import com.route.newsc41.data.mappers.toArticle
import com.route.newsc41.data.repository.news_repository.data_sources.local_data_source.NewsLocalDataSource
import com.route.newsc41.data.repository.news_repository.data_sources.remote_data_source.NewsRemoteDataSource
import com.route.newsc41.data.utils.ConnectivityChecker
import com.route.newsc41.domain.model.Article
import com.route.newsc41.domain.model.Source
import com.route.newsc41.domain.repository.NewsRepository
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private var localDataSource: NewsLocalDataSource,
    private var remoteDataSource: NewsRemoteDataSource,
    private var sourcesMapper: SourcesMapper,
    private var connectivityChecker: ConnectivityChecker,
) : NewsRepository {
    override suspend fun getSources(categoryId: String): List<Source> {

        return if (connectivityChecker.isOnline()) {
            val sources = remoteDataSource.getSources(categoryId)
            localDataSource.saveSources(sources)
            sourcesMapper.fromDataModels(sources)
        } else {
            sourcesMapper.fromDataModels(localDataSource.getSources(categoryId))
        }
    }

    override suspend fun getArticles(sourceId: String): List<Article> =
        remoteDataSource.getArticles(sourceId).map {
            it.toArticle()
        }
}