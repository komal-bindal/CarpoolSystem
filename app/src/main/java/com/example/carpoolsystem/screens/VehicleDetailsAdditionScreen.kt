package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.VehicleUtils

class VehicleDetailsAdditionScreen : AppCompatActivity() {
    private val CAR_MODEL_ERROR = "invalid model format"
    private val CAR_MAKE_ERROR = "invalid cake make format"
    private val LICENCE_NUMBER_ERROR = "invalid number format"
    lateinit var letters: EditText
    lateinit var digits: EditText
    lateinit var districtCode: EditText
    private lateinit var carModel: EditText
    private lateinit var licenceNumber: EditText
    private lateinit var carMake: EditText
    private lateinit var saveDetails: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details_addition_screen)
        letters = findViewById(R.id.letters)
        digits = findViewById(R.id.digits)
        districtCode = findViewById(R.id.districtcode)
        carModel = findViewById(R.id.editTextnewCarModel)
        licenceNumber = findViewById(R.id.editTextLicenseNumber)
        carMake = findViewById(R.id.editTextnewCarMake)
        saveDetails = findViewById(R.id.buttonsavenewdetails)
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

        var textView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView4)
        val array = arrayOf<String>(
            "UP",
            "UK",
            "CG",
            "HR",
            "PB",
            "AN",
            "BR",
            "AP",
            "AR",
            "AS",
            "DL",
            "GA",
            "GJ",
            "JH",
            "KA",
            "KL",
            "LA",
            "LD",
            "MP",
            "MH",
            "ML",
            "MZ",
            "NL",
            "OD",
            "PY",
            "RG",
            "WB",
            "TN",
            "Others",

            )
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, array)
        textView.setAdapter(arrayAdapter)

        carModel.addTextChangedListener(object : TextWatcher {
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
                    if (VehicleUtils.isValidCarModel(p0.toString())) {
                        carModel.error = null
                    } else {
                        carModel.error = CAR_MODEL_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        licenceNumber.addTextChangedListener(object : TextWatcher {
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
                    if (VehicleUtils.isValidLicenseNumber(p0.toString())) {
                        licenceNumber.error = null
                    } else {
                        licenceNumber.error = LICENCE_NUMBER_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        carMake.addTextChangedListener(object : TextWatcher {
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
                    if (VehicleUtils.isValidCarMake(p0.toString())) {
                        carMake.error = null
                    } else {
                        carMake.error = CAR_MAKE_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })


    }
}