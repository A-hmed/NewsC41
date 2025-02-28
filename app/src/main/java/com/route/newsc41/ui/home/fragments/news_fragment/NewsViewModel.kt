package com.route.newsc41.ui.home.fragments.news_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsc41.api.ApiManager
import com.route.newsc41.api.model.ArticleDM
import com.route.newsc41.api.model.SourceDM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    var sourcesApi = MutableLiveData<Resource<List<SourceDM?>>>()
    var articlesApi = MutableLiveData<Resource<List<ArticleDM>>>()
    fun getSources(categoryId: String) {
        sourcesApi.value = Resource.LoadingState()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sourcesResponseDM =
                    ApiManager.getWebServices().getSources(ApiManager.apiKey, categoryId)
                sourcesApi.value = Resource.SuccessState(sourcesResponseDM.sources!!)
            } catch (e: Throwable) {
                sourcesApi.value =
                    Resource.ErrorState(e.message ?: "Something went wrong")
            }
        }
    }

    fun getArticles(sourceId: String) {
        articlesApi.value = Resource.LoadingState()
        viewModelScope.launch {
            try {
                val articlesResponse =
                    ApiManager.getWebServices().getArticles(ApiManager.apiKey, sourceId)
                articlesApi.value =
                    Resource.SuccessState(articlesResponse.articles ?: emptyList())
            } catch (e: Throwable) {
                articlesApi.value =
                    Resource.ErrorState(e.message ?: "Something went wrong")

            }

        }
    }
}

sealed class Resource<T> {
    class LoadingState<E> : Resource<E>()
    class SuccessState<X>(var data: X) : Resource<X>()
    class ErrorState<Y>(val errorMessage: String) : Resource<Y>()
}


