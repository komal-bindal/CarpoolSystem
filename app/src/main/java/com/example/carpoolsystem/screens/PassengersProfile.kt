package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PassengersProfile : AppCompatActivity() {
    lateinit var phonenumberChange: TextView
    lateinit var passwordChange: TextView
    lateinit var logout: Button
    lateinit var name: TextView
    lateinit var emailTextView: TextView
    lateinit var phone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passengers_profile)

        phonenumberChange = findViewById(R.id.phonenumberchangepassenger)
        passwordChange = findViewById(R.id.passwordchangepassenger)
        logout = findViewById(R.id.buttonlogoutpassenger)
        name = findViewById(R.id.fullNamePassenger)
        emailTextView = findViewById(R.id.emailofpassenger)
        phone = findViewById(R.id.phoneNumberTextView)
        fetchDetailsFromDatabase()

        emailTextView.setOnClickListener {
            if (emailTextView.text.toString().equals("Add your email id")) {
                val intent = Intent(this, EmailIdEmpty::class.java)
                startActivity(intent)
                finish()
            }
        }

        phonenumberChange.setOnClickListener {
            val intent = Intent(this@PassengersProfile, AddNewPhoneNumber::class.java)
            if (phonenumberChange.text.toString().equals("Add phone number")) {
                Log.d("Passenger", "add")
                intent.putExtra("Phone", "add")
            } else {
                Log.d("Passenger", "change")
                intent.putExtra("phone", "change")
            }
            startActivity(intent)
            finish()
        }

        passwordChange.setOnClickListener {
            val intent = Intent(this@PassengersProfile, ChangePassword::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@PassengersProfile, UsersScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun fetchDetailsFromDatabase() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val docRef = FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo("uid", firebaseUser?.uid.toString())
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.isEmpty == false) {
                var list = querySnapshot.documents
                for (item in list) {
                    val userName = item.data?.get("name").toString()
                    val emailId = item.data?.get("emailId").toString()
                    val phoneNumber = item.data?.get("phoneNumber").toString()
                    if (!userName.isEmpty()) {
                        name.text = userName
                    }
                    if (emailId.isEmpty() == false) {
                        emailTextView.text = emailId
                    }
                    if (phoneNumber.isEmpty() == false) {
                        phone.text = phoneNumber
                    } else {
                        phonenumberChange.text = "Add phone number"
                    }
                }
            }
        }
    }
}