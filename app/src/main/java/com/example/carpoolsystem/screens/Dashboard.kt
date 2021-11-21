package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth

class Dashboard : AppCompatActivity() {
    private lateinit var driverProfileLayout: RelativeLayout
    private lateinit var driverAddRideLayout: RelativeLayout
    private lateinit var driverViewRequestLayout: RelativeLayout
    private lateinit var driverCarDetailsLayout: RelativeLayout
    private lateinit var driverLogoutLayout: RelativeLayout
    private lateinit var driverManageRideLayout: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        driverProfileLayout = findViewById(R.id.driverProfileLayout)
        driverAddRideLayout = findViewById(R.id.driverAddRideLayout)
        driverViewRequestLayout = findViewById(R.id.driverViewRequestLayout)
        driverCarDetailsLayout = findViewById(R.id.driverCarDetailsLayout)
        driverLogoutLayout = findViewById(R.id.driverLogoutLayout)
        driverManageRideLayout = findViewById(R.id.driverManageRideLayout)

        driverProfileLayout.setOnClickListener {
            val intent = Intent(this, DriversProfile::class.java)
            startActivity(intent)
        }

        driverAddRideLayout.setOnClickListener {
            val intent = Intent(this, AddRideScreen::class.java)
            startActivity(intent)
        }

        driverLogoutLayout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, UsersScreen::class.java)
            startActivity(intent)
            finish()
        }
        driverCarDetailsLayout.setOnClickListener {
            val intent = Intent(this, ViewCarDetails::class.java)
            startActivity(intent)
        }

        driverViewRequestLayout.setOnClickListener {
            val intent = Intent(this, DriversViewRequestScreen::class.java)
            startActivity(intent)
        }

        driverManageRideLayout.setOnClickListener {
            val intent = Intent(this, DriversProfile::class.java)
            startActivity(intent)
        }
    }
}