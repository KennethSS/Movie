package com.solar.movie

import android.os.Bundle
import android.view.*
import androidx.navigation.findNavController
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.databinding.FragmentMainBinding

class MainFragment : BindingFragment<FragmentMainBinding>(){

    override val layoutRes: Int = R.layout.fragment_main


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(bind: FragmentMainBinding, savedInstanceState: Bundle?) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        bind?.let {
            activity?.menuInflater?.inflate(R.menu.nav_bottom, menu)
            it.bottomBar.setupWithNavController(menu, it.mainFragmentContainer.findNavController())
        }
    }
}