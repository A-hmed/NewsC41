package com.route.newsc41.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.newsc41.R
import com.route.newsc41.databinding.ActivityMainBinding
import com.route.newsc41.ui.home.fragments.categories.CategoriesFragment
import com.route.newsc41.ui.home.fragments.news_fragment.NewsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var categoriesFragment = CategoriesFragment {
        showFragment(NewsFragment(it.id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        showFragment(categoriesFragment)

    }

    private fun initListeners() {
        binding.appBarLayout.icToggle.setOnClickListener {
            if (binding.draweLayout.isOpen) {
                binding.draweLayout.close()
            } else {
                binding.draweLayout.open()
            }

        }
        val homeTv = binding.sideNavigationView.rootView.findViewById<TextView>(R.id.homeTv)
        homeTv.setOnClickListener {
            showFragment(categoriesFragment)
            binding.draweLayout.close()
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}