package com.example.carpoolsystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignupScreen2 : AppCompatActivity() {
    private lateinit var phoneNumber: EditText
    private lateinit var OTP: EditText
    private lateinit var verifyButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent: Intent
        intent = Intent(this, SignupScreen1::class.java)


        setContentView(R.layout.activity_signup_screen2)
        phoneNumber = findViewById(R.id.editTextPhoneNumber)
        OTP = findViewById(R.id.editTextOTP)
        verifyButton = findViewById(R.id.buttonVerify)

        phoneNumber.addTextChangedListener(object : TextWatcher {
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
                        phoneNumber.error = null
                    } else {
                        phoneNumber.error = "erroe"
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


        verifyButton.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    startActivity(intent)
                    finish()
                }
            }
        )

    }
}