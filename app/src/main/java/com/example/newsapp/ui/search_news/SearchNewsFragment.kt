package com.example.newsapp.ui.search_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.OnItemClickListener
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.Constants.ARTICLES_KEY
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
            newsViewModel.searchNews(it.toString())
        }
    }

    private fun observeErrorLiveData() {
        newsViewModel.errorLiveData.observe(viewLifecycleOwner) {
            it.showToast(requireContext())
        }
    }

    private fun observeSearchNewsLiveData() {
        newsViewModel.successNewsLiveData.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it.articles)
        }
    }

    private fun setUpRecyclerView() {
        with(binding.searchNewsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    override fun onItemCLick(article: Articles) {
        findNavController().navigate(
            R.id.action_searchNewsFragment_to_newsDetailFragment,
            Bundle().apply { putParcelable(ARTICLES_KEY, article) }
        )
    }

}