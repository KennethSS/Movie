package com.solar.movie.presentation.movie.thumb

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.solar.movie.R

class MovieThumbViewModel : ViewModel() {

    private val _showMovieDetailLiveData = MutableLiveData<MovieThumbView>()
    val showMovieDetailLiveData: LiveData<MovieThumbView> = _showMovieDetailLiveData

    var callback: ((iv: AppCompatImageView, item: MovieThumbView) -> Unit)? = null


    fun showMovieDetail(view: View, thumb: MovieThumbView) {
        val poster = view.findViewById<AppCompatImageView>(R.id.movie_thumb_poster)
        val extras = FragmentNavigatorExtras(poster to thumb.name)

        //_showMovieDetailLiveData.value = thumb
        callback?.invoke(poster, thumb)

        //view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToMovieDetailFragment())

        /*view.findNavController().navigate(
                R.id.fragment_movie_detail,
                //MainFragmentDirections.actionMainFragmentToMovieDetailFragment()
                bundleOf("movieId" to thumb.id, "title" to thumb.name), // Bundle of args
                null, // NavOptions
                extras)*/
    }
}