package com.example.newsapp.presenter.search_news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.presenter.adapter.NewsAdapter
import com.example.newsapp.presenter.adapter.OnItemClickListener
import com.example.newsapp.presenter.base.BaseFragment
import com.example.newsapp.presenter.news.NewsViewModel
import com.example.newsapp.util.extensions.string.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchNewsFragment : BaseFragment<FragmentSearchNewsBinding, SearchNewsViewModel>(),
    OnItemClickListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSearchNewsBinding
        get() = FragmentSearchNewsBinding::inflate

    override val newsViewModel: SearchNewsViewModel by sharedViewModel()


    private val newsAdapter by lazy { NewsAdapter(this) }

    override fun init() {
        setUpRecyclerView()
        observeSearchNewsLiveData()
        observeErrorLiveData()
        searchNews()
    }

    private fun searchNews() {
        binding.searchNewsEditText.addTextChangedListener {
            isShownProgressBar(it!!.isNotBlank())
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
        }
    }

    private fun isShownProgressBar(boolean: Boolean) {
        binding.customProgressBar.isVisible = boolean
    }

    override fun onItemCLick(article: Articles) {
        SearchNewsFragmentDirections.actionSearchNewsFragmentToNewsDetailFragment(article)
    }

}