package com.example.kinofanplus.viewmodel

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.ActivityMainBinding
import com.example.kinofanplus.view.ADULT_KEY
import com.example.kinofanplus.view.isAdultDefault
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val buttonNavigationPanel: BottomNavigationView by lazy { binding.buttonNavigationPanel }

    private val navigationController by lazy { findNavController(R.id.navigation_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(custom_toolbar)

        buttonNavigationPanel.setupWithNavController(navigationController)

        setDefaultSettings()
    }

    private fun setDefaultSettings() {
        this.getPreferences(MODE_PRIVATE)?.getBoolean(ADULT_KEY, isAdultDefault)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                navigationController.navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}