package com.example.carpoolsystem.screens

import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.Abc
import com.example.carpoolsystem.models.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL: String = "https://vpic.nhtsa.dot.gov/api/"

class CarModel : AppCompatActivity() {
    private lateinit var textView: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_model)
        textView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView3)

//        val retrofit =
//            Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
//                .create(ApiInterface::class.java)

//        GlobalScope.launch(Dispatchers.IO) {
//            val a = retrofit.getCarMake("json").await()
//            Log.d("res", a.Count.toString())
//           var abc: Array<String> = Array(a.Results.size) { "" }
//            var j = 0
//            for (i in a.Results) {
//                abc[j] = i.Make_Name
//                Log.d("p", abc[j].toString())
//            }
//            val adapt = ArrayAdapter(this@CarModel, R.layout.dropdown_item, abc)
//            textView.setAdapter(adapt)
//
//        }
//        val array = arrayOf<String>(
//            "Hindi"
//        )
//        val arrayAdapter = ArrayAdapter(this@CarModel, R.layout.dropdown_item, array)
//        textView.setAdapter(arrayAdapter)
        getMyData()
    }
}

//            var b = getMyData()
//            Log.d("hi", b.size.toString())
//for (i in b) {
//            Log.d("1", i.Make_ID.toString())
//        }

fun getMyData() {
    val retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
            .create(ApiInterface::class.java)

    val retrofitData = retrofit.getCarMake("json")

    retrofitData.enqueue(object : Callback<Abc?> {

        override fun onFailure(call: Call<Abc?>, t: Throwable) {
            Log.d("my", t.message.toString())
        }

        override fun onResponse(
            call: Call<Abc?>,
            response: Response<Abc?>

        ) {
            val resposeBody = response.body()!!
            var array = Array(resposeBody.Results.size) { "" }
            var i = 0
            for (x in resposeBody.Results) {
                array[i] = x.Make_Name
                Log.d("bye", x.Make_Name)

            }
            //         var adapter = ArrayAdapter(this, R.layout.dropdown_item, array)

        }
    })
}