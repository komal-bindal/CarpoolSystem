package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth

class Dashboard : AppCompatActivity() {
    private lateinit var addride: TextView
    private lateinit var addDetails: TextView
    private lateinit var logout: TextView
    private lateinit var profile: TextView
    private lateinit var forgetPassword: TextView
    private lateinit var manageRides: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        addride = findViewById(R.id.textView4searchride)
        addDetails = findViewById(R.id.textView7addfeedback)
        logout = findViewById(R.id.textView8logout)
        forgetPassword = findViewById(R.id.textView6forgetpassword)
        profile = findViewById(R.id.textView3profile)
        manageRides = findViewById(R.id.textViewmanageridesoption)

        addride.setOnClickListener {
            val intent = Intent(this, AddRideScreen::class.java)
            startActivity(intent)


        }
        addDetails.setOnClickListener {
            val intent = Intent(this, ChangeCarDetails::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, UsersScreen::class.java)
            startActivity(intent)


        }
        forgetPassword.setOnClickListener {
            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)

        }
        profile.setOnClickListener {

            val intent = Intent(this, PassengersProfile::class.java)
            startActivity(intent)


        }
        manageRides.setOnClickListener {

            val intent = Intent(this, DriversProfile::class.java)
            startActivity(intent)


        }
    }
}