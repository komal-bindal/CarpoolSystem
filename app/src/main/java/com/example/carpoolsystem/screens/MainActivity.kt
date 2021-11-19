package com.example.carpoolsystem.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.screens.map.MapActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = applicationContext?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        val intent = Intent(this@MainActivity,  AddRideScreen::class.java)

        startActivity(intent)
        finish()
    }
}