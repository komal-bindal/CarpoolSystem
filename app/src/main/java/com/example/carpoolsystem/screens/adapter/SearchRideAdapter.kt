package com.example.carpoolsystem.screens.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Ride

class SearchRideAdapter(var rides: List<Ride>) :
    RecyclerView.Adapter<SearchRideAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRideAdapter.ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_ride_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val ride: Ride = rides[position]
        holder.name.text = ride.name
        holder.pickup.text = ride.source
        holder.drop.text = ride.destination
        holder.date.text = ride.date
        holder.time.text = ride.time
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
    }
}
