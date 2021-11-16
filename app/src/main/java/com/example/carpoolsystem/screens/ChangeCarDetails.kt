package com.example.carpoolsystem.screens

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.ApiInterface
import com.example.carpoolsystem.models.Vehicle
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

        var adapter = ArrayAdapter(this, R.layout.dropdown_item, carMakeList)

        carMakeAutoCompleteTextView.setAdapter(adapter)

        val retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
                .create(ApiInterface::class.java)

        val retrofitData = retrofit.getCarMake("json")

        retrofitData.enqueue(object : Callback<Vehicle?> {
            override fun onFailure(call: Call<Vehicle?>, t: Throwable) {
                Log.d("my", t.message.toString())
            }

            override fun onResponse(
                call: Call<Vehicle?>,
                response: Response<Vehicle?>
            ) {
                val resposeBody = response.body()!!
                for (x in resposeBody.Results) {
                    carMakeList.add(x.MakeName)
                    Log.d("bye", x.MakeName)
                }

            }
        })
    }
}