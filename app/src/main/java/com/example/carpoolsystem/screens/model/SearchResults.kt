package com.example.carpoolsystem.screens.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.Recycler

class SearchResults : AppCompatActivity() {
    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter:RecyclerView.Adapter<Recycler.ViewHolder>?=null
    private lateinit var recyclerview:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results2)
        layoutManager=LinearLayoutManager(this)
        recyclerview=findViewById(R.id.recycler)
        recyclerview.layoutManager=layoutManager
        adapter=Recycler()
        recyclerview.adapter=adapter

    }
}