package com.example.newsapp.ui.news_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.extensions.image_view.setImage

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>() {
    private val args: NewsDetailFragmentArgs by navArgs()
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentNewsDetailBinding
        get() = FragmentNewsDetailBinding::inflate

    override fun init() {

        setListener()
        with(binding) {
            newsNameTextView.text = args.article.source.name
            newsTitleTextView.text = args.article.title
            newsDescriptionTextView.text = args.article.description
            newsImageView.setImage(args.article.urlToImage)
        }
    }

    private fun setListener() {
        binding.favoriteNewsActionButton.setOnClickListener {
            newsViewModel.insertArticle(args.article)
        }
    }

    override fun getViewModelClass(): Class<NewsDetailViewModel> = NewsDetailViewModel::class.java

}