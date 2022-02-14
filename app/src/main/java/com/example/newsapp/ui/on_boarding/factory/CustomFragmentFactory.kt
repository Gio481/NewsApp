package com.example.newsapp.ui.on_boarding.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.newsapp.ui.on_boarding.screens.OnBoardingFinishFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingMiddleFragment
import com.example.newsapp.ui.on_boarding.screens.OnBoardingStartFragment

class CustomFragmentFactory(private val onBoardingItemListener: OnBoardingItemListener) :
    FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            OnBoardingStartFragment::class.java.name -> {
                OnBoardingStartFragment { onBoardingItemListener.changeOnBoardingScreen() }
            }
            OnBoardingMiddleFragment::class.java.name -> {
                OnBoardingMiddleFragment { onBoardingItemListener.changeOnBoardingScreen() }
            }
            OnBoardingFinishFragment::class.java.name -> {
                OnBoardingFinishFragment { onBoardingItemListener.finishOnBoardingScreen() }
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}