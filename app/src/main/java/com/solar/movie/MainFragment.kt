package com.solar.movie

import android.os.Bundle
import android.view.View
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.databinding.FragmentMainBinding

class MainFragment : BindingFragment<FragmentMainBinding>(){

    override val layoutRes: Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onViewCreated(bind: FragmentMainBinding, savedInstanceState: Bundle?) {

    }
}