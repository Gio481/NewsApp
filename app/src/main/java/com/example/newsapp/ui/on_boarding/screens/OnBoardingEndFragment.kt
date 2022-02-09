package com.example.newsapp.ui.on_boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentOnBoardingEndBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.on_boarding.OnBoardingViewModel

class OnBoardingEndFragment : BaseFragment<FragmentOnBoardingEndBinding, OnBoardingViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentOnBoardingEndBinding
        get() = FragmentOnBoardingEndBinding::inflate

    override fun getViewModelClass(): Class<OnBoardingViewModel> = OnBoardingViewModel::class.java

    override fun init() {
        binding.finishOnBoardingButton.setOnClickListener {view->
            view.findNavController().navigate(R.id.action_onBoardingFragment_to_newsFragment)
        }
    }

}