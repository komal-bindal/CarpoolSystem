package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class DashboardPassenger : AppCompatActivity() {
    private lateinit var searchRide: TextView
    private lateinit var addFeedback: TextView
    private lateinit var logout: TextView
    private lateinit var profile: TextView
    private lateinit var forgetPassword: TextView
    private lateinit var manageRides: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_passenger)
        searchRide = findViewById(R.id.textView4searchride)
        addFeedback = findViewById(R.id.textView7addfeedback)
        logout = findViewById(R.id.textView8logout)
        forgetPassword = findViewById(R.id.textView6forgetpassword)
        profile = findViewById(R.id.textView3profile)
        manageRides = findViewById(R.id.textViewmanageridesoption)
        searchRide.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, AddRideScreen::class.java)
            startActivity(intent)


        }
        addFeedback.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, ChangeCarDetails::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, UsersScreen::class.java)
            startActivity(intent)


        }
        forgetPassword.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, ChangePassword::class.java)
            startActivity(intent)

        }
        profile.setOnClickListener {

            val intent = Intent(this@DashboardPassenger, PassengersProfile::class.java)
            startActivity(intent)


        }
        manageRides.setOnClickListener {

            val intent = Intent(this@DashboardPassenger, DriversProfile::class.java)
            startActivity(intent)


        }
    }
}