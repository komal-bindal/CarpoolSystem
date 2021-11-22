package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MapActivity : AppCompatActivity() {

    private lateinit var pickupPoint: AutoCompleteTextView
    private lateinit var dropPoint: AutoCompleteTextView
    private lateinit var pool: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map2)

        pool = findViewById(R.id.buttonPool1)
        pickupPoint = findViewById(R.id.autocompleteTextViewsource)
        dropPoint = findViewById(R.id.autocompleteTextViewdestination)

        val locations = arrayListOf<String>("")
        val pickupArrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations)
        pickupPoint.setAdapter(pickupArrayAdapter)

        val dropArrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations)
        dropPoint.setAdapter(dropArrayAdapter)

        FirebaseFirestore.getInstance().collection("locations").get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val list = querySnapshot.documents
                    for (item in list) {
                        locations.add(item.data?.get("location").toString())
                    }
                }
            }




        pool.setOnClickListener {
            val dropLoc = dropPoint.text.toString()
            val pickUpLoc = pickupPoint.text.toString()
            if (dropLoc.isEmpty() || pickUpLoc.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please add pickup or drop point",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (dropPoint.text.toString().equals(pickupPoint.text.toString())) {
                Toast.makeText(
                    this,
                    "Please add different location in pickup and drop point",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (!locations.contains(dropLoc)) {
                    addLocationForReview(dropLoc)
                    Toast.makeText(
                        this,
                        "drop location is added for review since it is not present in our database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (!locations.contains(pickUpLoc)) {
                    addLocationForReview(pickUpLoc)
                    Toast.makeText(
                        this,
                        "pickup location is added for review since it is not present in our database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val intent = Intent(this@MapActivity, AddRideScreen::class.java)
                intent.putExtra("dropPoint", dropPoint.text.toString())
                intent.putExtra("pickUpPoint", pickupPoint.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun addLocationForReview(loc: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val locationHashMap = hashMapOf<String, String>(
            "uid" to currentUser?.uid.toString(),
            "location" to loc
        )
        FirebaseFirestore.getInstance().collection("ReviewLocations").add(locationHashMap)
    }
}