package com.example.carpoolsystem.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.ApiInterface
import com.example.carpoolsystem.models.VehicleCarMake
import com.example.carpoolsystem.models.VehicleCarModel
import com.example.carpoolsystem.utility.VehicleUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://vpic.nhtsa.dot.gov/api/"

class ChangeCarDetails : AppCompatActivity() {
    private lateinit var carModelAutoCompleteTextView: AutoCompleteTextView
    private lateinit var carMakeAutoCompleteTextView: AutoCompleteTextView
    lateinit var letters: EditText
    lateinit var digits: EditText
    lateinit var districtCode: EditText
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_car_details)

        carMakeAutoCompleteTextView = findViewById(R.id.carMakeAutoComplete)
        carModelAutoCompleteTextView = findViewById(R.id.carModelAutoComplete)
        letters = findViewById(R.id.letters)
        digits = findViewById(R.id.digits)
        districtCode = findViewById(R.id.districtcode)
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

        var carMakeList = arrayListOf<String>()
        var carMakeAdapter = ArrayAdapter(this, R.layout.dropdown_item, carMakeList)
        carMakeAutoCompleteTextView.setAdapter(carMakeAdapter)

        var carModelList = arrayListOf<String>()
        var carModelAdapter = ArrayAdapter(this, R.layout.dropdown_item, carModelList)
        carModelAutoCompleteTextView.setAdapter(carModelAdapter)


        val retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
                .create(ApiInterface::class.java)

        val carMakeData = retrofit.getCarMake("json")
        carMakeData.enqueue(object : Callback<VehicleCarMake?> {
            override fun onFailure(call: Call<VehicleCarMake?>, t: Throwable) {
                Log.d("my", t.message.toString())
            }

            override fun onResponse(
                call: Call<VehicleCarMake?>,
                response: Response<VehicleCarMake?>
            ) {
                val resposeBody = response.body()!!
                for (x in resposeBody.Results) {
                    carMakeList.add(x.MakeName)
                }

            }
        })
        var carModelData: Call<VehicleCarModel>

        carMakeAutoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            Log.d("model", carMakeAutoCompleteTextView.text.toString())
            carModelData = retrofit.getCarModel(
                carMakeAutoCompleteTextView.text.toString().lowercase(),
                "json"
            )
            carModelList.clear()
            carModelData.enqueue(object : Callback<VehicleCarModel?> {
                override fun onFailure(call: Call<VehicleCarModel?>, t: Throwable) {
                    Log.d("my", t.message.toString())
                }

                override fun onResponse(
                    call: Call<VehicleCarModel?>,
                    response: Response<VehicleCarModel?>
                ) {
                    val resposeBody = response.body()!!
                    for (x in resposeBody.Results) {
                        carModelList.add(x.Model_Name)
                        Log.d("model", x.Model_Name)
                    }
                }
            })
        }
    }
}