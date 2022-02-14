package com.example.newsapp.ui.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentOnBoardingBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.on_boarding.factory.CustomFragmentFactory
import com.example.newsapp.ui.on_boarding.factory.OnBoardingItemListener
import com.example.newsapp.ui.on_boarding.screens.OnBoardingFinishFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingMiddleFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingStartFragment
import com.example.newsapp.ui.on_boarding.view_pager.ViewPagerAdapter

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>(),
    OnBoardingItemListener {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentOnBoardingBinding
        get() = FragmentOnBoardingBinding::inflate

    override fun getViewModelClass(): Class<OnBoardingViewModel> = OnBoardingViewModel::class.java

    private val customFragmentFactory by lazy { CustomFragmentFactory(this) }

    private val fragmentList = listOf<Fragment>(
        OnBoardingStartFragment { changeOnBoardingScreen() },
        OnBoardingMiddleFragment { changeOnBoardingScreen() },
        OnBoardingFinishFragment { finishOnBoardingScreen() }
    )
    private val myAdapter by lazy {
        ViewPagerAdapter(
            fragmentList,
            this
        )
    }

    override fun init() {
        newsViewModel.saveValue()
        setUpOnBoardingViewPager()
    }

    private fun setUpOnBoardingViewPager() {
        binding.onBoardingViewPager.adapter = myAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = customFragmentFactory
        super.onCreate(savedInstanceState)
    }

    override fun changeOnBoardingScreen() {
        binding.onBoardingViewPager.currentItem += CHANGE_VIEW_PAGER_SCREEN
    }

    override fun finishOnBoardingScreen() {
        findNavController().navigate(R.id.action_onBoardingFragment_to_newsFragment)
    }


    companion object {
        private const val CHANGE_VIEW_PAGER_SCREEN = 1
    }
}