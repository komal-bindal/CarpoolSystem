package com.example.carpoolsystem

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat.is24HourFormat
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var source: EditText
    private lateinit var destination: EditText
    private lateinit var addRide: TextView
    private lateinit var addDetails: Button
    private lateinit var addDateAndTime: Button
    private lateinit var viewDateAndTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride_screen)

        source = findViewById(R.id.editTextSource)
        destination = findViewById(R.id.editTextDestination)
        addDetails = findViewById(R.id.buttonSubmit)
        viewDateAndTime = findViewById(R.id.textViewempty)
        addDateAndTime = findViewById(R.id.btnPick)

        addDateAndTime.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@AddRideScreen, this@AddRideScreen, year, month, day)
            datePickerDialog.show()
        }

        source.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int, count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int, before: Int, count: Int
            ) {
                s?.apply {
                    if (RideUtils.isValidSource(s.toString())) {
                        source.error = null
                    } else {
                        source.error = SOURCE_ERROR
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        destination.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int, p2: Int, p3: Int
            ) {
            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int, p2: Int, p3: Int
            ) {
                p0?.apply {
                    if (RideUtils.isValidDestination(p0.toString())) {
                        destination.error = null
                    } else {
                        destination.error = DESTINATION_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
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