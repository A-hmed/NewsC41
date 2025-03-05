package com.route.newsc41.data.repository.news_repository.data_sources.local_data_source

import com.route.newsc41.data.model.SourceDM

interface NewsLocalDataSource {
    suspend fun getSources(categoryId: String): List<SourceDM>

    suspend fun saveSources(sources: List<SourceDM>)
}