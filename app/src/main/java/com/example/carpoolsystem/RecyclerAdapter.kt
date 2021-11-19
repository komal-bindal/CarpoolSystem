package com.example.carpoolsystem
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var name= arrayOf("Deepanshi","Komal","kritika")
    private var number= arrayOf("UP80 12 AB 1234","UP80 12 AB 1234","UP80 12 AB 1234")
    private var source= arrayOf("agra->Mathura","mathura->GlA","agra->Aligarh")
    private var date1= arrayOf("20-Sept-2021","23-Sept-2021","19-Sept-2021")
    private var timings1= arrayOf("8:00 A.M","9:00 A.M","10:00 A.M")
    private var accept= arrayOf("Accept","Accept","Accept")
    private var cancel= arrayOf("Cancel","Cancel","Cancel")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.car_layout,parent,false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.driverName.text=name[position]
        holder.vehicleNumber.text=number[position]
        holder.sourceDestination.text=source[position]
        holder.date.text=date1[position]
        holder.timings.text=timings1[position]
        holder.acceptRequest.text=accept[position]
        holder.cancelRequest.text=cancel[position]

    }

    override fun getItemCount(): Int {
        return name.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var driverName:TextView
        var vehicleNumber:TextView
        var sourceDestination:TextView
        var date:TextView
        var timings:TextView
        var acceptRequest: TextView
        var cancelRequest:TextView
        init{
            driverName=itemView.findViewById((R.id.drivername))
            vehicleNumber=itemView.findViewById((R.id.vehiclenumber))
            sourceDestination=itemView.findViewById((R.id.sourcedestiation))
            date=itemView.findViewById((R.id.date))
            timings=itemView.findViewById((R.id.timings))
            acceptRequest=itemView.findViewById((R.id.buttonacceptrequest))
            cancelRequest=itemView.findViewById((R.id.buttoncancel))
        }
    }
}