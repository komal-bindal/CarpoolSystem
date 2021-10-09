package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class UsersScreen : AppCompatActivity() {
    private lateinit var buttondriver: Button
    private lateinit var buttonpassenger: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_screen)
        buttondriver = findViewById(R.id.buttonDriver)
        buttonpassenger = findViewById(R.id.buttonPassenger)
        buttondriver.setOnClickListener {
            val intent = (Intent(this@UsersScreen, SignupScreen1::class.java))
            intent.putExtra("User", "Driver")
            startActivity(intent)
            finish()

        }
        buttonpassenger.setOnClickListener {
            val intent = (Intent(this@UsersScreen, SignupScreen1::class.java))
            intent.putExtra("User", "Passenger")
            startActivity(intent)
            finish()

        }


    }
}