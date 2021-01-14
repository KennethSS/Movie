package com.solar.movie.extension

import androidx.lifecycle.*

fun <T> MediatorLiveData<T>.addSourceList(vararg liveDataArgument: MutableLiveData<*>, onChanged: () -> T) {
    liveDataArgument.forEach {
        this.addSource(it) { value = onChanged() }
    }
}

fun <T> LifecycleOwner.observe(liveData: LiveData<out T>, onChanged: (v: T) -> Unit) {
    liveData.observe(this, Observer { onChanged(it) })
}