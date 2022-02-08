package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.OnItemClickListener
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.Constants.ARTICLES_KEY
import com.example.newsapp.util.extensions.string.showToast

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsVideModel>(), OnItemClickListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentNewsBinding
        get() = FragmentNewsBinding::inflate

    override fun getViewModelClass(): Class<NewsVideModel> = NewsVideModel::class.java

    private val newsAdapter by lazy { NewsAdapter(this) }
    override fun init() {
        setUpRecyclerView()
        observeErrorLiveData()
        observeSuccessLiveData()
        setListener()
    }

    private fun setListener() {
        with(binding) {
            businessNewsTextView.setOnClickListener {
                newsViewModel.getCustomCategoryNews("business")
            }
            techNewsTextView.setOnClickListener {
                newsViewModel.getCustomCategoryNews("technology")
            }
            sportNewsTextView.setOnClickListener {
                newsViewModel.getCustomCategoryNews("sport")
            }
            scienceNewsTextView.setOnClickListener {
                newsViewModel.getCustomCategoryNews("science")
            }
        }
    }


    private fun observeSuccessLiveData() {
        newsViewModel.successNewsLiveData.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it.articles)
        }
    }

    private fun observeErrorLiveData() {
        newsViewModel.errorLiveData.observe(viewLifecycleOwner) {
           it.showToast(requireContext())
        }
    }


    private fun setUpRecyclerView() {
        with(binding.newsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter

        }
    }

    override fun onItemCLick(article: Articles) {
        findNavController().navigate(
            NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(article)

        )
    }

}