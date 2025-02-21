package com.route.newsc41.ui.home.fragments.news_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.route.newsc41.ArticlesAdapter
import com.route.newsc41.api.ApiManager
import com.route.newsc41.api.model.ArticleDM
import com.route.newsc41.api.model.ArticlesResponseDM
import com.route.newsc41.api.model.SourceDM
import com.route.newsc41.api.model.SourcesResponseDM
import com.route.newsc41.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment(val categoryId: String) : Fragment() {
    lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: ArticlesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initArticlesRecyclerView()
        getSources()
    }

    private fun initArticlesRecyclerView() {
        adapter = ArticlesAdapter()
        binding.articlesRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                getArticles(tab!!.tag as String)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                getArticles(tab!!.tag as String)
            }

        })
    }

    private fun getArticles(sourceId: String) {
        showLoading()
        ApiManager.getWebServices().getArticles(ApiManager.apiKey, sourceId)
            .enqueue(object : Callback<ArticlesResponseDM> {
                override fun onResponse(
                    p0: Call<ArticlesResponseDM>,
                    response: Response<ArticlesResponseDM>
                ) {
                    hideLoading()
                    hideError()
                    if (response.isSuccessful) {
                        showArticles(response.body()?.articles ?: emptyList())
                    } else {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            ArticlesResponseDM::class.java
                        )
                        showError(errorResponse.message ?: "Something went wrong")
                    }
                }

                override fun onFailure(p0: Call<ArticlesResponseDM>, ex: Throwable) {
                    hideLoading()
                    showError(ex.message ?: "Something went wrong")
                }

            })
    }

    private fun showArticles(articles: List<ArticleDM>) {
        binding.articlesRecyclerView.isVisible = true
        adapter.submitList(articles)
    }

    private fun getSources() {
        binding.loadingView.isVisible = true
        ApiManager.getWebServices().getSources(ApiManager.apiKey, categoryId)
            .enqueue(object : Callback<SourcesResponseDM> {
                override fun onResponse(
                    p0: Call<SourcesResponseDM>,
                    response: Response<SourcesResponseDM>
                ) {
                    hideLoading()
                    hideError()
                    if (response.isSuccessful) {
                        showTabs(response.body()!!.sources!!)
                    } else {
                        val errorResponse =
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                SourcesResponseDM::class.java
                            )
                        showError(errorResponse.message ?: "Something went wrong")
                    }

                }

                override fun onFailure(p0: Call<SourcesResponseDM>, p1: Throwable) {
                    binding.loadingView.isVisible = false
                    showError(p1.message ?: "Something went wrong")
                }
            })
    }

    private fun showError(error: String) {
        binding.errorView.root.isVisible = true
        binding.errorView.errorText.text = error
    }

    private fun hideError() {
        binding.errorView.root.isVisible = false
    }

    private fun showLoading() {
        binding.articlesRecyclerView.isVisible = false
        binding.loadingView.isVisible = true
    }

    private fun hideLoading() {
        binding.loadingView.isVisible = false
    }

    private fun showTabs(sources: List<SourceDM?>) {

        sources.forEach {
            val tab = binding.tabLayout.newTab()
            tab.text = it?.name ?: "Unkown"
            tab.tag = it?.id
            binding.tabLayout.addTab(tab)
        }
    }
}