package com.solar.movie

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.solar.library.binding.activity.BindingActivity
import com.solar.movie.databinding.ActivityMainBinding
import com.solar.movie.remote.MOVIE_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        if (MOVIE_KEY.isEmpty()) {
            Toast.makeText(this, R.string.api_key_empty, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.mainContainer.findNavController().navigateUp()
        return true
    }
}