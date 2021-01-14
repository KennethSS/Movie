package com.solar.movie

import android.os.Bundle
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.solar.library.binding.activity.BindingActivity
import com.solar.movie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    private lateinit var navController: NavController

    var bottomBarMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.mainContainer.findNavController().navigateUp()
        return true
    }
}