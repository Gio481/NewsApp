package com.example.newsapp.ui.on_boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentOnBoardingFinishBinding
import com.example.newsapp.ui.on_boarding.screens.base.BaseOnBoardingFragment

class OnBoardingFinishFragment(private val action:()->Unit) : BaseOnBoardingFragment<FragmentOnBoardingFinishBinding>() {


    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> FragmentOnBoardingFinishBinding
        get() = FragmentOnBoardingFinishBinding::inflate

    override fun init() {
        binding.finishOnBoardingButton.setOnClickListener {
            action.invoke()
        }
    }

}