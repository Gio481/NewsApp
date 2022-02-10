package com.example.newsapp.ui.on_boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentOnBoardingMiddleBinding
import com.example.newsapp.ui.on_boarding.screens.base.BaseOnBoardingFragment

class OnBoardingMiddleFragment : BaseOnBoardingFragment<FragmentOnBoardingMiddleBinding>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> FragmentOnBoardingMiddleBinding
        get() = FragmentOnBoardingMiddleBinding::inflate

    override fun init() {
        binding.middleOnBoardingNextButton.setOnClickListener {
            action.invoke()

        }
    }

    companion object {
        lateinit var action: () -> Unit
    }
}