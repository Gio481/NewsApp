package com.example.newsapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.newsapp.ui.news_detail.NewsDetailFragment
import com.example.newsapp.ui.news_detail.NewsDetailViewModel

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> VB
    protected lateinit var newsViewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    abstract fun init()
    abstract fun getViewModelClass(): Class<VM>

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}