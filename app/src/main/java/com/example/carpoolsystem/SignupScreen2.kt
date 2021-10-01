package com.example.carpoolsystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit


class SignupScreen2 : AppCompatActivity() {
    private lateinit var phoneNumberEditText: EditText
    private lateinit var otpEditText: EditText

    private lateinit var getOtpButton: Button
    private lateinit var verifyButton: Button
    private lateinit var verificationOTP: String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup_screen2)

        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        getOtpButton = findViewById(R.id.buttonGetOtp)
        otpEditText = findViewById(R.id.editTextOTP)
        verifyButton = findViewById(R.id.buttonVerify)

        phoneNumberEditText.addTextChangedListener(object : TextWatcher {
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
                    if (RegistrationUtils.isValidPhoneNumber(s.toString())) {
                        phoneNumberEditText.error = null
                    } else {
                        phoneNumberEditText.error = "error"
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


        getOtpButton.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (phoneNumberEditText.text.toString().trim().isEmpty()) {
                        Toast.makeText(applicationContext, "Enter mobile", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + phoneNumberEditText.text.toString(),
                        60,
                        TimeUnit.SECONDS,
                        this@SignupScreen2,
                        object : OnVerificationStateChangedCallbacks() {
                            override fun onCodeSent(
                                verificationId: String,
                                forceResendingToken: ForceResendingToken
                            ) {
                                super.onCodeSent(verificationId, forceResendingToken)
                                verificationOTP = verificationId
                            }

                            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                            }

                            override fun onVerificationFailed(e: FirebaseException) {
                                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                }
            }
        )
        verifyButton.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    if (otpEditText.text.toString().isEmpty()) {
                        Toast.makeText(applicationContext, "Please enter Otp", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    if (otpEditText.text.toString().length != 6) {
                        Toast.makeText(
                            applicationContext,
                            "Please enter valid Otp",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    var otp = otpEditText.text.toString()
                    if (verificationOTP != null) {
                        var phoneAuthCredential: PhoneAuthCredential =
                            PhoneAuthProvider.getCredential(
                                verificationOTP,
                                otp
                            )
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(
                                object : OnCompleteListener<AuthResult> {
                                    override fun onComplete(task: Task<AuthResult>) {
                                        if (task.isSuccessful) {
                                            startActivity(
                                                Intent(
                                                    applicationContext,
                                                    SignupScreen1::class.java
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
}
