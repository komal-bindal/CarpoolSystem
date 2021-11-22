package com.example.carpoolsystem.screens

import android.annotation.SuppressLint
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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class SignInScreen : AppCompatActivity() {
    private lateinit var signUpButton: Button
    private lateinit var otpLoginButton: Button
    private lateinit var forgetPasswordButton: Button
    private lateinit var loginButton: Button
    private lateinit var emailIdEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var progressDialog: ProgressDialog

    private var firebaseAuth: FirebaseAuth? = null
    private val UID = "uid"
    private val USER = "user"
    private val USERS_COLLECTION = "users"
    private val PASSWORD_ERROR =
        "Password should contain at least one upper case letter, lower case letter, number, and special characters(@#\$%^&+=!)"
    private val EMAIL_ID_ERROR = "Enter your GLA Email address"

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_screen)
        signUpButton = findViewById(R.id.buttonSignUp)
        otpLoginButton = findViewById(R.id.buttonOtpLogin)
        forgetPasswordButton = findViewById(R.id.buttonForgetPassword)
        loginButton = findViewById(R.id.buttonLogin)
        emailIdEditText = findViewById(R.id.editTextEnterEmail)
        passwordEditText = findViewById(R.id.editTextEnterPassword)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")

        firebaseAuth = FirebaseAuth.getInstance()

        val intent = intent
        val user = intent.getStringExtra("User").toString()

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

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignupScreen1::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }

        otpLoginButton.setOnClickListener {
            val intent = Intent(this, SignupScreen2PhoneNumber::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }

        forgetPasswordButton.setOnClickListener {
            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            progressDialog.show()
            val email = emailIdEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isEmpty() || password.isEmpty() || !RegistrationUtils.isValidEmail(email) || !RegistrationUtils.isValidPassword(
                    password
                )
            ) {
                progressDialog.hide()
                makeToast("Please enter email Id and password")
            } else {
                firebaseAuth?.signInWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val currentUser = firebaseAuth?.currentUser
                            if (currentUser?.isEmailVerified == true) {
                                progressDialog.hide()
                                val docReference =
                                    getReferenceOfCurrentUserFromDatabase(currentUser)
                                docReference.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            val list: List<DocumentSnapshot> =
                                                querySnapshot.documents
                                            for (d in list) {
                                                if (d.data?.get(USER) == user) {
                                                    if (user.toString() == "Passenger") {
                                                        startActivity(
                                                            Intent(
                                                                this,
                                                                DashboardPassenger::class.java
                                                            )
                                                        )
                                                    } else {
                                                        startActivity(
                                                            Intent(
                                                                this,
                                                                Dashboard::class.java
                                                            )
                                                        )
                                                    }
                                                    finish()
                                                } else {
                                                    makeToast("You have not registered as $user")
                                                }
                                            }
                                        } else {
                                            makeToast("User is not registered")
                                        }
                                    }.addOnFailureListener { e ->
                                        makeToast(e.message.toString())
                                    }
                            } else {
                                progressDialog.hide()
                                makeToast("Please verify your Email Id")
                            }
                        } else {
                            progressDialog.hide()
                            makeToast(task.exception.toString())
                        }
                    }
            }
        }
    }

    private fun getReferenceOfCurrentUserFromDatabase(currentUser: FirebaseUser): Query {
        return FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
            .whereEqualTo(
                UID,
                currentUser.uid.toString()
            )
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
