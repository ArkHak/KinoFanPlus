package com.example.kinofanplus.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val buttonNavigationPanel: BottomNavigationView by lazy { binding.buttonNavigationPanel }

    private val navigationController by lazy { findNavController(R.id.navigation_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        buttonNavigationPanel.setupWithNavController(navigationController)
    }
}