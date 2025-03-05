package com.route.newsc41.data.repository.news_repository.data_sources.remote_data_source

import com.route.newsc41.data.api.web_services.WebServices
import com.route.newsc41.data.model.ArticleDM
import com.route.newsc41.data.model.SourceDM
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val apiServices: WebServices) :
    NewsRemoteDataSource {

    override suspend fun getSources(categoryId: String): List<SourceDM> {
        return apiServices.getSources(id = categoryId).sources!!
    }

    override suspend fun getArticles(sourceId: String): List<ArticleDM> {
        return apiServices.getArticles(sourceId = sourceId).articles!!
    }
}