package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EntranceLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance_layout)
        supportActionBar?.hide()
        Handler().postDelayed({
            val currentUser = FirebaseAuth.getInstance().currentUser
            Log.d("current", currentUser?.uid.toString())
            if (!currentUser?.uid.toString().isEmpty() && currentUser?.uid != null) {
                val docRef = FirebaseFirestore.getInstance().collection("users")
                    .whereEqualTo("uid", currentUser.uid.toString())
                docRef.get().addOnSuccessListener { query ->
                    if (!query.isEmpty) {
                        for (item in query.documents) {
                            val user = item.data?.get("user").toString()
                            if (user == "Driver") {
                                val intent = Intent(this@EntranceLayout, Dashboard::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                val intent =
                                    Intent(this@EntranceLayout, DashboardPassenger::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    } else {
                        val intent = Intent(this@EntranceLayout, Animation::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                val intent = Intent(this@EntranceLayout, Animation::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}