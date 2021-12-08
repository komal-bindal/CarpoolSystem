package com.example.carpoolsystem.screens.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Ride
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ManageRideAdapter(var rides: MutableList<Ride>) :
    RecyclerView.Adapter<ManageRideAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.ride_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val ride: Ride = rides[position]
        holder.pickup.text = ride.source
        holder.drop.text = ride.destination
        holder.date.text = ride.date
        holder.time.text = ride.time
        holder.deleteButton.setOnClickListener {
            deleteFromDatabase(rides.get(position))
            rides.removeAt(position)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return rides.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pickup: TextView = view.findViewById(R.id.pickupItem)
        val drop: TextView = view.findViewById(R.id.dropItem)
        val date: TextView = view.findViewById(R.id.dateItem)
        val time: TextView = view.findViewById(R.id.timeItem)
        val deleteButton: Button = view.findViewById(R.id.deleteRide)
    }

    fun deleteFromDatabase(ride: Ride) {
        val docRef =
            FirebaseFirestore.getInstance().collection("ride").whereEqualTo("uid", ride.driverId)
                .whereEqualTo("source", ride.source).whereEqualTo("destination", ride.destination)
                .whereEqualTo("date", ride.date).whereEqualTo("time", ride.time)
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val list: List<DocumentSnapshot> =
                    querySnapshot.documents
                for (d in list) {
                    d.reference.delete()
                }
            }
        }
    }

}