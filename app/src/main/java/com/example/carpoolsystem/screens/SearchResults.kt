package com.example.carpoolsystem.screens

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Ride
import com.example.carpoolsystem.screens.adapter.SearchRideAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class SearchResults : AppCompatActivity() {
    private lateinit var pickUpPointAutoCompleteTextView: AutoCompleteTextView
    private lateinit var dropPointAutoCompleteTextView: AutoCompleteTextView
    private lateinit var searchButton: Button
    private lateinit var searchResultsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        pickUpPointAutoCompleteTextView = findViewById(R.id.pickUpEt)
        dropPointAutoCompleteTextView = findViewById(R.id.dropEt)
        searchButton = findViewById(R.id.searchButton)
        searchResultsRecyclerView = findViewById(R.id.searchResultsRecyclerView)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(this)
        searchResultsRecyclerView.setHasFixedSize(true)

        val locations = arrayListOf<String>("")
        val pickupArrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations)
        pickUpPointAutoCompleteTextView.setAdapter(pickupArrayAdapter)

        val dropArrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations)
        dropPointAutoCompleteTextView.setAdapter(dropArrayAdapter)

        FirebaseFirestore.getInstance().collection("locations").get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val list = querySnapshot.documents
                    for (item in list) {
                        locations.add(item.data?.get("location").toString())
                    }
                }
            }

        searchButton.setOnClickListener {
            val dropLoc = dropPointAutoCompleteTextView.text.toString()
            val pickUpLoc = pickUpPointAutoCompleteTextView.text.toString()
            if (dropLoc.isEmpty() || pickUpLoc.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please add pickup or drop point",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (dropPointAutoCompleteTextView.text.toString()
                    .equals(pickUpPointAutoCompleteTextView.text.toString())
            ) {
                Toast.makeText(
                    this,
                    "Please add different location in pickup and drop point",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val rideList: ArrayList<Ride> = arrayListOf()
                val user = FirebaseAuth.getInstance().currentUser
                val database = FirebaseFirestore.getInstance()
                val docRef = database.collection("ride").whereEqualTo("source", pickUpLoc)
                docRef.get().addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val list: List<DocumentSnapshot> =
                            querySnapshot.documents
                        for (d in list) {
                            val r = Ride(
                                d.get("name").toString(),
                                d.get("source").toString(),
                                d.get("destination").toString(),
                                d.get("date").toString(),
                                d.get("time").toString(),
                            )
                            Log.d("ride", r.date + r.destination + r.source + r.time)
                            rideList.add(r)
                            Log.d("ride", rideList.toString())

                        }
                        searchResultsRecyclerView.adapter = SearchRideAdapter(rideList)
                    }
                }

            }
        }
    }
}