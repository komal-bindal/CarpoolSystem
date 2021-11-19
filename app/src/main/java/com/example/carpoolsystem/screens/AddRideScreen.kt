package com.example.carpoolsystem.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.screens.map.MapActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mapmyindia.sdk.plugin.directions.view.s
import java.util.*

class AddRideScreen : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    //private var firebaseAuth: FirebaseAuth? = null
    private val SOURCE_ERROR = "invalid source format"
    private val DESTINATION_ERROR = "invalid destination format"

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0


    private lateinit var addDetails: Button
    private lateinit var addDateAndTime: Button
    private lateinit var viewDateAndTime: TextView
    private lateinit var addLocationButton: Button
    private lateinit var src:TextView
    private lateinit var dest: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride_screen)
        //firebaseAuth = FirebaseAuth.getInstance()
        val intent=intent
        val source=intent.getStringExtra("source")
        addDetails = findViewById(R.id.buttonSubmit)
        viewDateAndTime = findViewById(R.id.textViewempty)
        addDateAndTime = findViewById(R.id.btnPick)
        src = findViewById(R.id.textsource)
        dest = findViewById(R.id.textdestination)
        addDetails=findViewById(R.id.buttonSubmit)
        addLocationButton = findViewById(R.id.buttonAddLocation)
        val sr=getIntent().getStringExtra("source")
        val des=getIntent().getStringExtra("destination")
        src.setText(sr.toString())
        dest.setText(des.toString())

        addDetails.setOnClickListener {
            val s1 = src.text.toString()
            val d1 = dest.text.toString()
            val dateTime = "Date=$day/$myMonth/$myYear"
            val time = "Time=$myHour : $myMinute"
            //val firebaseUser = firebaseAuth?.currentUser
            //val uid=firebaseUser?.uid!!.toString()

            if (s1.isEmpty() || d1.isEmpty() || dateTime.isEmpty() || time.isEmpty()) {
                Toast.makeText(
                    this@AddRideScreen,
                    "Please fill all the details",
                    Toast.LENGTH_SHORT
                ).show()
            }

        else {

                saveFireStore(s1, d1, dateTime, time)
            }
        }


        addLocationButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        addDateAndTime.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            Toast.makeText(this,source,Toast.LENGTH_SHORT).show()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@AddRideScreen, this@AddRideScreen, year, month, day)
            datePickerDialog.show()
        }

    }

    private fun saveFireStore(s1: String, d1: String, dateTime: String,time:String) {
        val db=FirebaseFirestore.getInstance()
        val user:MutableMap<String,Any> = HashMap()
        user["source"]=s1
        user["destination"]=d1
        user["time"]=time
        user["date"]=dateTime
       // user["uid"]=uid
        db.collection("ride")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this@AddRideScreen,"recordAddedSuccesfully",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@AddRideScreen,"recordAddedFailed",Toast.LENGTH_SHORT).show()
            }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            this@AddRideScreen, this@AddRideScreen, hour, minute,
            is24HourFormat(this)
        )
        timePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        viewDateAndTime.text =
            "Date = $day/$myMonth/$myYear\nTime = $myHour : $myMinute"
    }
}