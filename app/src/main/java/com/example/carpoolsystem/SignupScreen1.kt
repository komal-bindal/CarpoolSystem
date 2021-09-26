package com.example.carpoolsystem

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignupScreen1 : AppCompatActivity() {
    private val PASSWORD_ERROR = "invalid password"
    private val EMAIL_ID_ERROR = "invalid emailId"
    private val PASSWORD_INSTRUCTIONS =
        "Password should contain one special character(@#$%^&+=!), 1 uppercase letter, 1 lowercase letter and should have minimum 4 characters"


    private lateinit var password: EditText
    private lateinit var emailId: EditText
    private lateinit var passwordInstructions: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen1)

        passwordInstructions = findViewById(R.id.passwordInstructions)
        password = findViewById(R.id.passwordText)
        emailId = findViewById(R.id.emailIdText)
        passwordInstructions.text = PASSWORD_INSTRUCTIONS

        password.addTextChangedListener(object : TextWatcher {
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
                    if (RegistrationUtils.isValidPassword(s.toString())) {
                        password.error = null
                    } else {
                        password.error = PASSWORD_ERROR
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        emailId.addTextChangedListener(object : TextWatcher {
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
                    if (RegistrationUtils.isValidEmail(p0.toString())) {
                        emailId.error = null
                    } else {
                        emailId.error = EMAIL_ID_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

    }
}




