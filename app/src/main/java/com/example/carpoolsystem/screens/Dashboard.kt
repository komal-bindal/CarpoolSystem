package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class Dashboard : AppCompatActivity() {
    private lateinit var addride:TextView
    private lateinit var addDetails: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        addride=findViewById(R.id.textView4)
        addDetails=findViewById(R.id.textView7)
        addride.setOnClickListener{
            val intent =Intent(this@Dashboard,AddRideScreen::class.java)
            startActivity(intent)


        }
        addDetails.setOnClickListener{
            val intent =Intent(this@Dashboard,VehicleDetailsAdditionScreen::class.java)
            startActivity(intent)


        }

    }
}