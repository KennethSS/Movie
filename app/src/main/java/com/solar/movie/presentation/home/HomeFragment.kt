package com.solar.movie.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.MainFragment
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*observe(movieThumbViewModel.showMovieDetailLiveData) { pair ->
            Log.d("HomeFragment", "Observe")

            val poster = pair.first
            val thumb = pair.second
            val extras = FragmentNavigatorExtras(poster to thumb.name)
            findNavController().navigate(
                R.id.fragment_movie_detail,
                //MainFragmentDirections.actionMainFragmentToMovieDetailFragment()
                bundleOf("movieId" to thumb.id, "title" to thumb.name), // Bundle of args
                null, // NavOptions
                extras)
        }*/

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
                        //MainFragmentDirections.actionMainFragmentToMovieDetailFragment()
                        bundleOf("movieId" to thumb.id, "title" to thumb.name), // Bundle of args
                        options, // NavOptions
                        extras)
                }
            }
        }
    }

    val observer = Observer<MovieThumbView> {
        Log.d("HomeFragment", "Observe")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("onViewCreated", "onViewCreated")

        movieThumbViewModel.showMovieDetailLiveData.removeObserver(observer)
        movieThumbViewModel.showMovieDetailLiveData.observe(viewLifecycleOwner, observer)

        bind?.let { bind ->
            observe(homeViewModel.popularMovieLiveData) {
                when(it) {
                    is NetworkState.Success -> {
                        bind.homeListView.adapter = HomeListAdapter(movieThumbViewModel).apply {
                            addAll(listOf(MovieListView("Popular", it.item)))
                            (view.parent as? ViewGroup)?.doOnPreDraw {
                                startPostponedEnterTransition()
                            }
                        }
                    }
                    else -> { }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("onDestroyView", "onDestroyView")
    }
}