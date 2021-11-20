package com.example.carpoolsystem.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.RegistrationUtils
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class EmailIdEmpty : AppCompatActivity() {
    private lateinit var emailIdEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private val PASSWORD_ERROR =
        "Password length should be 6"
    private val EMAIL_ID_ERROR = "Enter your GLA Email address"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_id_empty)
        emailIdEditText = findViewById(R.id.EnterEmailIdentity)
        passwordEditText = findViewById(R.id.EnterpasswordPassword)
        loginButton = findViewById(R.id.login_Button)


        emailIdEditText.addTextChangedListener(
            object : TextWatcher {
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
                        if (RegistrationUtils.isValidEmail(s.toString())) {
                            emailIdEditText.error = null

                        } else {
                            emailIdEditText.error = EMAIL_ID_ERROR
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

        passwordEditText.addTextChangedListener(
            object : TextWatcher {
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
                        if (RegistrationUtils.isValidPassword(p0.toString())) {
                            passwordEditText.error = null
                        } else {
                            passwordEditText.error = PASSWORD_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

        loginButton.setOnClickListener {
            val email = emailIdEditText.text.toString()
            val password = passwordEditText.text.toString()
            val credential = EmailAuthProvider.getCredential(email, password)
            var auth = FirebaseAuth.getInstance()
            auth.currentUser!!.linkWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.user
                        Toast.makeText(
                            applicationContext,
                            "EmailId added successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener(this) { a ->
                    Toast.makeText(this, a.message.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }
}