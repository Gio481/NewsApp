package com.example.newsapp.ui.on_boarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentOnBoardingBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingFinishFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingMiddleFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingStartFragment
import com.example.newsapp.ui.on_boarding.view_pager.ViewPagerAdapter

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentOnBoardingBinding
        get() = FragmentOnBoardingBinding::inflate

    override fun getViewModelClass(): Class<OnBoardingViewModel> = OnBoardingViewModel::class.java

    private val fragmentList = listOf<Fragment>(
        OnBoardingStartFragment(),
        OnBoardingMiddleFragment(),
        OnBoardingFinishFragment()
    )

    private val myAdapter by lazy {
        ViewPagerAdapter(
            fragmentList,
            this
        )
    }

    override fun init() {
        onBoardingScreenAction()
        setUpOnBoardingViewPager()
    }


    private fun onBoardingScreenAction() {
        onBoardingStartScreenAction()
        onBoardingMiddleScreenAction()
        onBoardingFinishScreenAction()
    }

    private fun onBoardingStartScreenAction() {
        OnBoardingStartFragment.action =
            { binding.onBoardingViewPager.currentItem += CHANGE_VIEW_PAGER_SCREEN }
    }

    private fun onBoardingMiddleScreenAction() {
        OnBoardingMiddleFragment.action =
            { binding.onBoardingViewPager.currentItem += CHANGE_VIEW_PAGER_SCREEN }
    }

    private fun onBoardingFinishScreenAction() {
        OnBoardingFinishFragment.action =
            { findNavController().navigate(R.id.action_onBoardingFragment_to_newsFragment) }
    }

    private fun setUpOnBoardingViewPager() {
        binding.onBoardingViewPager.adapter = myAdapter
        binding.onBoardingViewPager.isUserInputEnabled = false
    }

    companion object {
        private const val CHANGE_VIEW_PAGER_SCREEN = 1
    }
}