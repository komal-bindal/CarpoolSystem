package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

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

        val pickupLocations = resources.getStringArray(R.array.locations)
        val pickupArrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pickupLocations)
        pickupPoint.setAdapter(pickupArrayAdapter)

        val dropLocations = resources.getStringArray(R.array.locations)
        val dropArrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dropLocations)
        dropPoint.setAdapter(dropArrayAdapter)

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
                val intent = Intent(this@MapActivity, AddRideScreen::class.java)
                intent.putExtra("dropPoint", dropPoint.text.toString())
                intent.putExtra("pickUpPoint", pickupPoint.text.toString())
                startActivity(intent)
            }
        }
    }
}