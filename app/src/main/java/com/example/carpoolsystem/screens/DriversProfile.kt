package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DriversProfile : AppCompatActivity() {
    private val USERS_COLLECTION = "users"
    private val EMAIL_ID = "emailId"
    private val NAME = "name"
    private val PHONE_NUMBER = "phoneNumber"
    private val UID = "uId"
    private val USER = "user"
    lateinit var phonenumberChangeDriver: TextView
    lateinit var nameDriver: TextView
    lateinit var passwordChangeDriver: TextView
    lateinit var logoutDriver: Button

    lateinit var feedbackForm: TextView


    lateinit var emailIdDriver: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_profile)

        phonenumberChangeDriver=findViewById(R.id.phonenumberdriver)
        passwordChangeDriver=findViewById(R.id.passwordchangedriver)
        logoutDriver = findViewById(R.id.buttonlogoutdriver)
        feedbackForm = findViewById(R.id.feedbackForm)
        emailIdDriver = findViewById(R.id.emailofDriver)
        nameDriver = findViewById(R.id.fullNameDriver)

        phonenumberChangeDriver.setOnClickListener {
            val intent = Intent(this@DriversProfile, SignInScreen::class.java)
            startActivity(intent)
        }
        passwordChangeDriver.setOnClickListener {
            val intent = Intent(this@DriversProfile, ChangePassword::class.java)
            startActivity(intent)
        }

        feedbackForm.setOnClickListener {
            val intent = Intent(this@DriversProfile, ChangeCarDetails::class.java)
            startActivity(intent)
        }
        logoutDriver.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@DriversProfile, UsersScreen::class.java)
            startActivity(intent)
        }
        emailIdDriver.setOnClickListener {
            if (emailIdDriver.text.toString().equals("Add your email id")) {
                val intent = Intent(this@DriversProfile, EmailIdEmpty::class.java)
                startActivity(intent)
            }
        }
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val docReference = FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
            .whereEqualTo(USER, firebaseUser?.uid)
        docReference.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                var list = querySnapshot.documents
                for (item in list) {
                    val name = item?.data?.get(NAME).toString()
                    val emailId = item?.data?.get(EMAIL_ID).toString()
                    val phoneNumber = item?.data?.get(PHONE_NUMBER).toString()
                    val uId = item?.data?.get(UID).toString()
                    val user = item?.data?.get(USER).toString()
                    if (!name.isEmpty()) {
                        nameDriver.text = name
                    } else {
                        nameDriver.text = user.uppercase()
                    }
                    if (!emailId.isEmpty()) {
                        emailIdDriver.text = emailId
                    }
                    if (!phoneNumber.isEmpty()) {
                        phonenumberChangeDriver.text = emailId
                    }


                }
            }
        }
    }
}