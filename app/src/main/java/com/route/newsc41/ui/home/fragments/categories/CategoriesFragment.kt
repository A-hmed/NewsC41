package com.route.newsc41.ui.home.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.newsc41.data.model.CategoryDM
import com.route.newsc41.databinding.FragmentCategoriesBinding


class CategoriesFragment(val onCategoryClick: (CategoryDM) -> Unit) : Fragment() {
    var categoriesAdapter = CategoriesAdapter(CategoryDM.categories, onCategoryClick)
    lateinit var binding: FragmentCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoriesRecyclerView()
    }

    private fun initCategoriesRecyclerView() {
        binding.categoriesRecycler.adapter = categoriesAdapter
    }

}