package com.route.newsc41

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.route.newsc41.databinding.LayoutArticleItemBinding
import com.route.newsc41.domain.model.Article

class ArticlesAdapter : Adapter<ArticlesAdapter.ArticlesViewHolder>() {
    var articlesList = emptyList<Article>()

    fun submitList(articles: List<Article>) {
        articlesList = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutArticleItemBinding.inflate(layoutInflater, parent, false)
        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article = articlesList[position]
        holder.binding.articleTitle.text = article.title
        holder.binding.articleAuthor.text = article.author
        holder.binding.articleDate.text = article.date

        Glide.with(holder.binding.articleImage)
            .load(article.urlToImage)
            .into(holder.binding.articleImage)
    }

    override fun getItemCount(): Int = articlesList.size
    class ArticlesViewHolder(val binding: LayoutArticleItemBinding) :
        ViewHolder(binding.root)
}