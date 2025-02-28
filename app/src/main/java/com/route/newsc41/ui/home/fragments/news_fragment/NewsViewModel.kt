package com.route.newsc41.ui.home.fragments.news_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.newsc41.api.ApiManager
import com.route.newsc41.api.model.ArticleDM
import com.route.newsc41.api.model.ArticlesResponseDM
import com.route.newsc41.api.model.SourceDM
import com.route.newsc41.api.model.SourcesResponseDM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    var sourcesApi = MutableLiveData<Resource<List<SourceDM?>>>()
    var articlesApi = MutableLiveData<Resource<List<ArticleDM>>>()
    fun getSources(categoryId: String) {
        sourcesApi.value = Resource.LoadingState()
        ApiManager.getWebServices().getSources(ApiManager.apiKey, categoryId)
            .enqueue(object : Callback<SourcesResponseDM> {
                override fun onResponse(
                    p0: Call<SourcesResponseDM>,
                    response: Response<SourcesResponseDM>
                ) {
                    if (response.isSuccessful) {
                        sourcesApi.value = Resource.SuccessState(response.body()!!.sources!!)
                    } else {
                        val errorResponse =
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                SourcesResponseDM::class.java
                            )
                        sourcesApi.value =
                            Resource.ErrorState(errorResponse.message ?: "Something went wrong")
                    }

                }

                override fun onFailure(p0: Call<SourcesResponseDM>, p1: Throwable) {
                    sourcesApi.value =
                        Resource.ErrorState(p1.message ?: "Something went wrong")
                }
            })


    }

    fun getArticles(sourceId: String) {
        articlesApi.value = Resource.LoadingState()
        ApiManager.getWebServices().getArticles(ApiManager.apiKey, sourceId)
            .enqueue(object : Callback<ArticlesResponseDM> {
                override fun onResponse(
                    p0: Call<ArticlesResponseDM>,
                    response: Response<ArticlesResponseDM>
                ) {
                    if (response.isSuccessful) {
                        articlesApi.value =
                            Resource.SuccessState(response.body()?.articles ?: emptyList())
                    } else {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            ArticlesResponseDM::class.java
                        )
                        articlesApi.value =
                            Resource.ErrorState(errorResponse.message ?: "Something went wrong")
                    }
                }

                override fun onFailure(p0: Call<ArticlesResponseDM>, ex: Throwable) {
                    articlesApi.value =
                        Resource.ErrorState(ex.message ?: "Something went wrong")

                }

            })
    }
}

sealed class Resource<T> {
    class LoadingState<E> : Resource<E>()
    class SuccessState<X>(var data: X) : Resource<X>()
    class ErrorState<Y>(val errorMessage: String) : Resource<Y>()
}


