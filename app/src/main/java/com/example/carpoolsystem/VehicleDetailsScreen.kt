package com.example.carpoolsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class VehicleDetailsScreen : AppCompatActivity() {
    private val SOURCE_ERROR = "invalid source format"
    private val DESTINATION_ERROR = "invalid destination format"
    private val DATE_ERROR = "invalid date format"
    private val TIME_ERROR = "invalid time format"


    private lateinit var source: EditText
    private lateinit var destination: EditText
    private lateinit var time: EditText
    private lateinit var date: EditText
    private lateinit var addRide: TextView
    private lateinit var addDetails: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details_screen)
        source=findViewById(R.id.editTextSource)
        destination=findViewById(R.id.editTextDestination)
        time=findViewById(R.id.editTextTime)
        date=findViewById(R.id.editTextDate)
        addRide=findViewById(R.id.textView)
        addDetails=findViewById(R.id.buttonSubmit)

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
                    if (RegistrationUtils.isValidSource(s.toString())) {
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
                    if (RegistrationUtils.isValidDestination(p0.toString())) {
                        destination.error = null
                    } else {
                        destination.error = DESTINATION_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        date.addTextChangedListener(object : TextWatcher {
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
                    if (RegistrationUtils.isValidDate(p0.toString())) {
                        date.error = null
                    } else {
                        date.error = DATE_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        time.addTextChangedListener(object : TextWatcher {
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
                    if (RegistrationUtils.isValidTime(p0.toString())) {
                     time.error = null
                    } else {
                        time.error = TIME_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

}
}