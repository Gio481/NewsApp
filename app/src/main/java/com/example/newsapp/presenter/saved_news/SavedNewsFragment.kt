package com.example.newsapp.presenter.saved_news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.model.Articles
import com.example.newsapp.presenter.adapter.NewsAdapter
import com.example.newsapp.presenter.adapter.OnItemClickListener
import com.example.newsapp.presenter.base.BaseFragment
import com.example.newsapp.presenter.news.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SavedNewsFragment : BaseFragment<FragmentSavedNewsBinding, SavedNewsViewModel>(),
    OnItemClickListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSavedNewsBinding
        get() = FragmentSavedNewsBinding::inflate

    override val newsViewModel: SavedNewsViewModel by sharedViewModel()


    private val newsAdapter by lazy { NewsAdapter(this) }
    override fun init() {
        newsViewModel.getAllNews()
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
            SavedNewsFragmentDirections.actionSavedNewsFragmentToNewsDetailFragment(article)

        )
    }


}