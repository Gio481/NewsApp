package com.example.newsapp.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsRowLayoutBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.util.ItemsDiffUtil
import com.example.newsapp.util.extensions.image_view.setImage

class NewsAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Articles, NewsAdapter.ViewHolder>(ItemsDiffUtil<Articles>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NewsRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), onItemClickListener)
    }

    class ViewHolder(private val binding: NewsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(articles: Articles, onItemClickListener: OnItemClickListener) {
            with(binding) {
                newsTitleTextView.text = articles.title
                newsSubjectTextView.text = articles.source.name
                newsDateTextView.text = articles.getNewsPublishedTime()
                newsImageView.setImage(articles.urlToImage)
                newsRowLayout.setOnClickListener {
                    onItemClickListener.onItemCLick(articles)
                }
            }
        }
    }
}