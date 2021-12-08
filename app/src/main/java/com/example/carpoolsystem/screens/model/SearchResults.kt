package com.example.carpoolsystem.screens.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.Recycler
import com.example.carpoolsystem.models.User
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject

class SearchResults : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<com.example.carpoolsystem.screens.User>
    private lateinit var myAdapter:Recycler
    private lateinit var db: FirebaseFirestore

//    private var layoutManager:RecyclerView.LayoutManager?=null
//    private var adapter:RecyclerView.Adapter<Recycler.ViewHolder>?=null
//    private lateinit var recyclerview:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results2)
    recyclerView=findViewById(R.id.recycler)
    recyclerView.layoutManager=LinearLayoutManager(this)
    recyclerView.setHasFixedSize(true)
    userArrayList= arrayListOf()
    myAdapter= Recycler(userArrayList)
    EventChangeListener()
//        layoutManager=LinearLayoutManager(this)
//        recyclerview=findViewById(R.id.recycler)
//        recyclerview.layoutManager=layoutManager
//        adapter=Recycler()
//        recyclerview.adapter=adapter

    }
    private fun EventChangeListener(){
        db= FirebaseFirestore.getInstance()
        db.collection("ride").
                addSnapshotListener(object:EventListener<QuerySnapshot>{
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if(error!=null){
                            Log.e("Firebase Error",error.message.toString())
                            return
                        }
                        for(dc:DocumentChange in value?.documentChanges!!){
                            if(dc.type==DocumentChange.Type.ADDED){
                                userArrayList.add(dc.document.toObject(com.example.carpoolsystem.screens.User::class.java))
                            }
                        }
                        myAdapter.notifyDataSetChanged()
                    }

                })

    }
}