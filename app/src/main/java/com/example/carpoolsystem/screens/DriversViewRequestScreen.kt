package com.example.carpoolsystem.screens

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Request
import com.example.carpoolsystem.screens.adapter.ViewRequestAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class DriversViewRequestScreen : AppCompatActivity() {
    private lateinit var viewRequestRecyclerView: RecyclerView
    private lateinit var infoTextViewForRequest: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_view_request_screen)

        viewRequestRecyclerView = findViewById(R.id.viewRequestRecyclerView)
        infoTextViewForRequest = findViewById(R.id.infoTextViewForRequest)
        viewRequestRecyclerView.layoutManager = LinearLayoutManager(this)
        viewRequestRecyclerView.setHasFixedSize(true)


        val requestList: ArrayList<Request> = arrayListOf()

        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseFirestore.getInstance()
        val docRef = database.collection("request").whereEqualTo("driverId", user?.uid)
            .whereEqualTo("accepted", "false")
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val list: List<DocumentSnapshot> =
                    querySnapshot.documents
                for (d in list) {
                    val driverId = d.get("driverId").toString()
                    val driverName = user?.displayName.toString()
                    val passengerId = d.get("passengerId").toString()
                    val source = d.get("source").toString()
                    val destination = d.get("destination").toString()
                    val date = d.get("date").toString()
                    val time = d.get("time").toString()
                    var passengerName: String
                    val dr = database.collection("users").whereEqualTo("uid", passengerId)
                    dr.get().addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val list1: List<DocumentSnapshot> =
                                querySnapshot.documents
                            for (i in list1) {
                                passengerName = d.get("name").toString()
                                val r = Request(
                                    driverId,
                                    driverName,
                                    passengerId,
                                    passengerName,
                                    source,
                                    destination,
                                    date,
                                    time
                                )
                                requestList.add(r)
                            }
                        }
                    }
                }
                viewRequestRecyclerView.adapter = ViewRequestAdapter(requestList)
                infoTextViewForRequest.visibility = View.GONE
            } else {
                infoTextViewForRequest.visibility = View.VISIBLE
                infoTextViewForRequest.text = "No results found"
            }
        }

    }
}
