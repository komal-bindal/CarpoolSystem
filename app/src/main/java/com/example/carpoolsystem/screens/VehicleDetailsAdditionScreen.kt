package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.VehicleUtils

class VehicleDetailsAdditionScreen : AppCompatActivity() {
    private val CAR_NUMBER_ERROR = "invalid number format"
    private val CAR_MODEL_ERROR = "invalid model format"
    private val CAR_MAKE_ERROR = "invalid cake make format"
    private val LICENCE_NUMBER_ERROR = "invalid number format"
    private lateinit var carNumber: Button
    private lateinit var carModel: EditText
    private lateinit var licenceNumber: EditText
    private lateinit var carMake: EditText
    private lateinit var saveDetails: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details_addition_screen)
        carNumber = findViewById(R.id.editTextCarNumber)
        carModel = findViewById(R.id.editTextCarModel)
        licenceNumber = findViewById(R.id.editTextLicenseNumber)
        carMake = findViewById(R.id.editTextCarMake)
        saveDetails = findViewById(R.id.buttonSaveDetails)

        carNumber.setOnClickListener {
            val intent = (Intent(this@VehicleDetailsAdditionScreen, CarNumber::class.java))
            intent.putExtra("User", "Driver")
            startActivity(intent)
            finish()
        }
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