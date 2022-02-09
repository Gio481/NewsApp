package com.example.newsapp.ui.on_boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentOnBoardingStartBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.on_boarding.OnBoardingViewModel

class OnBoardingStartFragment :
    BaseFragment<FragmentOnBoardingStartBinding, OnBoardingViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentOnBoardingStartBinding
        get() = FragmentOnBoardingStartBinding::inflate

    override fun getViewModelClass(): Class<OnBoardingViewModel> = OnBoardingViewModel::class.java

    override fun init() {
        binding.startOnBoardingNextButton.setOnClickListener {

        }
    }
}