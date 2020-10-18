package com.mehmetalemdar.permcomb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigation:BottomNavigationView = findViewById<BottomNavigationView>(R.id.calculteBottom)
        val navFragment = findNavController(R.id.fragment)

        bottomNavigation.setupWithNavController(navFragment)




    }
}