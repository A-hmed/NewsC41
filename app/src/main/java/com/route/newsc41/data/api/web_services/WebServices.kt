package com.route.newsc41.data.api.web_services

import com.route.newsc41.data.api.ApiManager
import com.route.newsc41.data.model.ArticlesResponseDM
import com.route.newsc41.data.model.SourcesResponseDM
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("/v2/everything")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = ApiManager.apiKey,
        @Query("sources") sourceId: String
    ): ArticlesResponseDM

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String = ApiManager.apiKey,
        @Query("category") id: String
    ): SourcesResponseDM
}