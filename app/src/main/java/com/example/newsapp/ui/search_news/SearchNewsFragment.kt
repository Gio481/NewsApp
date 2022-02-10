package com.example.newsapp.ui.search_news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.OnItemClickListener
import com.example.newsapp.ui.adapter.OnScrollListener
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.extensions.string.showToast

class SearchNewsFragment : BaseFragment<FragmentSearchNewsBinding, SearchNewsViewModel>(),
    OnItemClickListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSearchNewsBinding
        get() = FragmentSearchNewsBinding::inflate

    override fun getViewModelClass(): Class<SearchNewsViewModel> = SearchNewsViewModel::class.java

    private val newsAdapter by lazy { NewsAdapter(this) }

    override fun init() {
        setUpRecyclerView()
        observeSearchNewsLiveData()
        observeErrorLiveData()
        searchNews()
    }

    private fun searchNews() {
        binding.searchNewsEditText.addTextChangedListener {
            isShownProgressBar(true)
            newsViewModel.searchNews(it.toString())
        }
    }

    private fun observeErrorLiveData() {
        newsViewModel.errorLiveData.observe(viewLifecycleOwner) {
            isShownProgressBar(false)
            it.showToast(requireContext())
        }
    }

    private fun observeSearchNewsLiveData() {
        newsViewModel.successNewsLiveData.observe(viewLifecycleOwner) {
            isShownProgressBar(false)
            newsAdapter.submitList(it.articles.toList())
        }
    }

    private fun setUpRecyclerView() {
        with(binding.searchNewsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            addOnScrollListener(
                OnScrollListener(
                    { newsViewModel.searchNews(binding.searchNewsEditText.text.toString()) },
                    20,
                    LinearLayoutManager(requireContext())
                )
            )
        }
    }

    private fun isShownProgressBar(boolean: Boolean) {
        binding.customProgressBar.isVisible = boolean
    }

    override fun onItemCLick(article: Articles) {
        SearchNewsFragmentDirections.actionSearchNewsFragmentToNewsDetailFragment(article)
    }

}