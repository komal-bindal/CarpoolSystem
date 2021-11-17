package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class Dashboard : AppCompatActivity() {
    private lateinit var addride: TextView
    private lateinit var addDetails: TextView
    private lateinit var logout: TextView
    private lateinit var profile: TextView
    private lateinit var forgetPassword: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        addride = findViewById(R.id.textView4)
        addDetails = findViewById(R.id.textView7)
        logout = findViewById(R.id.textView8)
        forgetPassword = findViewById(R.id.textView6)
        profile = findViewById(R.id.textView3)
        addride.setOnClickListener {
            val intent = Intent(this@Dashboard, AddRideScreen::class.java)
            startActivity(intent)


        }
        addDetails.setOnClickListener {
            val intent = Intent(this@Dashboard, ChangeCarDetails::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener {
            val intent = Intent(this@Dashboard, UsersScreen::class.java)
            startActivity(intent)


        }
        forgetPassword.setOnClickListener {
            val intent = Intent(this@Dashboard, ChangePassword::class.java)
            startActivity(intent)

        }
        profile.setOnClickListener {

                val intent = Intent(this@Dashboard, PassengersProfile::class.java)
                startActivity(intent)





        }
    }
}