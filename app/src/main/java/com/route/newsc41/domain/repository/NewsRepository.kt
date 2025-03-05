package com.route.newsc41.domain.repository

import com.route.newsc41.domain.model.Article
import com.route.newsc41.domain.model.Source


interface NewsRepository {
    suspend fun getSources(categoryId: String): List<Source>

    suspend fun getArticles(sourceId: String): List<Article>
}