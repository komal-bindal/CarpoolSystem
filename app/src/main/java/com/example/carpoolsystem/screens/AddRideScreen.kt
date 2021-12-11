package com.example.carpoolsystem.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class AddRideScreen : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private var firebaseAuth: FirebaseAuth? = null
    private val SOURCE_ERROR = "invalid source format"
    private val DESTINATION_ERROR = "invalid destination format"

    var day: Int = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay: Int = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    lateinit var Time: String

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")

    private lateinit var addDetails: Button
    private lateinit var addDateAndTime: Button
    private lateinit var viewDateAndTime: TextView
    private lateinit var addLocationButton: Button
    private lateinit var src: TextView
    private lateinit var dest: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride_screen)

        firebaseAuth = FirebaseAuth.getInstance()
        val intent = intent

        viewDateAndTime = findViewById(R.id.textViewempty)
        addDateAndTime = findViewById(R.id.btnPick)
        src = findViewById(R.id.textsource)
        dest = findViewById(R.id.textdestination)
        addDetails = findViewById(R.id.buttonSubmit)
        addLocationButton = findViewById(R.id.buttonAddLocation)

        var currentDate = sdf.format(Date())
        var dateTime = currentDate.split(" ")
        var date = dateTime.get(0)
        var time = dateTime.get(1)
        viewDateAndTime.text =
            "Date: $date\nTime: $time"

        val pickUpPoint = intent.getStringExtra("pickUpPoint").toString()
        val dropPoint = intent.getStringExtra("dropPoint").toString()

        if (pickUpPoint.isEmpty() || dropPoint.isEmpty() || pickUpPoint == null || dropPoint == null) {
            src.text = "Pickup: "
            dest.text = "Drop: "
        } else {
            src.text = "Pickup: $pickUpPoint"
            dest.text = "Drop: $dropPoint"
        }

        addDetails.setOnClickListener {
            val pick = src.text.toString()
            val drop = dest.text.toString()
            val dateTime = "$myDay/$myMonth/$myYear"
            val time = "$myHour : $myMinute"
            currentDate = sdf.format(Date())
//            val dateNow = currentDate.split(" ").get(0)
//            val timeNow = currentDate.split(" ").get(1)


            if (pick.isEmpty() || drop.isEmpty() || myDay == 0 || myMonth == 0 || myYear == 0 || myHour == 0 || myMinute == 0) {
                Toast.makeText(
                    this,
                    "please enter the locations, time and date",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("xxx", "$pick $drop $myDay $myMonth $myYear $myHour $myMinute")
            } else {
                val firebaseUser = firebaseAuth?.currentUser
                val name = firebaseUser?.displayName.toString()
                val uid = firebaseUser?.uid!!.toString()
                addDetails.isEnabled = false
                if (isEmailIdLinked(firebaseUser)) {
                    val db = FirebaseFirestore.getInstance()
                    val docReference = db.collection("car")
                        .whereEqualTo("owner", uid)
                    docReference.get().addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val list = querySnapshot.documents
                            for (i in list) {
                                Log.d("car", i.data?.get("carMake").toString())
                                if (i.data?.get("carMake").toString() != "") {
                                    saveFireStore(pick, drop, dateTime, Time, uid, name)
                                    startActivity(Intent(this, Dashboard::class.java))
                                }
                            }
                        } else {
                            Toast.makeText(
                                this@AddRideScreen,
                                "Add the Car Details",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this, ChangeCarDetails::class.java))
                        }
                    }.addOnFailureListener { e ->
                        Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this, Dashboard::class.java))
                    }
                } else {
                    Toast.makeText(
                        this@AddRideScreen,
                        "Please link your EmailId first",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, DriversProfile::class.java))
                }
            }
        }


        addLocationButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
            finish()
        }

        addDateAndTime.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
//            Toast.makeText(this, source, Toast.LENGTH_SHORT).show()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@AddRideScreen, this@AddRideScreen, year, month, day)
            datePickerDialog.datePicker.minDate = calendar.timeInMillis
            datePickerDialog.show()
        }

    }


    private fun isEmailIdLinked(currentUser: FirebaseUser): Boolean {
        val list = currentUser.providerData
        for (i in list) {
            if (i.providerId == "password") {
                return true
            }
        }
        return false
    }

    private fun saveFireStore(
        s1: String,
        d1: String,
        dateTime: String,
        time: String,
        uid: String,
        name: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["source"] = s1
        user["destination"] = d1
        user["time"] = time
        user["date"] = dateTime
        user["name"] = name
        user["uid"] = uid
        db.collection("ride")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this@AddRideScreen, "record added succesfully", Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener {
                Toast.makeText(this@AddRideScreen, "failed to add record", Toast.LENGTH_SHORT)
                    .show()
            }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month + 1
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
        val selectedTime = "$hourOfDay:$minute"
        val _24HrTimeFormat = SimpleDateFormat("HH:mm")
        val _12HrTimeFormat = SimpleDateFormat("hh:mm a")
        var _24HrTime: Date? = _24HrTimeFormat.parse(selectedTime)
        Time = _12HrTimeFormat.format(_24HrTime)
        viewDateAndTime.text = "Date = $myDay/$myMonth/$myYear\nTime=$Time"

    }
}