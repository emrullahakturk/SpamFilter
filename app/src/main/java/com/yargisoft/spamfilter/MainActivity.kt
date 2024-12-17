package com.yargisoft.spamfilter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigationrail.NavigationRailView
import com.yargisoft.spamfilter.databinding.ActivityMainBinding
import com.yargisoft.spamfilter.navigation.NavigationConstants.TOP_LEVEL_DESTINATIONS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {




    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navigationRailView: NavigationRailView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigation: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        uiBinding()
        setNavigation()
        navigationCloser()
    }

    private fun uiBinding() {
        toolbar = binding.toolbar
        drawerLayout = binding.drawerLayout
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navigationRailView = binding.navigationRailView
        navController = navHostFragment.navController
        bottomNavigation = binding.bottomNavigation
    }

    private fun setNavigation() {
        appBarConfiguration = AppBarConfiguration(
            TOP_LEVEL_DESTINATIONS,
            drawerLayout
        )
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
        toolbar.setupWithNavController(navController, drawerLayout)
        navigationRailView.setupWithNavController(navController)
        bottomNavigation.setupWithNavController(navController)
    }


    private fun navigationCloser() {
        navigationRailView.setOnItemSelectedListener { item ->
            val handled = NavigationUI.onNavDestinationSelected(item, navController)
            if (handled) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            handled
        }
    }


}