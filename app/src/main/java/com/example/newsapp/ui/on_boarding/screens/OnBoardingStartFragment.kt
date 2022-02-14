package com.example.newsapp.ui.on_boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentOnBoardingStartBinding
import com.example.newsapp.ui.on_boarding.screens.base.BaseOnBoardingFragment

class OnBoardingStartFragment(private val doSomething:()->Unit) : BaseOnBoardingFragment<FragmentOnBoardingStartBinding>() {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> FragmentOnBoardingStartBinding
        get() = FragmentOnBoardingStartBinding::inflate

    override fun init() {
        binding.startOnBoardingNextButton.setOnClickListener {
            doSomething.invoke()
        }
    }



}