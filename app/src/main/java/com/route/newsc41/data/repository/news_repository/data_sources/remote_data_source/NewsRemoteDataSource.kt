package com.route.newsc41.data.repository.news_repository.data_sources.remote_data_source

import com.route.newsc41.data.model.ArticleDM
import com.route.newsc41.data.model.SourceDM

interface NewsRemoteDataSource {
    suspend fun getSources(categoryId: String): List<SourceDM>

    suspend fun getArticles(sourceId: String): List<ArticleDM>
}