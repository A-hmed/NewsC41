package com.route.newsc41.ui.home.fragments.news_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsc41.domain.model.Article
import com.route.newsc41.domain.model.Source
import com.route.newsc41.domain.usecases.GetArticlesUseCase
import com.route.newsc41.domain.usecases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private var getSourcesUseCase: GetSourcesUseCase,
    private var getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    var sourcesApi = MutableLiveData<Resource<List<Source?>>>()
    var articlesApi = MutableLiveData<Resource<List<Article>>>()

    fun getSources(categoryId: String) {
        sourcesApi.postValue(Resource.LoadingState())
        viewModelScope.launch {
            try {
                val sources = getSourcesUseCase.execute(categoryId)
                sourcesApi.postValue(Resource.SuccessState(sources))
            } catch (e: Throwable) {
                sourcesApi.postValue(
                    Resource.ErrorState(e.message ?: "Something went wrong")
                )
            }
        }
    }

    fun getArticles(sourceId: String) {
        articlesApi.value = Resource.LoadingState()
        viewModelScope.launch {
            try {
                val articles = getArticlesUseCase.execute(sourceId)
                articlesApi.value =
                    Resource.SuccessState(articles)
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


