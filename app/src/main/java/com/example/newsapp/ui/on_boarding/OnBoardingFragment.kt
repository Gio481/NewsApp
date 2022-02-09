package com.example.newsapp.ui.on_boarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentOnBoardingBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingEndFragment
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
        OnBoardingEndFragment()
    )

    private val myAdapter by lazy { ViewPagerAdapter(fragmentList,this) }


    override fun init() {
        binding.onBoardingViewPager.adapter = myAdapter
        binding.onBoardingViewPager.isUserInputEnabled = false
    }



}