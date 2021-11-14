package com.example.carpoolsystem.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.VehicleUtils

class CarNumber : AppCompatActivity() {
    lateinit var letters: EditText
    lateinit var digits: EditText
    lateinit var districtCode: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_number)
        letters = findViewById(R.id.editTextletters)
        digits = findViewById(R.id.editTextdigits)
        districtCode = findViewById(R.id.editTextdigits1)
        districtCode.addTextChangedListener(object : TextWatcher {
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
                    if (VehicleUtils.isValidDistrictCode(p0.toString())) {
                        districtCode.error = null
                    } else {
                        districtCode.error ="Enter two digit district Code"
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        letters.addTextChangedListener(object : TextWatcher {
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
                    if (VehicleUtils.isValidLetters(p0.toString())) {
                        letters.error = null
                    } else {
                        letters.error ="Enter 2 Valid Capital Letters"
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        digits.addTextChangedListener(object : TextWatcher {
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
                    if (VehicleUtils.isValidDigits(p0.toString())) {
                        digits.error = null
                    } else {
                        digits.error ="Enter four digit Valid Number"
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        var textView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView3)
        val array = arrayOf<String>(
            "UP",
            "UK",
            "CG",
            "HR",
            "PB",
            "AN",
            "BR",
            "Others",

            )
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, array)
        textView.setAdapter(arrayAdapter)
    }
}




