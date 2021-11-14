package com.example.carpoolsystem.screens

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.RegistrationUtils
import com.google.firebase.auth.FirebaseAuth

class SignupScreen1 : AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth? = null

    private val USERNAME_ERROR = "invalid Name"
    private val PASSWORD_ERROR =
        "Password should contain at least one upper case letter, lower case letter, number, and special characters(@#\$%^&+=!)"
    private val EMAIL_ID_ERROR = "Enter your GLA Email address"

    private lateinit var passwordEditText: EditText
    private lateinit var emailIdEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var nextButton: Button
    private lateinit var OTPSignUpButton: Button
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen1)
        val intent = intent
        val user = intent.getStringExtra("User").toString()

        firebaseAuth = FirebaseAuth.getInstance()
        passwordEditText = findViewById(R.id.editTextPassword)
        emailIdEditText = findViewById(R.id.editTextEmail)
        nameEditText = findViewById(R.id.editTextName)
        nextButton = findViewById(R.id.buttonNext)
        OTPSignUpButton = findViewById(R.id.buttonNext2)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")

        OTPSignUpButton.setOnClickListener {
            val intent = Intent(this@SignupScreen1, SignupScreen2PhoneNumber::class.java)
            startActivity(intent)
        }

        nextButton.setOnClickListener {
            register()
            progressDialog.show()
        }



        passwordEditText.addTextChangedListener(
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
                        if (RegistrationUtils.isValidPassword(s.toString())) {
                            passwordEditText.error = null

                        } else {
                            passwordEditText.error = PASSWORD_ERROR
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

        emailIdEditText.addTextChangedListener(
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
                        if (RegistrationUtils.isValidEmail(p0.toString())) {

                            emailIdEditText.error = null
                        } else {
                            emailIdEditText.error = EMAIL_ID_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

        nameEditText.addTextChangedListener(
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
                        if (RegistrationUtils.isValidUserName(p0.toString())) {
                            nameEditText.error = null
                        } else {
                            nameEditText.error = USERNAME_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
    }

    fun checkEmail() {
        val firebaseUser = firebaseAuth?.currentUser
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Verification mail send. Please verify your email",
                    Toast.LENGTH_SHORT
                ).show()
                firebaseAuth?.signOut()
                finish()
                startActivity(Intent(this, SignInScreen::class.java))
            } else {
                Toast.makeText(
                    this,
                    "Some error occurred. Please try again later.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun register() {
        val emailId: String = emailIdEditText.text.toString()
        val password: String = passwordEditText.text.toString()
        if (emailId.isEmpty() || password.isEmpty()) {
            progressDialog.hide()
            Toast.makeText(this, "Fill all given fields", Toast.LENGTH_SHORT).show()
        } else {
            firebaseAuth?.createUserWithEmailAndPassword(emailId, password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressDialog.hide()
                        checkEmail()
                    } else {
                        progressDialog.hide()
                        Toast.makeText(this, "Some error occurred.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}