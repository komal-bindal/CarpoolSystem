package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.RegistrationUtils
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit


class SignupScreen2PhoneNumber : AppCompatActivity() {
    private lateinit var phoneNumberEditText: EditText
    private lateinit var getOtpButton: Button
    private lateinit var verificationOTP: String
    private val PHONE_NUMBER_ERROR = "phone number should have length 10"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup_screen2_phone_number)

        phoneNumberEditText = findViewById(R.id.editTextNewPhoneNumberAddNew)
        getOtpButton = findViewById(R.id.buttonGetOtpNew)

        phoneNumberEditText.requestFocus()

        val intent = intent
        val user = intent.getStringExtra("User").toString()

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
                        phoneNumberEditText.error = PHONE_NUMBER_ERROR
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
                        Toast.makeText(
                            applicationContext,
                            "Please enter phone number",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return
                    }
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + phoneNumberEditText.text.toString(),
                        60,
                        TimeUnit.SECONDS,
                        this@SignupScreen2PhoneNumber,
                        object : OnVerificationStateChangedCallbacks() {
                            override fun onCodeSent(
                                verificationId: String,
                                forceResendingToken: ForceResendingToken
                            ) {
                                super.onCodeSent(verificationId, forceResendingToken)
                                verificationOTP = verificationId
                                val phoneNumber = "+91 " + phoneNumberEditText.text.toString()
                                val intent =
                                    Intent(applicationContext, SignupScreen2Otp::class.java)
                                intent.putExtra("PhoneNumber", phoneNumber.toString())
                                intent.putExtra("VerificationOTP", verificationOTP.toString())
                                intent.putExtra("User", user)
                                startActivity(intent)
                                finish()
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


    }
}
