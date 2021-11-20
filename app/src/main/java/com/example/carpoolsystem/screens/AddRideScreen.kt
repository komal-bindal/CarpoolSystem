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
import java.util.*

class AddRideScreen : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

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
    private lateinit var src: TextView
    private lateinit var dest: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride_screen)
        val intent = intent
//        val source = intent.getStringExtra("source")


        addDetails = findViewById(R.id.buttonSubmit)
        viewDateAndTime = findViewById(R.id.textViewempty)
        addDateAndTime = findViewById(R.id.btnPick)
        src = findViewById(R.id.textsource)
        dest = findViewById(R.id.textdestination)
        addLocationButton = findViewById(R.id.buttonAddLocation)
        val sr = getIntent().getStringExtra("source")
        val des = getIntent().getStringExtra("destination")
        src.text = sr.toString()
        dest.text = des.toString()

        addLocationButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        addDateAndTime.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
//            Toast.makeText(this, source, Toast.LENGTH_SHORT).show()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@AddRideScreen, this@AddRideScreen, year, month, day)
            datePickerDialog.show()
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