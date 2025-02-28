package com.route.newsc41.ui.home.fragments.news_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.route.newsc41.ArticlesAdapter
import com.route.newsc41.api.model.ArticleDM
import com.route.newsc41.api.model.SourceDM
import com.route.newsc41.databinding.FragmentNewsBinding

class NewsFragment(val categoryId: String) : Fragment() {
    lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: ArticlesAdapter
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }
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
        setUpObservers()
        viewModel.getSources(categoryId)
    }

    private fun setUpObservers() {
        viewModel.sourcesApi.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.SuccessState -> {
                    hideError()
                    hideLoading()
                    showTabs(it.data)
                }

                is Resource.ErrorState -> {
                    hideLoading()
                    showError(it.errorMessage)
                }

                is Resource.LoadingState -> {
                    hideError()
                    showLoading()
                }
            }
        }
        viewModel.articlesApi.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.ErrorState -> {
                    hideLoading()
                    showError(it.errorMessage)
                }

                is Resource.LoadingState -> {
                    hideError()
                    showLoading()
                }

                is Resource.SuccessState -> {
                    hideError()
                    hideLoading()
                    showArticles(it.data)
                }
            }
        }
    }

    private fun initArticlesRecyclerView() {
        adapter = ArticlesAdapter()
        binding.articlesRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.getArticles(tab!!.tag as String)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewModel.getArticles(tab!!.tag as String)
            }

        })
    }

    private fun showArticles(articles: List<ArticleDM>) {
        binding.articlesRecyclerView.isVisible = true
        adapter.submitList(articles)
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

    fun hideLoading() {
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