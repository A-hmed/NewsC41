package com.route.newsc41.domain.usecases

import com.route.newsc41.domain.model.Source
import com.route.newsc41.domain.repository.NewsRepository
import javax.inject.Inject

open class GetSourcesUseCase @Inject constructor(var newsRepository: NewsRepository) {
    suspend fun execute(category: String): List<Source> = newsRepository.getSources(category)
}