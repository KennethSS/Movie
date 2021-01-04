package com.solar.movie.presentation.my

import android.os.Bundle
import android.view.View
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.R
import com.solar.movie.databinding.FragmentMyBinding

class MyFragment : BindingFragment<FragmentMyBinding>() {
    override val layoutRes: Int = R.layout.fragment_my

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}