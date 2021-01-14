package com.solar.movie.presentation.movie.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.NetworkState
import com.solar.movie.R
import com.solar.movie.databinding.FragmentMovieDetailBinding
import com.solar.movie.extension.observe
import com.solar.movie.remote.UNKNOWN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BindingFragment<FragmentMovieDetailBinding>() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private val movieId: Int by lazy { arguments?.getInt("movieId", 0) ?: 0 }
    private val title: String by lazy { arguments?.getString("title") ?: UNKNOWN }

    override val layoutRes: Int = R.layout.fragment_movie_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_image_thumb_to_detail).apply {
                duration = 300
            }
    }

    override fun onViewCreated(bind: FragmentMovieDetailBinding, savedInstanceState: Bundle?) {
        bind.movieDetailPoster.transitionName = title
        bind.movieDetailTitle.text = title
        bind.vm = movieDetailViewModel

        postponeEnterTransition()
        observe(movieDetailViewModel.movieDetailLiveData) { result ->
            when (result) {
                is NetworkState.Success -> {
                    bind.movie = result.item
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