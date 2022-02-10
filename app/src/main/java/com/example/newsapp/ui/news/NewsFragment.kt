package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.OnItemClickListener
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.news.adapter.Category
import com.example.newsapp.ui.news.adapter.CategoryAdapter
import com.example.newsapp.ui.news.adapter.OnCategoryItemListener
import com.example.newsapp.util.extensions.string.showToast

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsVideModel>(), OnItemClickListener,
    OnCategoryItemListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentNewsBinding
        get() = FragmentNewsBinding::inflate

    override fun getViewModelClass(): Class<NewsVideModel> = NewsVideModel::class.java

    private val newsAdapter by lazy { NewsAdapter(this) }
    private val categoryAdapter by lazy { CategoryAdapter(this) }

    override fun init() {
        isShownProgressBar(true)
        setUpNewsRecyclerView()
        observeSuccessLiveData()
        observeErrorLiveData()
        setUpCategoriesRecyclerView()
        categoryAdapter.submitList(CATEGORIES)
    }


    private fun observeSuccessLiveData() {
        newsViewModel.successNewsLiveData.observe(viewLifecycleOwner) {
            isShownProgressBar(false)
            newsAdapter.submitList(it.articles)
        }
    }

    private fun observeErrorLiveData() {
        newsViewModel.errorLiveData.observe(viewLifecycleOwner) {
            isShownProgressBar(false)
            it.showToast(requireContext())
        }
    }

    private fun setUpNewsRecyclerView() {
        with(binding.newsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun setUpCategoriesRecyclerView() {
        with(binding.categoryRecyclerView) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

    private fun isShownProgressBar(boolean: Boolean) {
        binding.customProgressBar.isVisible = boolean
    }

    override fun onItemCLick(article: Articles) {
        findNavController().navigate(
            NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(article)
        )
    }

    override fun onCategoryItemClick(category: String) {
        isShownProgressBar(true)
        newsViewModel.getCustomCategoryNews(category)
    }


    companion object {
        val CATEGORIES = listOf(
            Category("business"),
            Category("sports"),
            Category("technology"),
            Category("science")
        )
        private const val PRESSED_CATEGORY_TEXT_COLOR = "#FF4E4E"
        private const val UNPRESSED_CATEGORY_TEXT_COLOR = "#5E5C5B"
    }


}