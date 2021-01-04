package com.solar.movie.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.NetworkState
import com.solar.movie.R
import com.solar.movie.databinding.FragmentHomeBinding
import com.solar.movie.extension.observe
import com.solar.movie.presentation.movie.list.MovieListView
import com.solar.movie.presentation.movie.thumb.MovieThumbView
import com.solar.movie.presentation.movie.thumb.MovieThumbViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val movieThumbViewModel: MovieThumbViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind?.let { bind ->
            observe(homeViewModel.popularMovieLiveData) {
                when(it) {
                    is NetworkState.Success -> {
                        bind.homeListView.adapter = HomeListAdapter(movieThumbViewModel).apply {
                            addAll(listOf(MovieListView("Popular", it.item)))
                        }
                    }
                    else -> { }
                }
            }
        }
    }
}