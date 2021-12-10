package com.example.carpoolsystem.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Request
import com.example.carpoolsystem.screens.adapter.RequestStatusAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class RequestStatus : AppCompatActivity() {
    private lateinit var requestStatusRecyclerView: RecyclerView
    private lateinit var infoTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_status)
        requestStatusRecyclerView = findViewById(R.id.requestStatusRecyclerView)
        infoTextView = findViewById(R.id.infoTextViewStatus)
        requestStatusRecyclerView.layoutManager = LinearLayoutManager(this)
        requestStatusRecyclerView.setHasFixedSize(true)
        val requestList: ArrayList<Request> = arrayListOf()

        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseFirestore.getInstance()
        val docRef =
            database.collection("request").whereEqualTo("passengerId", user?.uid.toString())
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val list: List<DocumentSnapshot> =
                    querySnapshot.documents
                for (d in list) {
                    val driverId = d.get("driverId").toString()
                    val passengerName = user?.displayName.toString()
                    val passengerId = d.get("passengerId").toString()
                    val source = d.get("source").toString()
                    val destination = d.get("destination").toString()
                    val date = d.get("date").toString()
                    val time = d.get("time").toString()
                    val status = d.get("accepted").toString()
                    var driverName: String
                    val dr = database.collection("users").whereEqualTo("uid", driverId)
                    dr.get().addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val list1: List<DocumentSnapshot> =
                                querySnapshot.documents
                            for (i in list1) {
                                driverName = d.get("name").toString()
                                val r = Request(
                                    driverId,
                                    driverName,
                                    passengerId,
                                    passengerName,
                                    source,
                                    destination,
                                    date,
                                    time, status
                                )
                                requestList.add(r)
                                Log.d("abc", user?.uid.toString())
                            }
                            requestStatusRecyclerView.adapter = RequestStatusAdapter(requestList)
                        }
                    }
                }
                infoTextView.visibility = View.GONE
            } else {
                infoTextView.visibility = View.VISIBLE
                infoTextView.text = "No results found"
            }
        }
    }
}