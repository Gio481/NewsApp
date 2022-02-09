package com.example.newsapp.ui.news

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.OnItemClickListener
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.extensions.string.showToast

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsVideModel>(), OnItemClickListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentNewsBinding
        get() = FragmentNewsBinding::inflate

    override fun getViewModelClass(): Class<NewsVideModel> = NewsVideModel::class.java

    private val newsAdapter by lazy { NewsAdapter(this) }

    override fun init() {
        binding.businessNewsTextView.paint.isUnderlineText = true
        isShownProgressBar(true)
        setUpRecyclerView()
        observeSuccessLiveData()
        observeErrorLiveData()
        getNewsFromCategory()
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

    private fun getNewsFromCategory() {
        with(binding) {

            determineNewsCategory(
                businessNewsTextView,
                listOf(techNewsTextView, sportNewsTextView, scienceNewsTextView),
                CATEGORY_BUSINESS
            )
            determineNewsCategory(
                techNewsTextView,
                listOf(businessNewsTextView, sportNewsTextView, scienceNewsTextView),
                CATEGORY_TECH
            )
            determineNewsCategory(
                sportNewsTextView,
                listOf(techNewsTextView, businessNewsTextView, scienceNewsTextView),
                CATEGORY_SPORTS
            )
            determineNewsCategory(
                scienceNewsTextView,
                listOf(techNewsTextView, sportNewsTextView, businessNewsTextView),
                CATEGORY_SCIENCE
            )
        }
    }

    private fun determineNewsCategory(
        textView: TextView,
        otherCategory: List<TextView>,
        category: String
    ) {
        textView.setOnClickListener {
            changeTextView(textView, otherCategory)
            getNews(category)
        }
    }

    private fun getNews(category: String) {
        isShownProgressBar(true)
        newsViewModel.getCustomCategoryNews(category)
    }

    private fun changeTextView(textView: TextView, otherCategory: List<TextView>) {
        textView.paint.isUnderlineText = true
        textView.setTextColor(Color.parseColor("#FF4E4E"))
        otherCategory.forEach {
            it.setTextColor(Color.parseColor("#5E5C5B"))
            it.paint.isUnderlineText = false
        }
    }

    private fun setUpRecyclerView() {
        with(binding.newsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter

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

    companion object {
        private const val CATEGORY_BUSINESS = "business"
        private const val CATEGORY_SPORTS = "sports"
        private const val CATEGORY_TECH = "technology"
        private const val CATEGORY_SCIENCE = "science"
    }
}