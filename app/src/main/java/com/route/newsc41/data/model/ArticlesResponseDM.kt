package com.route.newsc41.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesResponseDM(
    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticleDM>? = null,
) : BaseResponse()