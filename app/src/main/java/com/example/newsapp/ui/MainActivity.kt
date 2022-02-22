package com.example.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNav()

    }

    private fun setUpBottomNav() {
        navController = this.findNavController(R.id.mainNavHostFragment)
        binding.bottomNavView.setupWithNavController(navController)
        setListener()
    }

    private fun setListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment -> binding.bottomAppBar.isVisible = false
                R.id.newsDetailFragment -> binding.bottomAppBar.isVisible = false
                R.id.onBoardingFragment -> binding.bottomAppBar.isVisible = false
                else -> binding.bottomAppBar.isVisible = true
            }
        }
    }
}