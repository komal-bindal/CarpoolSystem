package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class MapActivity : AppCompatActivity() {
    private lateinit var pickupPoint: AutoCompleteTextView
    private lateinit var dropPoint: AutoCompleteTextView
    private lateinit var pool: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map2)
        pool = findViewById(R.id.buttonPool)
        pickupPoint = findViewById(R.id.autocompleteTextViewsource)
        dropPoint = findViewById(R.id.autocompleteTextViewdestination)
        val pickup = resources.getStringArray(R.array.locations)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pickup)
        pickupPoint.setAdapter(arrayAdapter)
        val drop = resources.getStringArray(R.array.locations)
        val arrayAdapter2 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drop)
        dropPoint.setAdapter(arrayAdapter2)
        pool.setOnClickListener {
            val intent = Intent(this@MapActivity, EntranceLayout::class.java)
            startActivity(intent)
        }
    }
}