package com.solar.movie

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.solar.library.binding.activity.BindingActivity
import com.solar.movie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    private lateinit var navController: NavController

    var bottomBarMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {

            /*bottomBar.onItemSelected = { position ->

                when(position) {
                    0 -> navController.navigate(R.id.action_fragment_home_to_fragment_favorite)
                    1 -> navController.navigate(R.id.action_fragment_home_to_fragment_favorite)
                    2 -> navController.navigate(R.id.action_fragment_home_to_fragment_favorite)
                }
            }*/

            //NavigationUI.setupWithNavController()
            //navController = Navigation.findNavController(this@MainActivity, R.id.main_fragment_container)
            //val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
            //navController = navHostFragment.navController
            supportActionBar?.hide()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.mainContainer.findNavController().navigateUp()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("onCreateOptionsMenu", "onCreateOptionsMenu")
        //menuInflater.inflate(R.menu.nav_bottom, menu)


        return true
    }
}