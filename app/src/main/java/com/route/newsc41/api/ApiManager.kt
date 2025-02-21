package com.route.newsc41.api

import com.route.newsc41.api.web_services.WebServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiManager {
    private const val baseUrl = "https://newsapi.org"
    const val apiKey = "a2803275cc264f5ab82151862011361a"
    private var retrofit = Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWebServices() = retrofit.create(WebServices::class.java)
    private fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        //TODO: ADD INTERCEPTOR FOR API KEY

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return client
    }

}