package com.example.carpoolsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolsystem.screens.User

class Recycler(private val userList: ArrayList<com.example.carpoolsystem.screens.User>):RecyclerView.Adapter<Recycler.ViewHolder>() {

//       private var name= arrayOf("Deepanshi","Komal","kritika")
//       private var number= arrayOf("UP80 12 AB 1234","UP80 12 AB 1234","UP80 12 AB 1234")
//      private var source= arrayOf("agra->Mathura","mathura->GlA","agra->Aligarh")
//      private var date1= arrayOf("20-Sept-2021","23-Sept-2021","19-Sept-2021")
//      private var timings1= arrayOf("8:00 A.M","9:00 A.M","10:00 A.M")
//      private var send= arrayOf("Send","Send","Send")
//     private var cancel= arrayOf("Cancel","Cancel","Cancel")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler.ViewHolder {


        val v=LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: Recycler.ViewHolder, position: Int) {
//
        val user:User=userList[position]
        //holder.name.text=user.Drivername
        //holder.vehicleno.text=user.VehicleNo
        holder.sd.text=user.sourceDestination
        holder.dt.text=user.Date
        holder.timing.text=user.Time


    }

    override fun getItemCount(): Int {
        return userList.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var name: TextView
        var vehicleno: TextView
        var sd: TextView
        var dt: TextView
        var timing: TextView
        var sendRequest: TextView
        var cancel: TextView
        init{
            name=itemView.findViewById(R.id.driver);
            vehicleno=itemView.findViewById(R.id.vehicleno);
            sd=itemView.findViewById(R.id.sd);
            dt=itemView.findViewById(R.id.dt);
            timing=itemView.findViewById(R.id.timing);
            sendRequest=itemView.findViewById(R.id.buttonsendrequest);
            cancel=itemView.findViewById(R.id.cancel);
        }

    }

}