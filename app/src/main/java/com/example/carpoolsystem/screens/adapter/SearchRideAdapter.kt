package com.example.carpoolsystem.screens.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Ride
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class SearchRideAdapter(var rides: List<Ride>) :
    RecyclerView.Adapter<SearchRideAdapter.ItemViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRideAdapter.ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_ride_list_item, parent, false)
        context = parent.context
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val ride: Ride = rides[position]
        holder.name.text = ride.name
        holder.pickup.text = ride.source
        holder.drop.text = ride.destination
        holder.date.text = ride.date
        holder.time.text = ride.time
        holder.requestButton.setOnClickListener {
            sendRequest(ride.driverId, ride.source, ride.destination, ride.date, ride.time)
            holder.requestButton.isEnabled = false
        }
    }

    private fun sendRequest(
        driverId: String,
        source: String,
        destination: String,
        date: String,
        time: String
    ) {

        val db = FirebaseFirestore.getInstance()
        val request: MutableMap<String, Any> = HashMap()
        request["source"] = source
        request["destination"] = destination
        request["time"] = time
        request["date"] = date
        request["driverId"] = driverId
        request["accepted"] = false
        request["passengerId"] = FirebaseAuth.getInstance().currentUser?.uid.toString()
        db.collection("request")
            .add(request)
            .addOnSuccessListener {
                Log.d("success", "success")
                Toast.makeText(context, "request sent", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d("error", "error in sending request")
            }


    }

    override fun getItemCount(): Int {
        return rides.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameItem)
        val pickup: TextView = view.findViewById(R.id.pickupItem)
        val drop: TextView = view.findViewById(R.id.dropItem)
        val date: TextView = view.findViewById(R.id.dateItem)
        val time: TextView = view.findViewById(R.id.timeItem)
        val requestButton: Button = view.findViewById(R.id.sendRequestButton)
    }
}
