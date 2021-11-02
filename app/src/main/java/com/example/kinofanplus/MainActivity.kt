package com.example.kinofanplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kinofanplus.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonNavigationPanel: BottomNavigationView = binding.buttonNavigationPanel
        val navigationController = findNavController(R.id.navigation_fragment_container)
        buttonNavigationPanel.setupWithNavController(navigationController)

    }
}