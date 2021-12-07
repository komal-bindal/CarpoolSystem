package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class WaitingActivity : AppCompatActivity() {
    private lateinit var getStartedNow: Button
    private lateinit var image: ImageView
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting)
        getStartedNow = findViewById(R.id.button)
        getStartedNow.setOnClickListener {
            val intent = Intent(this, SignupScreen1::class.java)
            startActivity(intent)
            finish()
        }
    }
}