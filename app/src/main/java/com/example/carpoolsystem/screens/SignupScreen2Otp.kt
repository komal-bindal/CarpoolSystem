package com.example.carpoolsystem.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupScreen2Otp : AppCompatActivity() {

    private lateinit var otpCode1: EditText
    private lateinit var otpCode2: EditText
    private lateinit var otpCode3: EditText
    private lateinit var otpCode4: EditText
    private lateinit var otpCode5: EditText
    private lateinit var otpCode6: EditText
    private lateinit var phoneNumber: TextView
    private lateinit var verifyButton: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private val USERS_COLLECTION = "users"
    private val UID = "uid"
    private val USER = "user"
    private val NAME = "name"
    private val EMAIL_ID = "emailId"
    private val PHONE_NUMBER = "phoneNumber"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen2_otp)

        otpCode1 = findViewById(R.id.otpCode1)
        otpCode2 = findViewById(R.id.otpCode2)
        otpCode3 = findViewById(R.id.otpCode3)
        otpCode4 = findViewById(R.id.otpCode4)
        otpCode5 = findViewById(R.id.otpCode5)
        otpCode6 = findViewById(R.id.otpCode6)

        phoneNumber = findViewById(R.id.mobile)
        firebaseAuth = FirebaseAuth.getInstance()

        verifyButton = findViewById(R.id.buttonVerify)

        val intent = intent
        val phone = intent.getStringExtra("PhoneNumber")
        val selectedUser = intent.getStringExtra("User")
        phoneNumber.text = phone.toString()
        val verificationOtp = intent.getStringExtra("VerificationOTP")

        otpCode1.requestFocus()

        otpEditTextSetUp()

        verifyButton.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    val otpEntered = otpCode1.text.toString() +
                            otpCode2.text.toString() +
                            otpCode3.text.toString() +
                            otpCode4.text.toString() +
                            otpCode5.text.toString() +
                            otpCode6.text.toString()
                    if (otpEntered.isEmpty()) {
                        makeToast("Please enter Otp")
                        return
                    }
                    if (otpEntered.length != 6) {

                        makeToast("Please enter valid Otp")
                        return
                    }

                    if (verificationOtp != null) {
                        var phoneAuthCredential: PhoneAuthCredential =
                            PhoneAuthProvider.getCredential(
                                verificationOtp,
                                otpEntered
                            )
                        firebaseAuth.signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(
                                object : OnCompleteListener<AuthResult> {
                                    @SuppressLint("LogNotTimber")
                                    override fun onComplete(task: Task<AuthResult>) {
                                        if (task.isSuccessful) {
                                            val fireBaseUser = firebaseAuth.currentUser
                                            Log.d("auth", fireBaseUser?.uid!!)
                                            val docReference =
                                                getReferenceOfCurrentUserFromDatabase(fireBaseUser)
                                            docReference.get()
                                                .addOnSuccessListener { querySnapshot ->
                                                    if (!querySnapshot.isEmpty) {
                                                        Log.d("done", "tada")
                                                        val list: List<DocumentSnapshot> =
                                                            querySnapshot.documents
                                                        for (d in list) {
                                                            Log.d("data", "${d.data?.get(USER)}")
                                                            if (d.data?.get(USER) == selectedUser) {
                                                                if (selectedUser == "Passenger") {
                                                                    startActivity(
                                                                        Intent(
                                                                            applicationContext,
                                                                            DashboardPassenger::class.java
                                                                        )
                                                                    )
                                                                } else {
                                                                    startActivity(
                                                                        Intent(
                                                                            applicationContext,
                                                                            Dashboard::class.java
                                                                        )
                                                                    )
                                                                }
                                                                finish()
                                                            } else {
                                                                makeToast("You have not registered as $selectedUser")
                                                            }
                                                        }

                                                    } else {
                                                        Log.d("it is", "empty")
                                                        val user = createUserHasMap(
                                                            fireBaseUser.uid.toString(),
                                                            "",
                                                            "",
                                                            selectedUser!!,
                                                            phone!!
                                                        )
                                                        addUserInDatabase(fireBaseUser, user)
                                                    }
                                                }.addOnFailureListener { e ->
                                                    Log.d(
                                                        "error",
                                                        "error in accessing database"
                                                    )
                                                }

                                        } else {
                                            makeToast("OTP is invalid")
                                        }
                                    }
                                }
                            )
                    }
                }
            }
        )

    }

    private fun getReferenceOfCurrentUserFromDatabase(currentUser: FirebaseUser): Query {
        return FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
            .whereEqualTo(
                UID,
                currentUser.uid.toString()
            )
    }

    private fun createUserHasMap(
        uid: String,
        name: String,
        emailId: String,
        selectedUser: String,
        phoneNumber: String
    ): HashMap<String, String> {
        return hashMapOf(
            UID to uid,
            NAME to name,
            EMAIL_ID to emailId,
            USER to selectedUser,
            PHONE_NUMBER to phoneNumber
        )
    }

    @SuppressLint("LogNotTimber")
    private fun addUserInDatabase(firebaseUser: FirebaseUser, user: HashMap<String, String>) {
        val db = Firebase.firestore
        db.collection(USERS_COLLECTION).document(firebaseUser.uid)
            .set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "database",
                    "DocumentSnapshot "
                )
                startActivity(
                    Intent(
                        applicationContext,
                        DashboardPassenger::class.java
                    )
                )
            }.addOnFailureListener { e ->
                Log.w("error", "Error adding documnent", e)
            }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun otpEditTextSetUp() {
        otpCode1.addTextChangedListener(object : TextWatcher {
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
                    if (!s.toString().trim().isEmpty()) {
                        otpCode2.requestFocus()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        otpCode2.addTextChangedListener(object : TextWatcher {
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
                    if (!s.toString().trim().isEmpty()) {
                        otpCode3.requestFocus()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        otpCode3.addTextChangedListener(object : TextWatcher {
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
                    if (!s.toString().trim().isEmpty()) {
                        otpCode4.requestFocus()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        otpCode4.addTextChangedListener(object : TextWatcher {
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
                    if (!s.toString().trim().isEmpty()) {
                        otpCode5.requestFocus()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        otpCode5.addTextChangedListener(object : TextWatcher {
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
                    if (!s.toString().trim().isEmpty()) {
                        otpCode6.requestFocus()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
}

