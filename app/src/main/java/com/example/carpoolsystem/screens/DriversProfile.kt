package com.example.carpoolsystem.screens

import android.content.Intent
import android.net.Uri
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
    private lateinit var phonenumberChangeDriver: TextView
    private lateinit var nameDriver: TextView
    private lateinit var passwordChangeDriver: TextView
    private lateinit var logoutDriver: Button
    private lateinit var feedbackForm: TextView
    private lateinit var emailIdDriver: TextView
    private var FEEDBACK_URL = "https://forms.gle/2zD9AoMWGegbTwmg8"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_profile)

        phonenumberChangeDriver = findViewById(R.id.phonenumberdriver)
        passwordChangeDriver = findViewById(R.id.passwordchangedriver)
        logoutDriver = findViewById(R.id.buttonlogoutdriver)
        feedbackForm = findViewById(R.id.feedbackForm)
        emailIdDriver = findViewById(R.id.emailofDriver)
        nameDriver = findViewById(R.id.fullNameDriver)

        passwordChangeDriver.setOnClickListener {
            val intent = Intent(this@DriversProfile, ChangePassword::class.java)
            startActivity(intent)
        }

        feedbackForm.setOnClickListener {
            var uri = Uri.parse(FEEDBACK_URL)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        logoutDriver.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@DriversProfile, UsersScreen::class.java)
            startActivity(intent)
            finish()
        }

        phonenumberChangeDriver.setOnClickListener {
            val intent = Intent(this@DriversProfile, SignInScreen::class.java)
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