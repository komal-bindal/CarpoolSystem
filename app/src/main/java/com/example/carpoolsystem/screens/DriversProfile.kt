package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class DriversProfile : AppCompatActivity() {
    lateinit var phonenumberChangeDriver: TextView
    lateinit var passwordChangeDriver: TextView
    lateinit var logoutDriver: Button
    lateinit var changeCarDetails:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_profile)
        phonenumberChangeDriver=findViewById(R.id.phonenumberdriver)
        passwordChangeDriver=findViewById(R.id.passwordchangedriver)
        logoutDriver=findViewById(R.id.buttonlogoutdriver)
        changeCarDetails=findViewById(R.id.changecardetails)
        phonenumberChangeDriver.setOnClickListener {
            val intent= Intent(this@DriversProfile,SignInScreen::class.java)
            startActivity(intent)
        }
        passwordChangeDriver.setOnClickListener {
            val intent= Intent(this@DriversProfile,ChangePassword::class.java)
            startActivity(intent)
        }
        changeCarDetails.setOnClickListener {
            val intent= Intent(this@DriversProfile,ChangeCarDetails::class.java)
            startActivity(intent)
        }
        logoutDriver.setOnClickListener {
            val intent= Intent(this@DriversProfile,UsersScreen::class.java)
            startActivity(intent)
        }
    }
}