package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.models.ApiInterface
import com.example.carpoolsystem.models.VehicleCarMake
import com.example.carpoolsystem.models.VehicleCarModel
import com.example.carpoolsystem.utility.VehicleUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://vpic.nhtsa.dot.gov/api/"

class ChangeCarDetails : AppCompatActivity() {
    private lateinit var carModelAutoCompleteTextView: AutoCompleteTextView
    private lateinit var carMakeAutoCompleteTextView: AutoCompleteTextView
    private lateinit var state: AutoCompleteTextView

    private lateinit var letters: EditText
    private lateinit var digits: EditText
    private lateinit var districtCode: EditText
    private lateinit var saveCarChangeDetailsButton: Button
    private val CAR_COLLECTION = "car"
    private val CAR_NUMBER = "carNumber"
    private val CAR_MODEL = "carModel"
    private val CAR_MAKE = "carMake"
    private val OWNER = "owner"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_car_details)

        carMakeAutoCompleteTextView = findViewById(R.id.carMakeAutoComplete)
        carModelAutoCompleteTextView = findViewById(R.id.carModelAutoComplete)
        letters = findViewById(R.id.letters)
        digits = findViewById(R.id.digits)
        districtCode = findViewById(R.id.districtcode)
        saveCarChangeDetailsButton = findViewById(R.id.buttonSaveCarChangeDetails)


        saveCarChangeDetailsButton.setOnClickListener {

            val carMake = carMakeAutoCompleteTextView.text.toString()
            val carModel = carModelAutoCompleteTextView.text.toString()
            val carNumber = getCarNumber()

            val firebaseUser = FirebaseAuth.getInstance().currentUser
            val docReference = FirebaseFirestore.getInstance().collection("car")
                .whereEqualTo("owner", firebaseUser?.uid!!.toString())
            docReference.get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val list: List<DocumentSnapshot> =
                            querySnapshot.documents
                        for (d in list) {
                            Log.d("data", d.data?.get("uid").toString())
                            if (!carMake.isEmpty()) {
                                d.reference.update(CAR_MAKE, carMake)
                                makeToast("data updated successfully")
                            }
                            if (!carModel.isEmpty()) {
                                d.reference.update(CAR_MODEL, carModel)
                                makeToast("data updated successfully")

                            }
                            if (carNumber != null) {
                                d.reference.update(CAR_NUMBER, carNumber)
                                makeToast("data updated successfully")
                            }
                        }
                        startActivity(Intent(this, ViewCarDetails::class.java))
                    } else {
                        val db = Firebase.firestore
                        val docData = hashMapOf(
                            CAR_NUMBER to carNumber,
                            CAR_MODEL to carModel,
                            CAR_MAKE to carMake,
                            OWNER to firebaseUser.uid.toString()
                        )
                        db.collection("car")
                            .add(docData)
                            .addOnSuccessListener {
                                Log.d(
                                    "done",
                                    "DocumentSnapshot successfully written!"
                                )
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "error",
                                    "Error writing document",
                                    e
                                )
                            }
                        makeToast("data added successfully")
                        startActivity(Intent(this, ViewCarDetails::class.java))

                    }
                }.addOnFailureListener { e ->
                    makeToast(e.message.toString())
                }
        }

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
                        districtCode.error = "Enter two digit district Code"
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
                        letters.error = "Enter 2 Valid Capital Letters"
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
                        digits.error = "Enter four digit Valid Number"
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        state = findViewById(R.id.autoCompleteTextView4)
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
        state.setAdapter(arrayAdapter)

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

    private fun getCarNumber(): String? {
        if (!state.text.toString().isEmpty() || !districtCode.text.toString()
                .isEmpty() || !letters.text.toString().isEmpty() || !digits.text.toString()
                .isEmpty()
        ) {
            if (state.text.toString().isEmpty() || districtCode.text.toString()
                    .isEmpty() || letters.text.toString().isEmpty() || digits.text.toString()
                    .isEmpty()
            ) {
                makeToast("Please enter all fields of car number")
                return null
            } else {
                return state.text.toString() + districtCode.text.toString() + letters.text.toString() + digits.text.toString()
            }
        } else {
            return null
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}