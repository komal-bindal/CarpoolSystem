package com.example.carpoolsystem.screens

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.ApiInterface
import com.example.carpoolsystem.models.VehicleCarMake
import com.example.carpoolsystem.models.VehicleCarModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://vpic.nhtsa.dot.gov/api/"

class ChangeCarDetails : AppCompatActivity() {
    private lateinit var carModelAutoCompleteTextView: AutoCompleteTextView
    private lateinit var carMakeAutoCompleteTextView: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_car_details)

        carMakeAutoCompleteTextView = findViewById(R.id.carMakeAutoComplete)
        carModelAutoCompleteTextView = findViewById(R.id.carModelAutoComplete)

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