package com.solar.movie

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.solar.library.binding.fragment.BindingFragment
import com.solar.movie.databinding.FragmentMainBinding
import me.ibrahimsn.lib.SmoothBottomBar

class MainFragment : BindingFragment<FragmentMainBinding>(){

    override val layoutRes: Int = R.layout.fragment_main


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(bind: FragmentMainBinding, savedInstanceState: Bundle?) {
        /*bind.bottomBar.onItemSelected = { position ->
            val navController = findNavController()
            when (position) {
                0 -> navController.navigate(R.id.fragment_home)
                1 -> navController.navigate(R.id.fragment_favorite)
                2 -> navController.navigate(R.id.fragment_my)
            }
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)



        if (bind == null) {
            Log.d("onCreateOptionsMenu", "MainFragment bind is null")
        }

        bind?.let {
            val bottomBarMenu = (activity as MainActivity).bottomBarMenu
            activity?.menuInflater?.inflate(R.menu.nav_bottom, menu)

            it.bottomBar.setupWithNavController(menu, it.mainFragmentContainer.findNavController())
        }
    }

    fun getBottomBar(): SmoothBottomBar? = bind?.bottomBar
}