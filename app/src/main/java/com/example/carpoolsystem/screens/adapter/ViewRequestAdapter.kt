package com.example.carpoolsystem.screens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Request
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ViewRequestAdapter(var requests: List<Request>) :
    RecyclerView.Adapter<ViewRequestAdapter.ItemViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewRequestAdapter.ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.driver_view_request_item, parent, false)
        context = parent.context
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val request: Request = requests[position]
        holder.name.text = request.passengerName
        holder.pickup.text = request.source
        holder.drop.text = request.destination
        holder.date.text = request.date
        holder.time.text = request.time
        holder.rejectButton.setOnClickListener {
            rejectRequest(request)
            holder.rejectButton.isEnabled = false
            holder.acceptButton.isEnabled = false
        }
        holder.acceptButton.setOnClickListener {
            acceptRequest(
                request
            )
            holder.rejectButton.isEnabled = false
            holder.acceptButton.isEnabled = false
        }
    }

    private fun rejectRequest(
        request: Request
    ) {
        val db = FirebaseFirestore.getInstance()
        val docRef =
            db.collection("request").whereEqualTo("driverId", request.driverId)
                .whereEqualTo("passengerId", request.passengerId)
                .whereEqualTo("source", request.source)
                .whereEqualTo("destination", request.destination)
                .whereEqualTo("date", request.date).whereEqualTo("time", request.time)
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val list: List<DocumentSnapshot> =
                    querySnapshot.documents
                for (d in list) {
                    d.reference.update("accepted", "rejected")
                }
            } else {
                Toast.makeText(context, "No data found in Database", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Fail to get the data.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun acceptRequest(
        request: Request
    ) {
        val db = FirebaseFirestore.getInstance()
        val docRef =
            db.collection("request").whereEqualTo("driverId", request.driverId)
                .whereEqualTo("passengerId", request.passengerId)
                .whereEqualTo("source", request.source)
                .whereEqualTo("destination", request.destination)
                .whereEqualTo("date", request.date).whereEqualTo("time", request.time)
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val list: List<DocumentSnapshot> =
                    querySnapshot.documents
                for (d in list) {
                    d.reference.update("accepted", "true")
                }
            } else {
                Toast.makeText(context, "No data found in Database", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Fail to get the data.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameItemRequest)
        val pickup: TextView = view.findViewById(R.id.pickupItemRequest)
        val drop: TextView = view.findViewById(R.id.dropItemRequest)
        val date: TextView = view.findViewById(R.id.dateItemRequest)
        val time: TextView = view.findViewById(R.id.timeItemRequest)
        val rejectButton: Button = view.findViewById(R.id.rejectButton)
        val acceptButton: Button = view.findViewById(R.id.acceptButton)
    }
}
