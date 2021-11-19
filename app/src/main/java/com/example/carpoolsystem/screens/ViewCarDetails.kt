package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ViewCarDetails : AppCompatActivity() {
    private val CAR_COLLECTION = "car"
    private val CAR_NUMBER = "carNumber"
    private val CAR_MODEL = "carModel"
    private val CAR_MAKE = "carMake"
    private val OWNER = "owner"
    private lateinit var carNumberTextView: TextView
    private lateinit var carMakeTextView: TextView
    private lateinit var carModelTextView: TextView
    private lateinit var changeCarDetailsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_car_details)
        changeCarDetailsButton = findViewById(R.id.buttonChangeCarDetails)
        carNumberTextView = findViewById(R.id.carNumber)
        carMakeTextView = findViewById(R.id.carMake)
        carModelTextView = findViewById(R.id.carModel)


        changeCarDetailsButton.setOnClickListener {
            val intent = Intent(this, ChangeCarDetails::class.java)
            startActivity(intent)
        }
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val docReference = FirebaseFirestore.getInstance().collection(CAR_COLLECTION)
            .whereEqualTo(OWNER, firebaseUser?.uid)
        docReference.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                var list = querySnapshot.documents
                for (item in list) {
                    val carNumber = item?.data?.get(CAR_NUMBER).toString()
                    val carMake = item?.data?.get(CAR_MAKE).toString()
                    val carModel = item?.data?.get(CAR_MODEL).toString()
                    if (!carNumber.isEmpty()) {
                        carNumberTextView.text = carNumber
                    }
                    if (!carMake.isEmpty()) {
                        carMakeTextView.text = carMake
                    }
                    if (!carModel.isEmpty()) {
                        carModelTextView.text = carModel
                    }
                }
            }
        }
    }
}