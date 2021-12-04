package com.example.carpoolsystem.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Ride
import com.example.carpoolsystem.screens.adapter.ManageRideAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ManageRide : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_ride)

        recyclerView = findViewById(R.id.rideList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        val rideList: ArrayList<Ride> = arrayListOf()
        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseFirestore.getInstance()
        val docRef = database.collection("ride").whereEqualTo("uid", user?.uid.toString())
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val list: List<DocumentSnapshot> =
                    querySnapshot.documents
                for (d in list) {
                    val r = Ride(
                        d.get("source").toString(),
                        d.get("destination").toString(),
                        d.get("date").toString(),
                        d.get("time").toString(),
                    )
                    Log.d("ride", r.date + r.destination + r.source + r.time)

                    rideList.add(r)
                    Log.d("ride", rideList.toString())

                }
                recyclerView.adapter = ManageRideAdapter(rideList)
            }
        }


    }
}