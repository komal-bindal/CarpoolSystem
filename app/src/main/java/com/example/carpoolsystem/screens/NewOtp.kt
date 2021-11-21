package com.example.carpoolsystem.screens

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewOtp : AppCompatActivity() {

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
        setContentView(R.layout.activity_new_otp)

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
        phoneNumber.text = phone.toString()
        val verificationOtp = intent.getStringExtra("VerificationOTP")
        val phoneAddOrChange = intent.getStringExtra("phone")

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
                        val auth = FirebaseAuth.getInstance()
                        val currentUser = auth.currentUser
                        if (phoneAddOrChange!! == "change") {
                            Log.d("otp", phoneAddOrChange)
                            updatePhoneNumber(currentUser, phoneAuthCredential, phone!!)
                        } else {
                            Log.d("otp", phoneAddOrChange)
                            addPhoneNumber(currentUser, phoneAuthCredential, phone!!)
                        }
                    }
                }
            }
        )
    }


    private fun updatePhoneNumberinDatabase(currentUser: FirebaseUser?, phone: String) {
        val docReference = FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
            .whereEqualTo(UID, currentUser?.uid!!.toString())

        docReference.get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val list: List<DocumentSnapshot> =
                        querySnapshot.documents
                    for (d in list) {
                        d.reference.update(PHONE_NUMBER, phone)
                    }
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    private fun updatePhoneNumber(
        currentUser: FirebaseUser?,
        phoneAuthCredential: PhoneAuthCredential, phone: String
    ) {
        val list = currentUser?.providerData
        for (i in list!!) {
            if (i.providerId == "phone") {
                currentUser.unlink(i.providerId)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            makeToast("unlinked")
                        }
                    }
            }
            Log.d("provider", i.providerId.toString())
        }
        GlobalScope.launch {
            delay(1000L)
            currentUser.linkWithCredential(phoneAuthCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        makeToast("phoneNumber updated successfully")
                        updatePhoneNumberinDatabase(currentUser, phone)
                    }
                }.addOnFailureListener { e ->
                    makeToast(e.message.toString())
                }
        }

    }

    private fun addPhoneNumber(
        currentUser: FirebaseUser?,
        phoneAuthCredential: PhoneAuthCredential, phone: String
    ) {
        currentUser!!.linkWithCredential(phoneAuthCredential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    makeToast("phoneNumber added successfully")
                    updatePhoneNumberinDatabase(currentUser, phone)
                }
            }.addOnFailureListener { e ->
                makeToast(e.message.toString())
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

