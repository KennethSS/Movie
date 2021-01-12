package com.solar.movie.presentation.movie.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.NetworkState
import com.solar.movie.R
import com.solar.movie.databinding.FragmentMovieDetailBinding
import com.solar.movie.extension.observe
import com.solar.movie.remote.UNKNOWN
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MovieDetailFragment : BindingFragment<FragmentMovieDetailBinding>() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private val movieId: Int by lazy { arguments?.getInt("movieId", 0) ?: 0 }
    private val title: String by lazy { arguments?.getString("title") ?: UNKNOWN }

    override val layoutRes: Int = R.layout.fragment_movie_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move).apply {
                duration = 300
            }
    }

    override fun onViewCreated(bind: FragmentMovieDetailBinding, savedInstanceState: Bundle?) {
        bind.movieDetailPoster.transitionName = title
        bind.movieDetailTitle.text = title
        postponeEnterTransition()
        observe(movieDetailViewModel.movieDetailLiveData) { result ->
            when (result) {
                is NetworkState.Success -> {
                    bind.movie = result.item
                    Log.d("MovieDetailActivity", result.item.toString())

                    Glide.with(bind.movieDetailPoster)
                        .load(result.item.poster)
                        //.apply(RequestOptions().dontTransform())
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                startPostponedEnterTransition()
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                startPostponedEnterTransition()
                                return false
                            }
                        })
                        .into(bind.movieDetailPoster)
                }
                else -> {
                }
            }
        }
        movieDetailViewModel.getMovieDetail(movieId)
    }
}