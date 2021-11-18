package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class ViewCarDetails : AppCompatActivity() {
    private lateinit var changeCarDetailsButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_car_details)
        changeCarDetailsButton = findViewById(R.id.buttonChangeCarDetails)
        changeCarDetailsButton.setOnClickListener {
            val intent = Intent(this, ChangeCarDetails::class.java)
            startActivity(intent)
        }
    }
}