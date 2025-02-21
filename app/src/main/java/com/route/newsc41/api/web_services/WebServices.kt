package com.route.newsc41.api.web_services

import com.route.newsc41.api.model.ArticlesResponseDM
import com.route.newsc41.api.model.SourcesResponseDM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("/v2/everything")
    fun getArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String
    ): Call<ArticlesResponseDM>

    @GET("/v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") id: String
    ): Call<SourcesResponseDM>
}