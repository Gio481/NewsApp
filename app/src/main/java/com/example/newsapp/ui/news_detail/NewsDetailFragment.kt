package com.example.newsapp.ui.news_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.extensions.image_view.setImage
import kotlinx.coroutines.launch

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>() {
    private val args: NewsDetailFragmentArgs by navArgs()

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentNewsDetailBinding
        get() = FragmentNewsDetailBinding::inflate

    private lateinit var navController: NavController


    override fun getViewModelClass(): Class<NewsDetailViewModel> = NewsDetailViewModel::class.java

    override fun init() {
        newsViewModel.checkArticle(args.article.url)
        setUpToolBar()
        observeButtonBackgroundLiveData()
        createArticle()
        setListener()
    }

    private fun observeButtonBackgroundLiveData() {
        newsViewModel.backgroundLiveData.observe(viewLifecycleOwner) {
            binding.favoriteNewsActionButton.setImageResource(it)
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
            lifecycleScope.launch {
                if (newsViewModel.checkSavedArticle(args.article.url)) {
                    binding.favoriteNewsActionButton.setImageResource(R.drawable.ic_favorite)
                } else {
                    binding.favoriteNewsActionButton.setImageResource(R.drawable.ic_delete)
                }
            }
            newsViewModel.determineOperation(args.article, args.article.url)

        }
    }

    private fun setUpToolBar() {
        binding.newsDetailBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}