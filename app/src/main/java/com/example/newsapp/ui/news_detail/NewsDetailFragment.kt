package com.example.newsapp.ui.news_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.extensions.image_view.setImage

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>() {
    private val args: NewsDetailFragmentArgs by navArgs()
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentNewsDetailBinding
        get() = FragmentNewsDetailBinding::inflate

    override fun getViewModelClass(): Class<NewsDetailViewModel> = NewsDetailViewModel::class.java

    override fun init() {
        setListener()
        createArticle()
    }

    private fun checkArticle() {
        if (args.article.url in newsViewModel.urlList.value!!) {
            Toast.makeText(requireContext(), "gio", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "nika", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createArticle() {
        with(binding) {
            newsNameTextView.text = args.article.source.name
            newsTitleTextView.text = args.article.title
            newsDescriptionTextView.text = args.article.description
            newsImageView.setImage(args.article.urlToImage)
        }
    }

    private fun setListener() {
        binding.favoriteNewsActionButton.setOnClickListener {
            newsViewModel.determineOperation(args.article)
            checkArticle()
        }
    }
}