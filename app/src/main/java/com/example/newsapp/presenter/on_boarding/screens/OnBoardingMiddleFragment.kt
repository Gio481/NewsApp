package com.example.newsapp.presenter.on_boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentOnBoardingMiddleBinding
import com.example.newsapp.presenter.on_boarding.screens.base.BaseOnBoardingFragment

class OnBoardingMiddleFragment(private val action: () -> Unit) :
    BaseOnBoardingFragment<FragmentOnBoardingMiddleBinding>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> FragmentOnBoardingMiddleBinding
        get() = FragmentOnBoardingMiddleBinding::inflate

    override fun init() {
        binding.middleOnBoardingNextButton.setOnClickListener {
            action.invoke()
        }
    }


}