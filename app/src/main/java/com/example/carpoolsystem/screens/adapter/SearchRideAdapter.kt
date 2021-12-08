package com.example.carpoolsystem.screens.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Driver2

class SearchRideAdapter(var drivers: List<Driver2>) :
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
        val driver: Driver2 = drivers[position]
        holder.name.text = driver.name
        holder.pickup.text = driver.source
        holder.drop.text = driver.destination
        holder.date.text = driver.date
        holder.time.text = driver.time
    }

    override fun getItemCount(): Int {
        return drivers.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameItem)
        val pickup: TextView = view.findViewById(R.id.pickupItem)
        val drop: TextView = view.findViewById(R.id.dropItem)
        val date: TextView = view.findViewById(R.id.dateItem)
        val time: TextView = view.findViewById(R.id.timeItem)
    }
}
