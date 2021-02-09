package com.solar.movie.presentation

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.solar.library.binding.activity.BindingActivity
import com.solar.movie.R
import com.solar.movie.databinding.ActivityNavBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationTestActivity : BindingActivity(){
    private val binding: ActivityNavBinding by binding(R.layout.activity_nav)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {

        }

        val host = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        binding.bottomNav.setupWithNavController(host.navController)
    }
}