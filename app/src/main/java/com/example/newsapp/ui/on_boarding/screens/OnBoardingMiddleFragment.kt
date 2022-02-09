package com.example.newsapp.ui.on_boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentOnBoardingMiddleBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.on_boarding.OnBoardingViewModel

class OnBoardingMiddleFragment :
    BaseFragment<FragmentOnBoardingMiddleBinding, OnBoardingViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentOnBoardingMiddleBinding
        get() = FragmentOnBoardingMiddleBinding::inflate

    override fun getViewModelClass(): Class<OnBoardingViewModel> = OnBoardingViewModel::class.java

    override fun init() {
        binding.middleOnBoardingNextButton.setOnClickListener {
        }
    }

}