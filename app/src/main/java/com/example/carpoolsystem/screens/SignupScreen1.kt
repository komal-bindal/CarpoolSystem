package com.example.carpoolsystem.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.RegistrationUtils
import com.google.firebase.auth.FirebaseAuth

class SignupScreen1 : AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth? = null

    private val USERNAME_ERROR = "invalid Name"
    private val PASSWORD_ERROR =
        "Password Should contain at least one upper case letter,lower case letter,number,special characters(@#\$%^&+=!)"
    private val EMAIL_ID_ERROR = "Enter your GLA EmailID"

    private lateinit var password: EditText
    private lateinit var emailId: EditText
    private lateinit var name: EditText
    private lateinit var buttonNext: Button
    private lateinit var OTPSignUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen1)
        val intent = intent
        val user = intent.getStringExtra("User").toString()

        //firebaseAuth = FirebaseAuth.getInstance()
        password = findViewById(R.id.editTextPassword)
        emailId = findViewById(R.id.editTextEmail)
        name = findViewById(R.id.editTextName)
        buttonNext = findViewById(R.id.buttonNext)
        OTPSignUpButton = findViewById(R.id.buttonNext2)
        OTPSignUpButton.setOnClickListener {
            val intent = Intent(this@SignupScreen1, SignupScreen2PhoneNumber::class.java)
            startActivity(intent)
        }

        buttonNext.setOnClickListener {

            //register()
        }

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
        name.addTextChangedListener(object : TextWatcher {
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
                    if (RegistrationUtils.isValidUserName(p0.toString())) {
                        name.error = null
                    } else {
                        name.error = USERNAME_ERROR
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

//    fun checkEmail() {
//        val firebaseUser = firebaseAuth?.currentUser
//        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Toast.makeText(this, "Verification mail send", Toast.LENGTH_SHORT).show()
//                firebaseAuth?.signOut()
//                finish()
//                startActivity(Intent(this, SignupScreen2PhoneNumber::class.java))
//            } else {
//                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    fun register() {
//        var e: String = emailId.text.toString()
//        var p: String = password.text.toString()
//        if (e.isEmpty() || p.isEmpty()) {
//            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
//        } else {
//            firebaseAuth?.createUserWithEmailAndPassword(e, p)?.addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    checkEmail()
//                } else {
//                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    //}
}