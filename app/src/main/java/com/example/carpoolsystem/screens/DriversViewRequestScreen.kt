package com.example.carpoolsystem.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.RecyclerAdapter

class DriversViewRequestScreen : AppCompatActivity() {
    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter:RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null
    private lateinit var recyclerview:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_view_request_screen)
        recyclerview=findViewById(R.id.recyclerView)
        layoutManager=LinearLayoutManager(this)
        recyclerview.layoutManager=layoutManager
        adapter=RecyclerAdapter()
        recyclerview.adapter=adapter
    }
}