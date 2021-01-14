package com.solar.movie.presentation.home

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.MainFragment
import com.solar.movie.NetworkState
import com.solar.movie.R
import com.solar.movie.databinding.FragmentHomeBinding
import com.solar.movie.extension.observe
import com.solar.movie.presentation.movie.list.MovieListView
import com.solar.movie.presentation.movie.thumb.MovieThumbViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val movieThumbViewModel: MovieThumbViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieThumbViewModel.callback = { poster, thumb ->
            parentFragment?.let { parentFragment ->
                val extras = FragmentNavigatorExtras(poster to thumb.name)

                val options = NavOptions.Builder()
                    .setEnterAnim(android.R.anim.fade_in)
                    .setExitAnim(android.R.anim.fade_out)
                    .build()
                parentFragment.parentFragment?.let { host ->
                    (host as MainFragment).findNavController().navigate(
                        R.id.movieDetailFragment,
                        bundleOf("movieId" to thumb.id, "title" to thumb.name), // Bundle of args
                        options, // NavOptions
                        extras)
                }
            }
        }
    }

    override fun onViewCreated(bind: FragmentHomeBinding, savedInstanceState: Bundle?) {
        observe(homeViewModel.popularMovieLiveData) {
            when(it) {
                is NetworkState.Success -> {
                    bind.homeListView.adapter = HomeListAdapter(movieThumbViewModel).apply {
                        addAll(listOf(MovieListView("Popular", it.item)))
                    }

                    bind.homeListView.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                }
                else -> { }
            }
        }
    }


}