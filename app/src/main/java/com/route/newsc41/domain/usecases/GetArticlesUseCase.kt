package com.route.newsc41.domain.usecases

import com.route.newsc41.domain.model.Article
import com.route.newsc41.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private var newsRepository: NewsRepository) {

    suspend fun execute(sourceId: String): List<Article> = newsRepository.getArticles(sourceId)

}