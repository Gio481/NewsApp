package com.example.newsapp.ui.saved_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.OnItemClickListener
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.Constants.ARTICLES_KEY

class SavedNewsFragment : BaseFragment<FragmentSavedNewsBinding, SavedNewsViewModel>(),
    OnItemClickListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSavedNewsBinding
        get() = FragmentSavedNewsBinding::inflate

    override fun getViewModelClass(): Class<SavedNewsViewModel> = SavedNewsViewModel::class.java

    private val newsAdapter by lazy { NewsAdapter(this) }
    override fun init() {
        setUpRecyclerView()
        observeNewsLiveData()
    }

    private fun setUpRecyclerView() {
        with(binding.savedNewsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun observeNewsLiveData() {
        newsViewModel.allNewsLiveData.observe(viewLifecycleOwner) { articles ->
            newsAdapter.submitList(articles)

        }
    }

    override fun onItemCLick(article: Articles) {
        findNavController().navigate(
            R.id.action_savedNewsFragment_to_newsDetailFragment,
            Bundle().apply { putParcelable(ARTICLES_KEY, article) }
        )
    }


}