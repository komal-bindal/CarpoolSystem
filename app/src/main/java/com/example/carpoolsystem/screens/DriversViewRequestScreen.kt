package com.example.carpoolsystem.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.RecyclerAdapter
import com.example.carpoolsystem.screens.User
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

public class DriversViewRequestScreen : AppCompatActivity() {
    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter:RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null
    private lateinit var recyclerview:RecyclerView
    private lateinit var userArrayList:ArrayList<User>
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_view_request_screen)
        recyclerview=findViewById(R.id.recyclerView)
        layoutManager=LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager=layoutManager
        adapter=RecyclerAdapter(userArrayList)
        recyclerview.adapter=adapter
        db= FirebaseFirestore.getInstance();
        EventChangeListener()
    }

    private fun EventChangeListener() {
        db.collection("DriversViewRequest")
            //.addSnapshotListener( new EventListener<QuerySnapshot>(){

           // })
    }


}
