package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class SignupScreen2Otp : AppCompatActivity() {

    private lateinit var otpCode1: EditText
    private lateinit var otpCode2: EditText
    private lateinit var otpCode3: EditText
    private lateinit var otpCode4: EditText
    private lateinit var otpCode5: EditText
    private lateinit var otpCode6: EditText
    private lateinit var phoneNumber: TextView
    private lateinit var verifyButton: Button

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

        verifyButton = findViewById(R.id.buttonVerify)

        val intent = intent
        val phone = intent.getStringExtra("PhoneNumber")
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
                        Toast.makeText(applicationContext, "Please enter Otp", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    if (otpEntered.length != 6) {
                        Toast.makeText(
                            applicationContext,
                            "Please enter valid Otp",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    if (verificationOtp != null) {
                        var phoneAuthCredential: PhoneAuthCredential =
                            PhoneAuthProvider.getCredential(
                                verificationOtp,
                                otpEntered
                            )
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(
                                object : OnCompleteListener<AuthResult> {
                                    override fun onComplete(task: Task<AuthResult>) {
                                        if (task.isSuccessful) {
                                            startActivity(
                                                Intent(
                                                    applicationContext,
                                                    Dashboard::class.java
                                                )
                                            )
                                        } else {
                                            Toast.makeText(
                                                applicationContext,
                                                "OTP is invalid",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            )
                    }
                }
            }
        )

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

