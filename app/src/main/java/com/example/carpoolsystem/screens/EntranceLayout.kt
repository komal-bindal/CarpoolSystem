package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class EntranceLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance_layout)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@EntranceLayout, UsersScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}