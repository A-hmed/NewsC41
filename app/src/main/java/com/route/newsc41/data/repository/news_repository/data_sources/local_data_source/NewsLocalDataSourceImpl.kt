package com.route.newsc41.data.repository.news_repository.data_sources.local_data_source

import com.route.newsc41.data.database.dao.SourcesDao
import com.route.newsc41.data.model.SourceDM
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(private var sourcesDao: SourcesDao) :
    NewsLocalDataSource {
    override suspend fun getSources(categoryId: String): List<SourceDM> {
        return sourcesDao.getSources(categoryId)
    }

    override suspend fun saveSources(sources: List<SourceDM>) {
        sourcesDao.saveSources(sources)
    }
}