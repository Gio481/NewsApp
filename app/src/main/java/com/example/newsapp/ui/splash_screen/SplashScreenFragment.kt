package com.example.newsapp.ui.splash_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSplashScreenBinding
import com.example.newsapp.ui.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSplashScreenBinding
        get() = FragmentSplashScreenBinding::inflate

    override fun getViewModelClass(): Class<SplashScreenViewModel> =
        SplashScreenViewModel::class.java

    override fun init() {
        makeAnimation()
        observeSplashScreenLiveData()
    }

    private fun observeSplashScreenLiveData() {
        newsViewModel.splashScreenLiveData.observe(viewLifecycleOwner) {
            if (it.toBoolean()) {
                determineNavigation(NAVIGATE_NEWS)
            } else {
                determineNavigation(NAVIGATE_ON_BOARDING)
            }
        }
    }

    private fun makeAnimation() {
        with(binding.splashScreenLogo.animate()) {
            duration = ANIMATION_DURATION
            rotation(LOGO_ROTATION)
        }
    }

    private fun determineNavigation(destination: Int) {
        lifecycleScope.launch {
            delay(DELAY)
            findNavController().navigate(destination)
        }
    }

    companion object {
        private const val NAVIGATE_ON_BOARDING = R.id.action_splashScreenFragment_to_onBoardingFragment
        private const val NAVIGATE_NEWS = R.id.action_splashScreenFragment_to_newsFragment
        private const val DELAY = 3000L
        private const val ANIMATION_DURATION = 2500L
        private const val LOGO_ROTATION = 360f
    }

}