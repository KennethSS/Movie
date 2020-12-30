package com.solar.movie

import android.content.Intent
import android.os.Bundle
import com.solar.library.binding.activity.BindingActivity
import com.solar.movie.databinding.ActivityMainBinding
import com.solar.movie.presentation.detail.MovieDetailActivity

class MainActivity : BindingActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainTv.setOnClickListener {
            startActivity(Intent(this, MovieDetailActivity::class.java))
        }
    }
}