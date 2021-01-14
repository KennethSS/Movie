package com.solar.movie.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.NetworkState
import com.solar.movie.R
import com.solar.movie.databinding.FragmentFavoriteBinding
import com.solar.movie.extension.observe
import com.solar.recyclerview.adapter.normal.DataBindingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BindingFragment<FragmentFavoriteBinding>() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val favoriteItemViewModel: FavoriteItemViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_favorite

    override fun onViewCreated(bind: FragmentFavoriteBinding, savedInstanceState: Bundle?) {
        bind.itemVm = favoriteItemViewModel
        bind.favoriteVm = favoriteViewModel

        observe(favoriteItemViewModel.deleteFavoriteLiveData) { favoriteMovieView ->
            (bind.favoriteListView.adapter as DataBindingAdapter<FavoriteMovieView>).let {
                it.remove(favoriteMovieView)
            }
        }

        observe(favoriteViewModel.favoriteMovieListLiveData) {
            when(it) {
                is NetworkState.Success -> {
                    bind.favoriteListView.adapter = object :
                        DataBindingAdapter<FavoriteMovieView>(viewModel = favoriteItemViewModel) {}.apply {
                        submitList(it.item)
                    }
                }
            }
        }
    }
}