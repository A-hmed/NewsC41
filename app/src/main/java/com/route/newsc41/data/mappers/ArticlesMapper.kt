package com.route.newsc41.data.mappers

import com.route.newsc41.data.model.ArticleDM
import com.route.newsc41.domain.model.Article


fun ArticleDM.toArticle(): Article =
    Article(
        date = publishedAt ?: "", author = author ?: "", title = title ?: "",
        urlToImage = urlToImage ?: ""
    )