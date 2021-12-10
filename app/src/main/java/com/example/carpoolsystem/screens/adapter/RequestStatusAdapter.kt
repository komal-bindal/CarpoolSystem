package com.example.carpoolsystem.screens.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Request

class RequestStatusAdapter(var requests: MutableList<Request>) :
    RecyclerView.Adapter<RequestStatusAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.request_status_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val request: Request = requests[position]
        holder.pickup.text = request.source
        holder.drop.text = request.destination
        holder.date.text = request.date
        holder.time.text = request.time
        holder.requestStatus.text = request.driverName
        holder.requestStatus.text = request.status

    }

    override fun getItemCount(): Int {
        return requests.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pickup: TextView = view.findViewById(R.id.pickupItemStatus)
        val drop: TextView = view.findViewById(R.id.dropItemStatus)
        val date: TextView = view.findViewById(R.id.dateItemStatus)
        val time: TextView = view.findViewById(R.id.timeItemStatus)
        val name: TextView = view.findViewById(R.id.nameItemStatus)
        val requestStatus: TextView = view.findViewById(R.id.statusItem)
    }

}