package com.solar.movie.presentation.home

import androidx.lifecycle.ViewModel
import com.solar.movie.databinding.ItemMovieListBinding
import com.solar.recyclerview.adapter.holder.BindingHolder
import com.solar.recyclerview.adapter.normal.DataBindingAdapter

class HomeListAdapter(vm: ViewModel) : DataBindingAdapter<HomeItem>(viewModel = vm){
    //private val stateList = hashMapOf<String, Parcelable>()

    override fun onViewRecycled(holder: BindingHolder<HomeItem>) {
        super.onViewRecycled(holder)
        val bind = holder.binding
        if (bind is ItemMovieListBinding) {
            bind.movieListView.layoutManager?.onSaveInstanceState()?.let {
                bind.item?.let { item ->
                    //stateList[item.title] = it
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BindingHolder<HomeItem>, position: Int) {
        super.onBindViewHolder(holder, position)
        val bind = holder.binding

        if (bind is ItemMovieListBinding) {
            bind.item?.let { item ->
                /*stateList[item.title]?.let {
                    bind.movieListView.layoutManager?.onRestoreInstanceState(it)
                }*/
            }
        }
    }
}