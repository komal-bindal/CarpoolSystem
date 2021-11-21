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
    private val UID = "uid"
    private val USER = "user"
    private lateinit var phonenumberChangeDriverTextView: TextView
    private lateinit var nameDriverTextView: TextView
    private lateinit var passwordChangeDriverTextView: TextView
    private lateinit var logoutDriverButton: Button
    private lateinit var feedbackFormTextView: TextView
    private lateinit var emailIdDriverTextView: TextView
    private lateinit var driverPhoneNumberTextView: TextView
    private var FEEDBACK_URL = "https://forms.gle/2zD9AoMWGegbTwmg8"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_profile)

        phonenumberChangeDriverTextView = findViewById(R.id.phonenumberdriver)
        passwordChangeDriverTextView = findViewById(R.id.passwordchangedriver)
        logoutDriverButton = findViewById(R.id.buttonlogoutdriver)
        feedbackFormTextView = findViewById(R.id.feedbackForm)
        emailIdDriverTextView = findViewById(R.id.emailofDriver)
        nameDriverTextView = findViewById(R.id.fullNameDriver)
        driverPhoneNumberTextView = findViewById(R.id.driverPhoneNumber)

        fetchDetailsFromDatabase()

        passwordChangeDriverTextView.setOnClickListener {
            val intent = Intent(this@DriversProfile, ChangePassword::class.java)
            startActivity(intent)
        }

        feedbackFormTextView.setOnClickListener {
            var uri = Uri.parse(FEEDBACK_URL)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        logoutDriverButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@DriversProfile, UsersScreen::class.java)
            startActivity(intent)
            finish()
        }

        phonenumberChangeDriverTextView.setOnClickListener {
            val intent = Intent(this, AddNewPhoneNumber::class.java)
            if (phonenumberChangeDriverTextView.text.toString().equals("Add phone number")) {
                intent.putExtra("Phone", "add")
            } else {
                intent.putExtra("phone", "change")
            }
            startActivity(intent)
            finish()
        }

        emailIdDriverTextView.setOnClickListener {
            if (emailIdDriverTextView.text.toString().equals("Add your email id")) {
                val intent = Intent(this, EmailIdEmpty::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun fetchDetailsFromDatabase() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val docRef = FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
            .whereEqualTo(UID, firebaseUser?.uid.toString())
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.isEmpty == false) {
                var list = querySnapshot.documents
                for (item in list) {
                    val userName = item.data?.get(NAME).toString()
                    val emailId = item.data?.get(EMAIL_ID).toString()
                    val phoneNumber = item.data?.get(PHONE_NUMBER).toString()
                    if (!userName.isEmpty()) {
                        nameDriverTextView.text = userName
                    }
                    if (emailId.isEmpty() == false) {
                        emailIdDriverTextView.text = emailId
                    }
                    if (phoneNumber.isEmpty() == false) {
                        driverPhoneNumberTextView.text = phoneNumber
                    } else {
                        passwordChangeDriverTextView.text = "Add phone number"
                    }
                }
            }
        }
    }
}