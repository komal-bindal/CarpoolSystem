package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth

class PassengersProfile : AppCompatActivity() {
    lateinit var phonenumberChange: TextView
    lateinit var passwordChange: TextView
    lateinit var logout:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passengers_profile)
        phonenumberChange=findViewById(R.id.phonenumberchangepassenger)
        passwordChange=findViewById(R.id.passwordchangepassenger)
        logout=findViewById(R.id.buttonlogoutpassenger)

        phonenumberChange.setOnClickListener {
            val intent= Intent(this@PassengersProfile,SignInScreen::class.java)
            startActivity(intent)
        }

        passwordChange.setOnClickListener {
            val intent= Intent(this@PassengersProfile,ChangePassword::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this@PassengersProfile,UsersScreen::class.java)
            startActivity(intent)
        }
    }
}